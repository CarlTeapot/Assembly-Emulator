package processing;

import patternmatching.ALU_PatternMatching;

import java.util.ArrayList;

public class AssemblyALU {
    public static void arithmetic(ArrayList<String> tokens, int[] registers,
                                 int index) throws Exception {
        String arithmetic = tokens.getFirst();
        ALU_PatternMatching.checkInstruction(tokens, index);

        switch (arithmetic) {
            case "add" -> add(tokens, registers, index);
            case "sub" -> sub(tokens, registers, index);
            case "mul" -> mul(tokens, registers, index);
            case "div" -> div(tokens, registers, index);
            case "rem" -> rem(tokens, registers, index);
            case "addi" -> addi(tokens, registers, index);
            case "mv" -> mv(tokens, registers, index);
            case "sll"-> sll(tokens, registers, index);
            case "slli" -> slli(tokens, registers, index);
            case "srli" -> srli(tokens, registers, index);
            case "srl" -> srl(tokens, registers, index);
            case "sra" -> sra(tokens, registers, index);
            case "srai" -> srai(tokens, registers, index);
            case "and" -> and(tokens, registers, index);
            case "andi" -> andi(tokens, registers, index);
            case "or" -> or(tokens, registers, index);
            case "ori" -> ori(tokens, registers, index);
            case "not" -> not(tokens, registers, index);
            case "xor" -> xor(tokens, registers, index);
            case "xori" -> xori(tokens, registers, index);

        }
    }
    public static void add(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   + registers[extractRegister(tokens.get(3))];
    }
    public static void sub(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   - registers[extractRegister(tokens.get(3))];
    }
    public static void mul(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   * registers[extractRegister(tokens.get(3))];
    }
    public static void div(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] =(int) registers[extractRegister(tokens.get(2))]
                   / registers[extractRegister(tokens.get(3))];
    }
    public static void rem(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   % registers[extractRegister(tokens.get(3))];
    }

    public static void addi(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   + Integer.parseInt(tokens.get(3));
    }
    public static void mv(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))];
    }
    public static void sll(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   << registers[extractRegister(tokens.get(3))];
    }
    public static void slli(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   << Integer.parseInt(tokens.get(3));
    }
    public static void srl(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   >> registers[extractRegister(tokens.get(3))];
    }
    public static void srli(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   >> Integer.parseInt(tokens.get(3));
    }
    public static void sra(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   >>> registers[extractRegister(tokens.get(3))];
    }
    public static void srai(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   >>> Integer.parseInt(tokens.get(3));
    }
    public static void and(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   & registers[extractRegister(tokens.get(3))];
    }
    public static void andi(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   & Integer.parseInt(tokens.get(3));
    }
    public static void or(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   | registers[extractRegister(tokens.get(3))];
    }
    public static void ori(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   | Integer.parseInt(tokens.get(3));
    }
    public static void xor(ArrayList<String> tokens, int[] registers, int index) throws Exception {

        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   ^ registers[extractRegister(tokens.get(3))];
    }
    public static void xori(ArrayList<String> tokens, int[] registers, int index) throws Exception {
        registers[extractRegister(tokens.get(1))] = registers[extractRegister(tokens.get(2))]
                   ^ Integer.parseInt(tokens.get(3));

    }
    public static void not(ArrayList<String> tokens, int[] registers, int index) throws Exception {
        registers[extractRegister(tokens.get(1))] = ~registers[extractRegister(tokens.get(2))];
    }
    public static int extractRegister(String s) {
        return Integer.parseInt(s.substring(1));
    }
}
