package vm;

import hardware.cpu.CPU;
import hardware.cpu.Opcode;
import hardware.memoria.Memory;
import so.Escalonador;
import so.ProcessManager;
import util.Console;

public class VM {
	private static VM INSTANCE;

	public static int TAM_MEM;
	public CPU cpu;

	public ProcessManager pm;
	public Escalonador escalonador;

    public VM(){   // vm deve ser configurada com endereço de tratamento de interrupcoes
        
    	// memória
        TAM_MEM = 1024;
		Memory.init(TAM_MEM);
        
        // cpu
		cpu = CPU.init();
		cpu.pc = 0;         // Alterar

		Inicializa();
    }

	private void Inicializa() {

		Console.debug(" > VM.Inicializa() ");
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
