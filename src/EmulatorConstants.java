public interface EmulatorConstants {
     final String[] load = new String[]{"li", "lh", "lb", "lw"};
     final String[] store = new String[]{"sw", "sb", "sh"};
     final String[] arithmetic = new String[]{"add", "addi", "sub", "mul", "div", "rem"};
     final String[] logic = new String[]{"and", "or", "not", "xor"};
     final String[] shift = new String[]{"sll", "srl", "sra"};
     final String[] branches = new String[]{"beq", "bne", "blt", "bgt", "ble", "bge"};
     final String[] jumps = new String[]{"j", "jal", "jr", "jalr", "call"};
}
