package processing;

import patternmatching.BranchPatternMatching;

import java.util.ArrayList;
import java.util.Map;

public class AssemblyBranch {
    public static int Branch(ArrayList<String> tokens, int[] registers, Map<String, Integer> labels, int index) throws Exception {
        String branch = tokens.getFirst();
        if (tokens.size() != 4) {
            throw new Exception("line: " + (index + 1) + ": Invalid number of arguments for " + tokens.getFirst() + ". expected 3, got " + tokens.size());
        }
        BranchPatternMatching.checkInstruction(tokens, labels, index);
        switch (branch) {
            case "beq" -> {
                return beq(registers, tokens, labels);
            }
            case "bne" -> {
                return bne(registers, tokens, labels);
            }
            case "blt" -> {
                return blt(registers, tokens, labels);
            }
            case "ble" -> {
                return ble(registers, tokens, labels);
            }
            case "bge" -> {
                return bge(registers, tokens, labels);
            }
            case "bgt" -> {
                return bgt(registers, tokens, labels);
            }
        }
        // this will never happen
        return -1;
    }

    private static int beq(int[] registers, ArrayList<String> tokens, Map<String, Integer> labels) {
        int newIndex = -1;
        if (registers[extractRegister(tokens.get(1))] == registers[extractRegister(tokens.get(2))]) {
            newIndex = labels.get(tokens.get(3));
        }
        return newIndex;
    }

    private static int bne(int[] registers, ArrayList<String> tokens, Map<String, Integer> labels) {
        int newIndex = -1;
        if (registers[extractRegister(tokens.get(1))] != registers[extractRegister(tokens.get(2))]) {
            newIndex = labels.get(tokens.get(3));
        }
        return newIndex;
    }

    private static int blt(int[] registers, ArrayList<String> tokens, Map<String, Integer> labels) {
        int newIndex = -1;

        if (registers[extractRegister(tokens.get(1))] < registers[extractRegister(tokens.get(2))]) {

            newIndex = labels.get(tokens.get(3));
        }
        return newIndex;

    }

    private static int bgt(int[] registers, ArrayList<String> tokens, Map<String, Integer> labels) {
        int newIndex = -1;
        if (registers[extractRegister(tokens.get(1))] > registers[extractRegister(tokens.get(2))]) {
            newIndex = labels.get(tokens.get(3));
        }
        return newIndex;

    }

    private static int bge(int[] registers, ArrayList<String> tokens, Map<String, Integer> labels) {
        int newIndex = -1;
        if (registers[extractRegister(tokens.get(1))] >= registers[extractRegister(tokens.get(2))]) {
            newIndex = labels.get(tokens.get(3));
        }
        return newIndex;

    }

    private static int ble(int[] registers, ArrayList<String> tokens, Map<String, Integer> labels) {
        int newIndex = -1;
        if (registers[extractRegister(tokens.get(1))] <= registers[extractRegister(tokens.get(2))]) {
            newIndex = labels.get(tokens.get(3));
        }
        return newIndex;
    }


    public static int extractRegister(String s) {
        return Integer.parseInt(s.substring(1));
    }
}
