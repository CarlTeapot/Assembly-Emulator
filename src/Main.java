
public class Main {
    public static void main(String[] args) throws Exception {


        AssemblyEmulator emulator = new AssemblyEmulator("recursion.txt", 5001);
        emulator.process();
        emulator.printAllRegisters();
    }
}