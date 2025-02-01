import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class AssemblyEmulator implements EmulatorConstants{
    private final String filepath;
    private final List<String> instructions;

    private int stackSize;
    private final ArrayList<String> tokens;
    private final int[] registers;
    private final byte[] stack;
    StringTokenizer st;
    public AssemblyEmulator(String filepath, int stackSize) {
        this.filepath = filepath;
        instructions = new ArrayList<>();
        tokens = new ArrayList<>();
        registers = new int[32];
        stack = new byte[stackSize];
        instructions.add("addi sp sp " + stackSize/2);

        readInstructionsFromFile();
    }
    private void readInstructionsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll(",", "");
                if (line.trim().isEmpty()) continue; // Skip empty lines
                if (line.charAt(0) == '#') continue; // Skip comments
                if (line.contains("#")) line = line.substring(0, line.indexOf("#"));
                instructions.add(line.trim()); // Trim whitespace and add to the list
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
    public void process() throws Exception {
        for (int i = 0; i < instructions.size(); i++) {
            processSingleLine(instructions.get(i), i);
        }
    }
    private void processSingleLine(String s, int index) throws Exception {
        st = new StringTokenizer(s, " ");
        while(st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
        replaceRegisters(tokens);
        if (Arrays.asList(load).contains(tokens.getFirst()))
            AssemblyLoad.load(tokens,registers,stack,index+1);
        else if (Arrays.asList(store).contains(tokens.getFirst()))
            AssemblyStore.store(tokens,registers,stack,index+1);
        else if (Arrays.asList(alu).contains(tokens.getFirst()))
            AssemblyALU.arithmetic(tokens,registers, index+1);
        tokens.clear();
    }

    private void replaceRegisters(ArrayList<String> tokens) {
        for (int i = 1; i < tokens.size(); i++) {
            tokens.set(i,tokens.get(i).replaceAll("sp", "x2"));
            tokens.set(i,tokens.get(i).replaceAll("ra", "x1"));
            tokens.set(i,tokens.get(i).replaceAll("zero", "x0"));

        }
    }
    public int getRegisterValue(int index) {
        return registers[index];
    }
    public byte getStackValue(int index) {
        return stack[index];
    }
    public byte getStackValue() {
        return stack[registers[2]];
    }

    public int generateValue(int pointer, int size) {
        int value = 0;
        for (int i = 0; i < size; i++) {
            value |= (stack[pointer + i] & 0xFF) << (8 * i);
        }
        return value;
    }
}
