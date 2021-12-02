package so;

import util.Console;
import vm.VM;

import java.util.Queue;

public class Escalonador extends Thread {
    private static Escalonador INSTANCE;

    private volatile int contador;

    private Escalonador() {}


    public void run() {                     Console.debug(" > Escalonador.run()");
        while (true) {
            Queue<PCB> processes = VM.get().pm.pcbList;
            contador = 0;

            while (contador == 0) {
                if (processes.size() > 0) {
                    processes.peek().status = Status.RUNNING;
                    VM.get().cpu.setContext(
                        processes.peek().allocatedPages, 
                        processes.peek().pc, 
                        processes.peek().id, 
                        processes.peek().reg
                    );
                    processes.remove(processes.peek());
                    VM.get().cpu.run();
                    contador++;
                }
            }
        }
    }



    /**
     * Cria uma instância única para a classe Escalonador.
     */
    public static void init() {
        if (INSTANCE == null) INSTANCE = new Escalonador();
    }

    /**
     * @return instância única do Escalonador.
     */
    public static Escalonador get() {
        return INSTANCE;
    }
}
