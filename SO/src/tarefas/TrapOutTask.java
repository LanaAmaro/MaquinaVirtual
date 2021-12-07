package tarefas;

import hardware.memoria.Memory;
import hardware.memoria.Word;
import so.Programas;

import util.Auxi;
import util.Console;

import vm.VM;

public class TrapOutTask implements Tarefa {
    public void run() {                             Console.debug(" > TrapOutTask.run()");
        Auxi aux = new Auxi();
        Word[] p = new Programas().trapOut;
        aux.cargaProgramaParaMemoria(p, Memory.get().data);
//        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa TRAP OUT ");
        aux.dumpMemoria(Memory.get().data, 10, 11);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(Memory.get().data, 10, 11);
        
    }
}
