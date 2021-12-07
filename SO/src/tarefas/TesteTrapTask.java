package tarefas;

import hardware.memoria.Memory;
import hardware.memoria.Word;
import so.Programas;

import util.Auxi;
import util.Console;

import vm.VM;

public class TesteTrapTask implements Tarefa{
    public void run() {
        Console.debug(" > TesteTrapTask.run()");
        Auxi aux = new Auxi();
        Word[] p = new Programas().testeTrap;
        aux.cargaProgramaParaMemoria(p, Memory.get().data);
//        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa TesteTrapTask carregado ");
        aux.dumpMemoria(Memory.get().data, 0, 30);
        //VM.get().cpu.run(); -> Ajustar
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(Memory.get().data, 39, 50);
    }
}

