package processing;

import constants.EmulatorConstants;

import java.util.ArrayList;

public class AssemblyHeap implements EmulatorConstants {
    public static void heap(ArrayList<String> tokens, int index, int[] registers) {
        check(tokens, index);
        switch (tokens.getFirst()) {
            case "malloc" -> malloc(tokens, registers);
            case "free" -> free(tokens, registers);
        }
    }

    private static void malloc(ArrayList<String> tokens, int[] registers) {
        int x = Integer.parseInt(tokens.get(1).substring(1));

        int tmp = registers[x];
        registers[x] = registers[gp];
        registers[gp] += tmp;
    }

    private static void free(ArrayList<String> tokens, int[] registers) {
        int x = Integer.parseInt(tokens.get(1).substring(1));
        registers[sp] -= x;

    }

    private static void check(ArrayList<String> tokens, int index) {
        if (tokens.size() != 2) {
            throw new IllegalArgumentException("line: " + (index + 1) + ": Invalid number of arguments for " + tokens.getFirst() + ". expected 3, got " + tokens.size());
        }
        if (!checkPattern(tokens.get(1))) {
            throw new IllegalArgumentException("line: " + (index + 1) + ": Invalid argument " + tokens.get(1));
        }
    }

    private static boolean checkPattern(String s) {
        return s.matches("x\\d+");
    }
}
