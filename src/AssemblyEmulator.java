import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class AssemblyEmulator implements EmulatorConstants{
    private final String filepath;
    private final List<String> instructions;

    private int stackPointer;
    private final ArrayList<String> tokens;
    private final int[] registers;
    private final byte[] stack;
    StringTokenizer st;
    public AssemblyEmulator(String filepath, int stackSize) {
        stackPointer = 0;
        this.filepath = filepath;
        instructions = new ArrayList<>();
        tokens = new ArrayList<>();
        registers = new int[32];
        stack = new byte[stackSize];
        stackPointer = stackSize - 1;

        readInstructionsFromFile();
    }
    private void readInstructionsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
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
        if (Arrays.asList(load).contains(tokens.getFirst()))
            AssemblyLoad.load(tokens,registers,stack,stackPointer, index);
        else if (Arrays.asList(store).contains(tokens.getFirst()))
            AssemblyStore.store(tokens,registers,stack,stackPointer, index);
        else if (Arrays.asList(arithmetic).contains(tokens.getFirst()))
            AssemblyArithmetic.arithmetic(tokens,registers, index);


        tokens.clear();
    }
    public int getRegisterValue(int index) {
        return registers[index];
    }
    public byte getStackValue(int index) {
        return stack[index];
    }
    public byte getStackValue() {
        return stack[stackPointer];
    }
}
