
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        AssemblyEmulator emulator = new AssemblyEmulator("src/instructions.txt", 5000);
        emulator.process();
        System.out.println(emulator.getRegisterValue(10));
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value |= (emulator.getStackValue(4999 - i) & 0xFF) << (8 * i);
        }
        System.out.println(value);
    }
}