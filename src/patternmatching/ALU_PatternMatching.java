package patternmatching;

import constants.EmulatorConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ALU_PatternMatching implements EmulatorConstants {

    public static void checkInstruction(ArrayList<String> tokens, int index) throws Exception {
        if (!tokens.getFirst().equals("mv") && tokens.size() != 4) {
            throw new Exception("line: " + index + ": Invalid number of arguments for " + tokens.getFirst() + ". expected 4, got " + tokens.size());
        }
        if (tokens.getFirst().equals("mv") && tokens.size() != 3) {
            throw new Exception("line: " + index + ": Invalid number of arguments for " + tokens.getFirst() + ". expected 3, got " + tokens.size());
        }

        int n = tokens.size();
        if (Arrays.asList(EmulatorConstants.immediate).contains(tokens.getFirst())) {
            n--;
        }

        for (int i = 1; i < n; i++) {
            if (!checkPattern(tokens.get(i))) {
                throw new Exception("line: " + index + ": Invalid argument " + tokens.get(i));
            }
        }

        if (Arrays.asList(EmulatorConstants.immediate).contains(tokens.getFirst()) && !tokens.get(3).matches("-?\\d+")) {
            throw new Exception("line: " + index + ": Third argument of " + tokens.getFirst() + " must be a number");
        }
    }

    static boolean checkPattern(String s) {
        String regex = "x\\d+";  // Allow registers like "x10" and also "sp"
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
