import javax.annotation.processing.SupportedSourceVersion;

public class Main {
    public static void main(String[] args) throws Exception {


        AssemblyEmulator emulator = new AssemblyEmulator("recursion.txt", 5001);
        emulator.process();
        int sp = emulator.getRegisterValue(2);
        emulator.printAllRegisters();
    }
}