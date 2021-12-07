package so;

import hardware.memoria.Memory;
import hardware.memoria.Word;
import util.Console;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import vm.VM;

public class ProcessManager {
	private static ProcessManager INSTANCE;

	public MemoryManager mm;
	public static Queue<PCB> pcbList;
	public int processId = 0;
	private static int contProcessos = 0;
	private int idPCB;
	private Semaphore useList;

	public ProcessManager() {
		this.mm = new MemoryManager();
		this.pcbList = new LinkedList<>();
		useList = new Semaphore(1);
	}

	/**
	 * 
	 * @param p programa. Ex.: bubbleSort
	 * @return referência para o novo processo criado
	 * @throws InterruptedException 
	 */
	public void create(Word[] p, String name) throws InterruptedException {
		
		useList.acquire();
		
		Console.debug(" > ProcessManager.create()");
		PCB processControlBlock;

		// ESPAÇO DAS INSTRUÇÕES + DADOS
		int tam = p.length + 30;

		if (mm.temEspacoParaAlocar(tam)) {
			processControlBlock = new PCB(processId, mm.allocate(p), name);
			++processId;

			pcbList.add(processControlBlock);
			VM.fp.colocaNaFilaProntos(processControlBlock);
		
		} else {
			Console.error("Sem espaço na memória para criar o processo de ID:" + processId);
			processControlBlock = null;
		}
		
		useList.release();
	}

	public void finish(PCB processo) {
		Console.debug(" > ProcessManager.finish()");
		Console.debug(" > Memória com o programa " + processo.name + " finalizado");
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
			Console.error("Não foi possível encontrar o processo de ID:" + processId);
			return null;
		}
	}

	/**
	 * Cria uma instância única para a classe ProcessManager.
	 */
	public static void init() {
		if (INSTANCE == null)
			INSTANCE = new ProcessManager();
	}

	/**
	 * @return instância única da ProcessManager.
	 */
	public static ProcessManager get() {
		return INSTANCE;
	}

	// verifica se existe espaco em memoria para o programa, caso nao exista informa
	// ao usuario
	// caso exista, cria um process control block com um id, e adiciona o mesmo na
	// lista de process control block do sistema
	// libera o escalonador para escalonar
	// ao terminar libera o useList
	/*public void alocaPCB(Programas p) {
		try {
			useList.acquire();
		} catch (Exception e) {
		}
		if (VM.mm.temEspacoParaAlocar(p.getTamanhoProg())) {
			idPCB = contProcessos;
			pcbList.add(new PCB(idPCB));
			for (PCB it : pcbList) {
				if (idPCB == it.getId()) {
					it.addPaginas(VM.mm.allocate(p.getLogica()));
					it.setNome(p.getNome());
					VM.fp.colocaNaFilaProntos(it);
					contProcessos++;
					VM.semESC.release();
					break;
				}
			}
		} else {
			System.out.println("Sem Espaco na memoria para novos programas");
		}
		useList.release();
	}

	// recebe o id de um processo, verifica a existencia do mesmo
	// pede para o gerente de memmoria desalocar esse processo da memoria
	// ao terminar libera o useList
	public void desalocaPCB(int ID) {
		try {
			useList.acquire();
		} catch (Exception e) {
		}
		int cont = -1;
		for (int i = 0; i < pcbList.size(); i++) {
			if (pcbList.get(i).getId() == ID) {
				VM.mm.unallocate(pcbList.get(i).getLista());
				cont = i;
				break;
			}
		}
		if (cont >= 0) {
			pcbList.remove(cont);
			cont = -1;
		}
		useList.release();
	}*/

}
