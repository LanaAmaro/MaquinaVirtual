package so;

import util.Console;
import vm.VM;

import java.util.Queue;

import hardware.cpu.Opcode;

public class Escalonador extends Thread {
	private static Escalonador INSTANCE;

	private volatile int contador;

	private PCB temp;

	public Escalonador() {
	}

	public void run() {

		Console.debug(" > Escalonador.run()");

		Queue<PCB> processes = VM.get().fp.filaProntos;

		while (processes.size() > 0) {

			contador = 0;

			while (contador == 0) {

				processes.peek().status = StatusPCB.RUNNING;

				try {
					VM.get().semCPU.acquire(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				VM.get().cpu.run();

				if (VM.get().cpu.memory.data[VM.get().cpu.translate(processes.peek().pc)].opc.equals(Opcode.STOP)) {

					VM.get().pm.finish(processes.peek());

				} else {

					processes.peek().status = StatusPCB.READY;
					processes.peek().setPc(VM.get().cpu.pc);
					temp = processes.peek();
					processes.remove(processes.peek());
					processes.add(temp);
				}

				contador++;
			}

			VM.get().semCPU.release(1);

		}
	}

	/**
	 * Cria uma instância única para a classe Escalonador.
	 */
	public static void init() {
		if (INSTANCE == null)
			INSTANCE = new Escalonador();
	}

	/**
	 * @return instância única do Escalonador.
	 */
	public static Escalonador get() {
		return INSTANCE;
	}
}
