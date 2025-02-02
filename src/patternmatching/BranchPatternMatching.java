package patternmatching;

import java.util.ArrayList;
import java.util.Map;

public class BranchPatternMatching {

    public static void checkInstruction(ArrayList<String> tokens, Map<String, Integer> labels, int index) {
        String branch = tokens.get(0);
        if (tokens.size() != 4) {
            throw new IllegalArgumentException("Invalid number of arguments for " + branch + ". expected 3, got " + tokens.size());
        }
        if (!checkPattern(tokens.get(1))) {
            throw new IllegalArgumentException("Invalid argument " + tokens.get(1));
        }
        if (!checkPattern(tokens.get(2))) {
            throw new IllegalArgumentException("Invalid argument " + tokens.get(2));
        }
        if (!labels.containsKey(tokens.get(3)) || labels.get(tokens.get(3)) < index) {
            throw new IllegalArgumentException("Invalid jump from branch " + tokens.get(3));
        }
    }

    static boolean checkPattern(String s) {
        return s.matches("x\\d+");
    }
}
