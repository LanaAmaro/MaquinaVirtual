package tarefas;

import util.Console;

import hardware.memoria.Word;
import so.PCB;
import so.Programas;

import util.Console;

import vm.VM;

public class MainTarefas implements Tarefa {

    public void run() {

        Console.debug(" > MainTask.run()");

        /*Console.debug(" > Fibonacci");
        
       Word[] fibonacci = Programas.progCalculoDeFibonacci;
        PCB processFibo = VM.get().pm.create(fibonacci);
        
        Console.debug(" > Fatorial"); 
        
        Word[] fatorial = Programas.progCalculaFatorial;
        PCB processFat = VM.get().pm.create(fatorial);*/
        
        Console.debug(" > Bubble Sort");
        
        Word[] bubble = Programas.progOrdenacaoDeVetoresComBubbleSort;
        PCB processBub = VM.get().pm.create(bubble);

        VM.get().escalonador.run();
       // VM.get().pm.finish(processFibo);
       //VM.get().pm.finish(processFat);
       VM.get().pm.finish(processBub);
    }

}