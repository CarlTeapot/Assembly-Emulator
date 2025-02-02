package processing;

import java.util.ArrayList;
import java.util.Map;

public class AssemblyJump {
    public static int processJump(ArrayList<String> tokens, int index, int[] registers, Map<String,Integer> labels) {
        if (tokens.size() != 2) {
            throw new IllegalArgumentException("Invalid number of arguments for " + tokens.getFirst() + ". expected 1, got " + (tokens.size() - 1));
        }
        if (!(labels.containsKey(tokens.get(1)) || tokens.get(1).matches("x\\d+")))
            throw new IllegalArgumentException("Invalid jump from branch " + tokens.get(1));
        int x = 0;
        if (tokens.get(1).matches("x\\d+")) {
            x = registers[extractRegister(tokens.get(1))];
        }
        else x= labels.get(tokens.get(1));

        if (tokens.get(0).equals("j")) {
            return x;

        } else if (tokens.get(0).equals("jal") || tokens.get(0).equals("call")) {
            registers[1] = index;
            return x;
        }
        return -1;
    }
    public static int extractRegister(String s) {
        return Integer.parseInt(s.substring(1));
    }
}
