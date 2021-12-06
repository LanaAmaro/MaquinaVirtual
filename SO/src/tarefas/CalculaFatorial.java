package tarefas;

import hardware.memoria.Memory;
import hardware.memoria.Word;
import so.Programas;

import util.Auxi;
import util.Console;

import vm.VM;

public class CalculaFatorial implements Tarefa{
    public void run() {
        Console.debug(" > CalculaFatorial.run()");
        Auxi aux = new Auxi();
        Word[] p = new Programas().progCalculoDeFibonacci;
        aux.cargaProgramaParaMemoria(p, Memory.get().data);
//        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa CalculaFatorial carregado ");
        aux.dumpMemoria(Memory.get().data, 0, 30);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(Memory.get().data, 39, 50);
    }
}


