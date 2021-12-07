package so;

import hardware.memoria.Memory;
import hardware.memoria.Word;
import util.Console;

import java.util.LinkedList;
import java.util.Queue;

public class ProcessManager {
    private static ProcessManager INSTANCE;

    public MemoryManager mm;
    public static Queue<PCB> pcbList;
    public int processId = 0;

    public ProcessManager() {
        this.mm = new MemoryManager();
        this.pcbList = new LinkedList<>();
    }

    /**
     * 
     * @param p programa. Ex.: bubbleSort
     * @return referência para o novo processo criado 
     */
    public void create(Word[] p, String name) {            
    	Console.debug(" > ProcessManager.create()");
        PCB processControlBlock;

        //ESPAÇO DAS INSTRUÇÕES + DADOS
        int tam = p.length + 30;
        
        
        if (mm.temEspacoParaAlocar(tam)) {
            processControlBlock = new PCB(processId, mm.allocate(p), name);
            ++processId;

            pcbList.add(processControlBlock);
        } else {
            Console.error("Sem espaço na memória para criar o processo de ID:"+processId);
            processControlBlock = null;
        }
    }

    public void finish(PCB processo) {
        Console.debug(" > ProcessManager.finish()");
    	Console.debug(" > Memória com o programa "+processo.name+" finalizado");
		for (int i = 0; i < 250; i++) {

			System.out.print(i + ":");
			System.out.print("[ " + Memory.get().data[i].opc + "," + Memory.get().data[i].r1 + ", "
					+ Memory.get().data[i].r2 + ", " + Memory.get().data[i].p + " ] \n");

		}
        mm.unallocate(processo.getAllocatedPages());
        pcbList.remove(processo);
    }

    public PCB getProcess(int id) {
        if (pcbList.peek().getId() == id) {
            return pcbList.peek();
        } else {
            Console.error("Não foi possível encontrar o processo de ID:"+processId);
            return null;
        }
    }



    /**
     * Cria uma instância única para a classe ProcessManager.
     */
    public static void init() {
        if (INSTANCE == null) INSTANCE = new ProcessManager();
    }

    /**
     * @return instância única da ProcessManager.
     */
    public static ProcessManager get() {
        return INSTANCE;
    }
}
