package vm;

import java.util.Queue;

import hardware.cpu.Opcode;
import so.PCB;
import so.StatusPCB;
import util.Console;

public class TrapHandling extends Thread {

	private static TrapHandling INSTANCE;

	public TrapHandling() {

	}

	public void run() {

		Console.debug("\n > entrada/saída.run()");

		Queue<PCB> pedidos = VM.get().fpc.filaPedidosConsole;

		while (true) {

			if (pedidos.size() == 0) {

				Console.debug(" Aguardando pedidos...");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				
				VM.get().fp.filaProntos.peek().status = StatusPCB.BLOCKED;
				VM.get().fb.colocaNaFilaBloqueados(VM.get().pm.pcbList.peek());
				VM.get().fp.retiraDaFilaProntos();

				int[] valor = pedidos.peek().reg;

				switch (valor[8]) {

				case 1:
					Console.log("\nENTRADA");
					Console.print("\n > Digite um valor inteiro para " + pedidos.peek().name + ":");
					String input = Console.read();

					// Converte o input para um valor inteiro
					int value = Integer.parseInt(input);

					VM.get().cpu.memory.data[VM.get().cpu.translate(valor[9])].opc = Opcode.DATA;
					VM.get().cpu.memory.data[VM.get().cpu.translate(valor[9])].p = value;

					break;

				case 2:
					Console.log("SAÍDA");
					Console.log("Valor: " + VM.get().cpu.memory.data[VM.get().cpu.translate(valor[9])].p);
					break;
				}

			}

		}

	}

	/**
	 * Cria uma instância única para a classe Escalonador.
	 */
	public static void init() {
		if (INSTANCE == null)
			INSTANCE = new TrapHandling();
	}

	/**
	 * @return instância única do TrapHandling.
	 */
	public static TrapHandling get() {
		return INSTANCE;
	}
}
