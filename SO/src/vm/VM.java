package vm;

import java.util.concurrent.Semaphore;
import hardware.cpu.CPU;
import hardware.cpu.Opcode;
import hardware.memoria.Memory;
import so.Escalonador;
import so.ProcessManager;
import util.Console;

public class VM {
	private static VM INSTANCE;
	public static Semaphore semConsole;
	public static int TAM_MEM;
	public CPU cpu;
	public static ProcessManager pm;
	public Escalonador escalonador;

    public VM(){   // vm deve ser configurada com endereço de tratamento de interrupcoes
		semConsole = new Semaphore(0);
    	// memória
        TAM_MEM = 1024;
		Memory.init(TAM_MEM);
		pm = new ProcessManager();
        
        // cpu
		cpu = CPU.init();
		cpu.pc = 0;         // Alterar

		Inicializa();
    }

	private void Inicializa() {

		//Console.debug(" > VM.Inicializa() ");
		// Inicializa kernel, OS e drivers (Software)

		ProcessManager.init();
		Escalonador.init();

		pm = ProcessManager.get();
		escalonador = Escalonador.get();
	}


	/**
	 * Cria uma instância única para a classe VM.
	 */
	public static void init() {
		if (INSTANCE == null) INSTANCE = new VM();
	}

	/**
	 * @return instância única da VM.
	 */
	public static VM get() {
		return INSTANCE;
	}


}
