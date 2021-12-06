package tarefas;

import util.Console;
import hardware.memoria.Memory;
import hardware.memoria.Word;
import so.Programas;

import vm.VM;

public class MainTarefas implements Tarefa {

	public void run() {

		Console.debug(" > MainTask.run()");

		Console.debug(" > Fibonacci");

		Word[] fibonacci = Programas.progCalculoDeFibonacci;
		VM.get().pm.create(fibonacci, "fibonacci");

		Console.debug(" > Fatorial");

		Word[] fatorial = Programas.progCalculaFatorial;
		VM.get().pm.create(fatorial, "fatorial");


		Console.debug(" > Bubble Sort");

		Word[] bubble = Programas.progOrdenacaoDeVetoresComBubbleSort;
		VM.get().pm.create(bubble, "bubble");
		
		Console.debug(" > Memória com instruções");
		for (int i = 0; i < 250; i++) {

			System.out.print(i + ":");
			System.out.print("[ " + Memory.get().data[i].opc + "," + Memory.get().data[i].r1 + ", "
					+ Memory.get().data[i].r2 + ", " + Memory.get().data[i].p + " ] \n");

		}

		VM.get().escalonador.run();
		
		Console.debug(" > Memória com todos os programas finalizados");
		for (int i = 0; i < 250; i++) {

			System.out.print(i + ":");
			System.out.print("[ " + Memory.get().data[i].opc + "," + Memory.get().data[i].r1 + ", "
					+ Memory.get().data[i].r2 + ", " + Memory.get().data[i].p + " ] \n");

		}

	}

}