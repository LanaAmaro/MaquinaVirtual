package vm;

import hardware.cpu.CPU;
import hardware.cpu.Opcode;
import util.Console;

public class TrapHandling {

    public static void trap(int[] registradores, CPU cpu) {
        Console.log("reg[8] = " + registradores[8]);
        Console.log("reg[9] = " + registradores[9]);
 
        switch (registradores[8]) {
            
        case 1:
                Console.log("ENTRADA");
                Console.print("\n > Digite um valor inteiro: ");
                String input = Console.read();

                // Converte o input para um valor inteiro
                int value = Integer.parseInt(input);
                
                cpu.memory.data[cpu.translate(registradores[9])].opc = Opcode.DATA;
                cpu.memory.data[cpu.translate(registradores[9])].p = value;
                //registradores.memory.data[registradores.registradores[9]].opc = Opcode.DATA;
                //registradores.memory.data[registradores.registradores[9]].p = value;

                Console.log("Valor armazenado " + cpu.memory.data[cpu.translate(registradores[9])].p);
                Console.log("Posição " + cpu.translate(registradores[9]));
                break;

            case 2:
                Console.log("SAÍDA");
                Console.log("Valor: " + cpu.memory.data[cpu.translate(registradores[9])].p);
                break;
        }
    }
}
