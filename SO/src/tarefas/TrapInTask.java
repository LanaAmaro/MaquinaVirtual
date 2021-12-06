package tarefas;

import hardware.memoria.Memory;
import hardware.memoria.Word;
import so.LogicaProgramas;
import util.Auxi;
import util.Console;

import vm.VM;

public class TrapInTask implements Tarefa {
    public void run() {                             Console.debug(" > TrapInTask.run()");
        Auxi aux = new Auxi();
        Word[] p = new LogicaProgramas().trapIn;
        aux.cargaProgramaParaMemoria(p, Memory.get().data);
//        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa TRAP IN ");
        aux.dumpMemoria(Memory.get().data, 4, 5);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(Memory.get().data, 4, 5);
        
    }
}
