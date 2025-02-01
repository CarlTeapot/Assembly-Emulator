public interface EmulatorConstants {
     final String[] load = new String[]{"li", "lh", "lb", "lw"};
     final String[] store = new String[]{"sw", "sb", "sh"};
     final String[] alu = new String[]{"add", "addi", "sub", "mul",
                              "div", "rem", "mv", "sll",
                              "slli", "srli", "srl", "sra", "srai",
                              "and", "andi", "or", "not", "xor", "xori"};
     final String[] immediate = new String[] {"addi", "slli", "srli", "srai", "andi", "ori", "xori"
     };

     final String[] branches = new String[]{"beq", "bne", "blt", "bgt", "ble", "bge"};
     final String[] jumps = new String[]{"j", "jal", "jr", "jalr", "call"};
}
