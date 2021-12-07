package vm;

import java.util.Queue;

import hardware.cpu.CPU;
import hardware.cpu.Opcode;
import so.PCB;
import so.ProcessManager;
import util.Console;

public class TrapHandling {

    public static void trap(int[] registradores) {
        
    	Queue<PCB> pedidos = ProcessManager.pcbList;
 
        switch (registradores[8]) {
            
        case 1:
                Console.log("ENTRADA");
                Console.print("\n > Digite um valor inteiro para "+pedidos.peek().name+":");
                String input = Console.read();

                // Converte o input para um valor inteiro
                int value = Integer.parseInt(input);
                
                VM.get().cpu.memory.data[VM.get().cpu.translate(registradores[9])].opc = Opcode.DATA;
                VM.get().cpu.memory.data[VM.get().cpu.translate(registradores[9])].p = value;
            
                break;

            case 2:
                Console.log("SAÍDA");
                Console.log("Valor: " + VM.get().cpu.memory.data[VM.get().cpu.translate(registradores[9])].p);
                break;
        }
    }
}
