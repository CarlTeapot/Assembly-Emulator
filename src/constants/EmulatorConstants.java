package constants;

public interface EmulatorConstants {
     final int ra = 1;
     final int sp = 2;
     final String[] load = new String[]{"li", "lh", "lb", "lw"};
     final String[] store = new String[]{"sw", "sb", "sh"};
     final String[] alu = new String[]{"add", "addi", "sub", "mul",
                              "div", "rem", "mv", "sll",
                              "slli", "srli", "srl", "sra", "srai",
                              "and", "andi", "or", "not", "xor", "xori"};
     final String[] immediate = new String[] {"addi", "slli", "srli", "srai", "andi", "ori", "xori"
     };
     final String[] branch = new String[]{"beq", "bne", "blt", "bgt", "ble", "bge"};
     final String[] jumps = new String[]{"j", "jal", "call"};
}
