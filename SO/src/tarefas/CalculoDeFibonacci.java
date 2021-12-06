package tarefas;

import hardware.memoria.Memory;
import hardware.memoria.Word;
import so.LogicaProgramas;

import util.Auxi;
import util.Console;

import vm.VM;

public class CalculoDeFibonacci implements Tarefa{
    public void run() {
        Console.debug(" > CalculoDeFibonacci.run()");
        Auxi aux = new Auxi();
        Word[] p = new LogicaProgramas().progCalculoDeFibonacci;
        aux.cargaProgramaParaMemoria(p, Memory.get().data);
//        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa CalculoDeFibonacci carregado ");
        aux.dumpMemoria(Memory.get().data, 0, 39);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(Memory.get().data, 39, 50);
    }
}
