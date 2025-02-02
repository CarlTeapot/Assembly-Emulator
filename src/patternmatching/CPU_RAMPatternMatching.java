package patternmatching;
import records.Result;
import java.util.ArrayList;


public class CPU_RAMPatternMatching {
    public static Result getResult(ArrayList<String> tokens, int[] registers, int stackSize, int heapSize, int index) throws Exception {
        if(!checkPattern(tokens.get(1), "x")) {
            throw new Exception("line: " + (index + 1) + ": First argument of " + tokens.getFirst() + " must be a register");
        }
        if(!checkPattern(tokens.get(2), "(")) {
            throw new Exception("line: " + (index + 1) + ": First argument of " + tokens.getFirst() + " must be a register");
        }
        int register = Integer.parseInt(tokens.get(1).substring(1));
        int pntr = Integer.parseInt(tokens.get(2).substring(tokens.get(2).indexOf('x')+1, tokens.get(2).length()-1));

        int offset = tokens.get(2).charAt(0) - '0';
        int pointer = registers[pntr] +  offset;

        if (register < 0 || register >= registers.length) {
            throw new Exception("line: " + (index + 1) + ": Invalid register number. Expected a number between 0 and " + registers.length + ", got " + register);
        }
        int sz = stackSize;
        if (pntr == 3)
            sz = heapSize;
        if (pointer < 0 || pointer >= sz) {
            throw new Exception("line: " + (index + 1) + ": Invalid pointer. Expected a number between 0 and "+ sz + ", got " + pointer);
        }

        return new Result(register, pointer);
    }
     public static boolean checkPattern(String s, String pattern2) {
        String regex = "";
        if (pattern2.equals("(")) {
            regex = "\\d+\\((x\\d+)\\)";
        }
        if (pattern2.equals("x"))
            regex = "x\\d+";
        return s.matches(regex);
    }
}
