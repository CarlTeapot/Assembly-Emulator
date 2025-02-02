import constants.EmulatorConstants;
import processing.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AssemblyEmulator implements EmulatorConstants {
    private final String filepath;
    private final List<String> instructions;
    private final Map<String, Integer> labels;
    private int stackSize;
    private final ArrayList<String> tokens;
    private final int[] registers;
    private final byte[] stack;
    private final byte[] heap;
    StringTokenizer st;
    public AssemblyEmulator(String filepath, int stackSize) {
        this.filepath = filepath;
        instructions = new ArrayList<>();
        tokens = new ArrayList<>();
        labels = new HashMap<>();
        registers = new int[32];
        stack = new byte[stackSize];
        heap = new byte[4 * stackSize];
        registers[2] = stackSize/2;
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
        for (int i = 0; i < instructions.size();i++) {
            st = new StringTokenizer(instructions.get(i), " ");
            while(st.hasMoreTokens()) {
                tokens.add(st.nextToken());
            }
            if (tokens.size() == 1 && tokens.getFirst().contains(":")) {
                labels.put(tokens.getFirst().substring(0, tokens.getFirst().length()-1), i);
            }
            tokens.clear();
        }

    }
    public void process() throws Exception {
        int i = 0;
        while (i != instructions.size()) {
            int x = processSingleLine(instructions.get(i), i);
            if (x == -1) i++;
            else i = x;
            if (x== -2)
                break;
        }

    }
    private int processSingleLine(String s, int index) throws Exception {
        st = new StringTokenizer(s, " ");
        while(st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
        replaceRegisters(tokens);
        int result;
        if (Arrays.asList(load).contains(tokens.getFirst()))
            AssemblyLoad.load(tokens,registers,stack,index);
        else if (Arrays.asList(store).contains(tokens.getFirst()))
            AssemblyStore.store(tokens,registers,stack,index);
        else if (Arrays.asList(alu).contains(tokens.getFirst()))
            AssemblyALU.arithmetic(tokens,registers, index);
        else if (Arrays.asList(branch).contains(tokens.getFirst())) {
            result = AssemblyBranch.Branch(tokens, registers, labels, index);
            tokens.clear();
            return result;
        }
        else if (Arrays.asList(jumps).contains(tokens.getFirst())) {
            result = AssemblyJump.processJump(tokens, index, registers, labels);
            tokens.clear();
            return result;
        }
        else if (Arrays.asList(EmulatorConstants.heap).contains(tokens.getFirst())) {
            AssemblyHeap.heap(tokens, index, registers, heap);
        }
        if (tokens.getFirst().equals("ecall")) {
            print(tokens.get(1));
        }
        if (tokens.getFirst().equals("ret")) {
            tokens.clear();
            return registers[ra]; //return address
        }
        if (tokens.getFirst().equals("end")) {
            return -2;
        }
        tokens.clear();
        return -1;
    }
    private void print(String s) throws Exception {
        if (!s.matches("x\\d+"))
            throw new Exception("Invalid register number");
        System.out.println(registers[AssemblyALU.extractRegister(s)]);
    }
    private void replaceRegisters(ArrayList<String> tokens) {
        for (int i = 1; i < tokens.size(); i++) {
            tokens.set(i,tokens.get(i).replaceAll("sp", "x2"));
            tokens.set(i,tokens.get(i).replaceAll("ra", "x1"));
            tokens.set(i,tokens.get(i).replaceAll("zero", "x0"));
            tokens.set(i,tokens.get(i).replaceAll("gp", "x3"));
        }
    }

    public int getRegisterValue(int index) {
        return registers[index];
    }
    public byte getStackValue(int index) {
        return stack[index];
    }
    public byte getStackValue() {
        return stack[registers[sp]];
    }


    public int getStackValue(int pointer, int size) {
        int value = 0;
        for (int i = 0; i < size; i++) {
            value |= (stack[pointer + i] & 0xFF) << (8 * i);
        }
        return value;
    }
    public void printAllRegisters() {
        for (int i = 0; i < registers.length; i++) {
            System.out.println("x" + i + ": " + registers[i]);
        }
    }
    public void printStack() {
        for (int i = 0; i < stack.length; i++) {
            System.out.println("stack[" + i + "]: " + stack[i]);
        }
    }
    public void printLabels() {
        for (Map.Entry<String, Integer> entry : labels.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
