import java.util.ArrayList;


public class AssemblyLoad implements EmulatorConstants{

    static void load(ArrayList<String> tokens, int[] registers, byte[] stack,
                      int index) throws Exception {
        String load = tokens.getFirst();
        if (tokens.size() != 3) {
            throw new Exception("line: " + index + ": Invalid number of arguments for " + tokens.getFirst() + ". expected 3, got " + tokens.size());
        }
        switch (load) {
            case "li" -> loadImmediate(tokens,registers, stack,index);
            case "lh" -> loadHalf(tokens, registers, stack,index);
            case "lb" -> loadByte(tokens, registers, stack,  index);
            case "lw" -> loadWord(tokens, registers, stack,  index);
        }
    }
    private static void loadImmediate(ArrayList<String> tokens, int[] registers, byte[] stack, int index) throws Exception {
        if (tokens.getFirst().equals("addi") &&
                !((Character.isDigit(tokens.get(2).charAt(0)) || tokens.get(2).charAt(0) == '-')
                        && tokens.get(2).substring(1).chars().allMatch(Character::isDigit))) {
            throw new Exception("line: " + index + ": Second argument of " + tokens.getFirst() + " must be a number");
        }
        if (!CPU_RAMPatternMatching.checkPattern(tokens.get(1), "x"))
            throw new Exception("line: " + index + ": First argument of " + tokens.getFirst() + " must be a register");

        registers[Integer.parseInt(tokens.get(1).substring(1))] = Integer.parseInt(tokens.get(2));
    }
    private static void loadHalf(ArrayList<String> tokens,  int[] registers, byte[] stack, int index) throws Exception {
        Result result = CPU_RAMPatternMatching.getResult(tokens, registers, stack, index);
        int value = generateValue(stack, result.pointer(), 2);
        registers[result.register()] = value;

    }
    private static void loadByte(ArrayList<String> tokens,  int[] registers, byte[] stack, int index) throws Exception {
        Result result = CPU_RAMPatternMatching.getResult(tokens, registers, stack,  index);
        int value = generateValue(stack, result.pointer(), 1);
        registers[result.register()] = value;
    }
    private static void loadWord(ArrayList<String> tokens,  int[] registers, byte[] stack,  int index) throws Exception {
        Result result = CPU_RAMPatternMatching.getResult(tokens, registers, stack, index);
        int value = generateValue(stack, result.pointer(), 4);
        registers[result.register()] = value;
    }

    public static int generateValue(byte[] stack, int pointer, int size) {
        int value = 0;
        for (int i = 0; i < size; i++) {
            value |= (stack[pointer + i] & 0xFF) << (8 * i);
        }
        return value;
    }



}
