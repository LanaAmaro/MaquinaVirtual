package vm;

import java.util.concurrent.Semaphore;

import hardware.cpu.CPU;
import hardware.cpu.Opcode;
import hardware.memoria.Memory;
import hardware.memoria.Word;
import jdk.nashorn.tools.Shell;
import so.Escalonador;
import so.FilaBloqueados;
import so.FilaPedidosConsole;
import so.FilaProntos;
import so.MemoryManager;
import so.ProcessManager;
import util.Console;

public class VM {
	private static VM INSTANCE;
	public static int TAM_MEM;
	public static Word[] m;
	public static CPU cpu;
	public static ProcessManager pm;
	public static MemoryManager mm;
	public static FilaProntos fp;
	public static FilaBloqueados fb;
	public static FilaPedidosConsole fpc;
	public static InterruptHandling rotTrat;
	public static Escalonador escalonador;
	public static Semaphore semConsole;
	public static Shell shell;
	public static Console console;
	public static Semaphore semESC;
	public static Semaphore semCPU;
	public static TrapHandling trap;

	public VM() { // vm deve ser configurada com endereço de tratamento de interrupcoes

		// memória
		TAM_MEM = 1024;
		Memory.init(TAM_MEM);
		m = new Word[TAM_MEM]; // m ee a memoria
		console = new Console();
		// shell = new Shell();
		escalonador = new Escalonador();
		rotTrat = new InterruptHandling();
		fp = new FilaProntos();
		fb = new FilaBloqueados();
		fpc = new FilaPedidosConsole();
		trap = new TrapHandling();
		pm = new ProcessManager();
		mm = new MemoryManager();
		CPU cpu;
		semESC = new Semaphore(0);
		semCPU = new Semaphore(0);
		semConsole = new Semaphore(0);

		// cpu
		cpu = CPU.init();
		cpu.pc = 0; // Alterar
		cpu.start();
		//escalonador.start();
		// shell.start();
		trap.start();
		// console.start();

		Inicializa();
	}

	private void Inicializa() {

		Console.debug(" > VM.Inicializa() ");

		ProcessManager.init();
		Escalonador.init();

		pm = ProcessManager.get();
		escalonador = Escalonador.get();
	}

	/**
	 * Cria uma instância única para a classe VM.
	 */
	public static void init() {
		if (INSTANCE == null)
			INSTANCE = new VM();
	}

	/**
	 * @return instância única da VM.
	 */
	public static VM get() {
		return INSTANCE;
	}

}
