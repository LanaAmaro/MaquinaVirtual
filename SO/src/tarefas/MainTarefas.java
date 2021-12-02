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

        Console.debug(" > Fibonacci");
        Word[] fibonacci = Programas.progCalculoDeFibonacci;
        PCB process = VM.get().pm.create(fibonacci);
        VM.get().escalonador.run();
        VM.get().pm.finish(process);
    }

}