package processing;

import constants.EmulatorConstants;
import patternmatching.CPU_RAMPatternMatching;
import records.Result;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssemblyStore implements EmulatorConstants {
    public static void store(ArrayList<String> tokens, int[] registers, byte[] stack,
                             byte[] heap, int index) throws Exception {

        String store = tokens.getFirst();
        if (tokens.size() != 3) {
            throw new Exception("line: " + index + ": Invalid number of arguments for " + tokens.getFirst() + ". expected 3, got " + tokens.size());
        }
        Result result = CPU_RAMPatternMatching.getResult(tokens, registers, stack.length, heap.length,  index);
        byte[] xd = stack;
        int pntr = Integer.parseInt(tokens.get(2).substring(tokens.get(2).indexOf('x') + 1, tokens.get(2).length()-1));

        if (pntr == 3)
            xd = heap;
        int value = registers[result.register()];
        switch (store) {
            case "sh" -> storeValue(xd, value, result.pointer() ,2);
            case "sb" -> storeValue(xd, value, result.pointer() ,1);
            case "sw" -> storeValue(xd, value, result.pointer() ,4);
        }
    }

    private static void storeValue(byte[] stack, int value, int pointer, int size) {

        for (int i = 0; i < size; i++) {
            stack[pointer + i] = (byte) ((value >> (8 * i)) & 0xFF);
        }
    }
}
