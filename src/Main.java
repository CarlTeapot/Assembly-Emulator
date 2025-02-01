import javax.annotation.processing.SupportedSourceVersion;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to my assembly emulator");
        System.out.println("I dont recommend using the following registers to store values: ");
        System.out.println("X0: Zero register");
        System.out.println("X1: Return address register");
        System.out.println("X2: Stack pointer register");

        AssemblyEmulator emulator = new AssemblyEmulator("src/instructions.txt", 5001);
        emulator.process();

        int sp = emulator.getRegisterValue(2);


        for (int i = 5; i < 25; i++) {
            System.out.println("Register x" + i + ": " + emulator.getRegisterValue(i));
        }

    }
}