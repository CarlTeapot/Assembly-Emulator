package processing;
import constants.EmulatorConstants;
import patternmatching.CPU_RAMPatternMatching;
import records.Result;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AssemblyLoad implements EmulatorConstants {

    public static void load(ArrayList<String> tokens, int[] registers, byte[] stack, byte[] heap,
                            int index) throws Exception {
        String load = tokens.getFirst();
        if (tokens.size() != 3) {
            throw new Exception("line: " + index + ": Invalid number of arguments for " + tokens.getFirst() + ". expected 3, got " + tokens.size());
        }
        if (load.equals("li")) {
            loadImmediate(tokens, registers, index);
            return;
        }
        Result result = CPU_RAMPatternMatching.getResult(tokens, registers, stack.length, heap.length, index);
        byte[] xd = stack;
        int pntr = Integer.parseInt(tokens.get(2).substring(tokens.get(2).indexOf('x') + 1, tokens.get(2).length()-1));
        if (pntr == 3)
            xd = heap;

        int value = 0;
        switch (load) {
            case "lh" -> value = generateValue(xd, result.pointer(), 2);
            case "lb" -> value = generateValue(xd, result.pointer(), 1);
            case "lw" -> value = generateValue(xd, result.pointer(), 4);
        }
        registers[result.register()] = value;
        System.out.println("register: " + value);

    }
    private static void loadImmediate(ArrayList<String> tokens, int[] registers, int index) throws Exception {
        if (tokens.getFirst().equals("addi") &&
                !((Character.isDigit(tokens.get(2).charAt(0)) || tokens.get(2).charAt(0) == '-')
                        && tokens.get(2).substring(1).chars().allMatch(Character::isDigit))) {
            throw new Exception("line: " + index + ": Second argument of " + tokens.getFirst() + " must be a number");
        }
        if (!CPU_RAMPatternMatching.checkPattern(tokens.get(1), "x"))
            throw new Exception("line: " + index + ": First argument of " + tokens.getFirst() + " must be a register");

        registers[Integer.parseInt(tokens.get(1).substring(1))] = Integer.parseInt(tokens.get(2));
    }

    public static int generateValue(byte[] stack, int pointer, int size) {
        int value = 0;
        for (int i = 0; i < size; i++) {
            value |= (stack[pointer + i] & 0xFF) << (8 * i);
        }
        return value;
    }
}
