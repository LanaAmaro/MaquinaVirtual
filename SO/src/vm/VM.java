package vm;

import java.util.concurrent.Semaphore;
import hardware.cpu.CPU;
import hardware.cpu.Opcode;
import hardware.memoria.Memory;
import hardware.memoria.Word;
import so.Escalonador;
import so.MemoryManager;
import so.ProcessManager;
import util.Console;
import util.FilaProntos;

public class VM {
	public static int PAGE_SIZE;
	private static VM INSTANCE;
	public static Semaphore semConsole;
	public static int MEM_SIZE;
	public static Semaphore semESC;
	public static CPU cpu;
	public static ProcessManager pm;
	public static FilaProntos fp;
	public static Word[] m;
	public static MemoryManager mm;
	public Escalonador escalonador;
	
    public VM(){   // vm deve ser configurada com endereço de tratamento de interrupcoes
		PAGE_SIZE = 16; //colocar o tamanho da pagina	
		semConsole = new Semaphore(0);
		semESC = new Semaphore(0);

    	// memória
        MEM_SIZE = 1024;
		Memory.init(MEM_SIZE);
		m = new Word[MEM_SIZE]; // m ee a memoria
		pm = new ProcessManager();
		fp = new FilaProntos();
		mm = new MemoryManager();
        cpu = new CPU(m);

		//start nas trheads
		cpu.start();
		escalonador.start();

    }

	/**
	 * @return instância única da VM.
	 */
	public static VM get() {
		return INSTANCE;
	}


}
