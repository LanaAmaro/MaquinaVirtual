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
        PCB processfibo = VM.get().pm.create(fibonacci);*/
        
        Console.debug(" > Fatorial"); 
        
        Word[] fatorial = Programas.progCalculaFatorial;
        PCB processfat = VM.get().pm.create(fatorial);

        VM.get().escalonador.run();
       // VM.get().pm.finish(processfibo);
        VM.get().pm.finish(processfat);
    }

}