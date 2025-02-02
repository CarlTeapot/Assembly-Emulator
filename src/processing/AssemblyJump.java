package processing;

import java.util.ArrayList;
import java.util.Map;

public class AssemblyJump {
    public static int processJump(ArrayList<String> tokens, int index, int[] registers, Map<String,Integer> labels) {
        if (tokens.size() <= 1)
            throw new IllegalArgumentException("Invalid number of arguments for " + tokens.get(0));
        if (!(labels.containsKey(tokens.get(1)) || tokens.get(1).matches("x\\d+")))
            throw new IllegalArgumentException("Invalid jump from branch " + tokens.get(1));


        switch (tokens.get(0)) {
            case "j" -> {
                return j(tokens, index, registers, labels);
            }
            case "call" -> {
                return call(tokens, index, registers, labels);
            }
        }
        return -1;
    }
    private static int j(ArrayList<String> tokens, int index, int[] registers, Map<String,Integer> labels) {
        if (tokens.size() != 2) {
            throw new IllegalArgumentException("Invalid number of arguments for " + tokens.get(0) + ". expected 2, got " + tokens.size());
        }
        int x = 0;
        if (tokens.get(1).matches("x\\d+")) {
            x = registers[extractRegister(tokens.get(1))];
        } else if (tokens.get(1).matches("\\d+\\((x\\d+)\\)")) {
            String[] s = tokens.get(1).split("\\(");
            x = registers[extractRegister(s[1]) + Integer.parseInt(s[0])];
        } else {
            if (!labels.containsKey(tokens.get(1)))
                throw new IllegalArgumentException("Invalid jump from branch " + tokens.get(1));
            x = labels.get(tokens.get(1));
        }
        return x;
    }
    private static int call(ArrayList<String> tokens, int index, int[] registers, Map<String,Integer> labels) {
        if (tokens.size() != 2) {
            throw new IllegalArgumentException("Invalid number of arguments for " + tokens.get(0) + ". expected 2, got " + tokens.size());
        }
        int x = 0;
        if (tokens.get(1).matches("x\\d+")) {
            x = registers[extractRegister(tokens.get(1))];
        } else if (tokens.get(1).matches("\\d+\\((x\\d+)\\)")) {
            String[] s = tokens.get(1).split("\\(");
            x = registers[extractRegister(s[1]) + Integer.parseInt(s[0])];
        } else {
            if (!labels.containsKey(tokens.get(1)))
                throw new IllegalArgumentException("Invalid jump from branch " + tokens.get(1));
            x = labels.get(tokens.get(1));
        }
        registers[1] = index+1;
        return x;
    }
        public static int extractRegister(String s) {
        return Integer.parseInt(s.substring(1));
    }
}

