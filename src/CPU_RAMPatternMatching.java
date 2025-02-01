import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPU_RAMPatternMatching {
    static Result getResult(ArrayList<String> tokens, int[] registers, byte[] stack,  int index) throws Exception {
        if(!checkPattern(tokens.get(1), "x")) {
            throw new Exception("line: " + index + ": First argument of " + tokens.getFirst() + " must be a register");
        }
        if(!checkPattern(tokens.get(2), "(")) {
            throw new Exception("line: " + index + ": First argument of " + tokens.getFirst() + " must be a register");
        }
        int register = Integer.parseInt(tokens.get(1).substring(1));
        int offset = tokens.get(2).charAt(0) - '0';
        int pointer = registers[2];
        pointer += offset;

        if (register < 0 || register >= registers.length) {
            throw new Exception("line: " + index + ": Invalid register number. Expected a number between 0 and " + registers.length + ", got " + register);
        }
        if (pointer < 0 || pointer >= stack.length) {
            throw new Exception("line: " + index + ": Invalid pointer. Expected a number between 0 and "+ stack.length + ", got " + pointer);
        }
        return new Result(register, pointer);
    }


     static boolean checkPattern(String s, String pattern2) {
        String regex = "";
        if (pattern2.equals("(")) {
            regex = "\\d+\\((x\\d+)\\)";
        }
        if (pattern2.equals("x"))
            regex = "x\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
