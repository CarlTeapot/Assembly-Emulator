package processing;

import patternmatching.ALU_PatternMatching;

import java.util.ArrayList;

public class AssemblyALU {
    public static void arithmetic(ArrayList<String> tokens, int[] registers,
                                  int index) throws Exception {
        String arithmetic = tokens.getFirst();
        ALU_PatternMatching.checkInstruction(tokens, index);

        switch (arithmetic) {
            case "add" -> add(tokens, registers);
            case "sub" -> sub(tokens, registers);
            case "mul" -> mul(tokens, registers);
            case "div" -> div(tokens, registers);
            case "rem" -> rem(tokens, registers);
            case "addi" -> addi(tokens, registers);
            case "mv" -> mv(tokens, registers);
            case "sll" -> sll(tokens, registers);
            case "slli" -> slli(tokens, registers);
            case "srli" -> srli(tokens, registers);
            case "srl" -> srl(tokens, registers);
            case "sra" -> sra(tokens, registers);
            case "srai" -> srai(tokens, registers);
            case "and" -> and(tokens, registers);
            case "andi" -> andi(tokens, registers);
            case "or" -> or(tokens, registers);
            case "ori" -> ori(tokens, registers);
            case "not" -> not(tokens, registers);
            case "xor" -> xor(tokens, registers);
            case "xori" -> xori(tokens, registers);
        }
    }

    public static void add(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                + registers[extractRegister(tokens.get(3))];
    }

    public static void sub(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                - registers[extractRegister(tokens.get(3))];
    }

    public static void mul(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                * registers[extractRegister(tokens.get(3))];
    }

    public static void div(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                / registers[extractRegister(tokens.get(3))];
    }

    public static void rem(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                % registers[extractRegister(tokens.get(3))];
    }

    public static void addi(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                + Integer.parseInt(tokens.get(3));
    }

    public static void mv(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))];
    }

    public static void sll(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                << registers[extractRegister(tokens.get(3))];
    }

    public static void slli(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                << Integer.parseInt(tokens.get(3));
    }

    public static void srl(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                >> registers[extractRegister(tokens.get(3))];
    }

    public static void srli(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                >> Integer.parseInt(tokens.get(3));
    }

    public static void sra(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                >>> registers[extractRegister(tokens.get(3))];
    }

    public static void srai(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                >>> Integer.parseInt(tokens.get(3));
    }

    public static void and(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                & registers[extractRegister(tokens.get(3))];
    }

    public static void andi(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                & Integer.parseInt(tokens.get(3));
    }

    public static void or(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                | registers[extractRegister(tokens.get(3))];
    }

    public static void ori(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                | Integer.parseInt(tokens.get(3));
    }

    public static void xor(ArrayList<String> tokens, int[] registers) {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                ^ registers[extractRegister(tokens.get(3))];
    }

    public static void xori(ArrayList<String> tokens, int[] registers) {
        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                ^ Integer.parseInt(tokens.get(3));

    }

    public static void not(ArrayList<String> tokens, int[] registers) {
        registers[extractRegister(tokens.get(1))] = ~registers[extractRegister(tokens.get(2))];
    }

    public static int extractRegister(String s) {
        return Integer.parseInt(s.substring(1));
    }
}
