import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssemblyStore implements EmulatorConstants{


    static void store(ArrayList<String> tokens, int[] registers, byte[] stack,
                      int index) throws Exception {
        String store = tokens.getFirst();
        if (tokens.size() != 3) {
            throw new Exception("line: " + index + ": Invalid number of arguments for " + tokens.getFirst() + ". expected 3, got " + tokens.size());
        }
        switch (store) {
            case "sh" -> storeHalf(tokens, registers, stack, index);
            case "sb" -> storeByte(tokens, registers, stack, index);
            case "sw" -> storeWord(tokens, registers, stack , index);
        }
    }

    private static void storeHalf(ArrayList<String> tokens,  int[] registers, byte[] stack,  int index) throws Exception {
        Result result = CPU_RAMPatternMatching.getResult(tokens, registers, stack, index);
        storeValue(stack, registers[result.register()], result.pointer(), 2);
    }
    private static void storeByte(ArrayList<String> tokens,  int[] registers, byte[] stack,  int index) throws Exception {
        Result result = CPU_RAMPatternMatching.getResult(tokens, registers, stack,  index);
        storeValue(stack, registers[result.register()], result.pointer(), 1);
    }
    private static void storeWord(ArrayList<String> tokens,  int[] registers, byte[] stack,  int index) throws Exception {
        Result result = CPU_RAMPatternMatching.getResult(tokens, registers, stack,  index);
        storeValue(stack, registers[result.register()], result.pointer(), 4);
    }

    private static void storeValue(byte[] stack, int value, int pointer, int size) {
        for (int i = 0; i < size; i++) {
            stack[pointer - i] = (byte) ((value >> (8 * i)) & 0xFF);
        }
    }



}
