package tarefas;

import hardware.memoria.Memory;
import hardware.memoria.Word;
import so.LogicaProgramas;

import util.Auxi;
import util.Console;

import vm.VM;

import vm.VM;

public class BubbleSortTask implements Tarefa{
    public void run() {
        Console.debug(" > BubbleSortTask.run()");
        Auxi aux = new Auxi();
        Word[] p = new LogicaProgramas().progOrdenacaoDeVetoresComBubbleSort;
        aux.cargaProgramaParaMemoria(p, Memory.get().data);
//        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa bubbleSort carregado ");
        aux.dumpMemoria(Memory.get().data, 0, 39);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(Memory.get().data, 39, 50);
    }
}