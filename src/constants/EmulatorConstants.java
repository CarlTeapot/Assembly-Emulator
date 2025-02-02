package constants;

public interface EmulatorConstants {
     int ra = 1;
     int sp = 2;
     int gp = 3;
     String[] load = new String[]{"li", "lh", "lb", "lw"};
     String[] store = new String[]{"sw", "sb", "sh"};
     String[] alu = new String[]{"add", "addi", "sub", "mul",
                        "div", "rem", "mv", "sll",
                        "slli", "srli", "srl", "sra", "srai",
                        "and", "andi", "or", "not", "xor", "xori"};
     String[] immediate = new String[] {"addi", "slli", "srli", "srai", "andi", "ori", "xori"};
     String[] branch = new String[]{"beq", "bne", "blt", "bgt", "ble", "bge"};
     String[] jumps = new String[]{"j", "jal", "call"};
     String[] heap = new String[]{"malloc", "free" };

}
