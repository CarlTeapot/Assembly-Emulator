import javax.annotation.processing.SupportedSourceVersion;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to my Assembly emulator");
        System.out.println("I dont recommend using the following registers to store values: ");
        System.out.println("X0: Zero register");
        System.out.println("X1: Return address register");
        System.out.println("X2: Stack pointer register");
        System.out.println("Beware, if you separate instructions with empty lines, the emulator will work fine \n" +
                "but if your code has a problem and exception is thrown, the numbering of the lines will be incorrect" +
                "(it may tell you that program crashed because of line 23, but in reality it is line 30");

        AssemblyEmulator emulator = new AssemblyEmulator("Jump_test.txt", 5001);
        emulator.process();
        int sp = emulator.getRegisterValue(2);
        emulator.printAllRegisters();
    }
}