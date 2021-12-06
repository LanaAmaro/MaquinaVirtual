package so;

import hardware.memoria.Word;
import util.Console;
import util.ProcessControlBlock;
import vm.VM;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProcessManager {
    public ArrayList<ProcessControlBlock> pcbList;
    private static ProcessManager INSTANCE;
    public MemoryManager mm;
    public int processId = 0;
    private Semaphore useList;

    public ProcessManager() {
        pcbList = new ArrayList<ProcessControlBlock>();
        useList = new Semaphore(1);
    }

    //verifica se existe espaco em memoria para o programa, caso nao exista informa ao usuario
    //caso exista, cria um process control block com um id, e adiciona o mesmo na lista de process control block do sistema
    //libera o escalonador para escalonar
    //ao terminar libera o useList
    public void alocaPCB(Programas p){
        try {
            useList.acquire();
        } catch (Exception e) {}
            if(VM.pm.verificaEspaco(p.getTamanhoProg())){
                idPCB = contProcessos;
                pcbList.add(new ProcessControlBlock(idPCB));
                for(ProcessControlBlock it: pcbList){
                    if(idPCB == it.getId()){
                        it.addPaginas(VM.gm.alocacao(p.getLogica(), p.getTamanhoProg()));
                        it.setNome(p.getNome());
                        VM.fp.colocaNaFilaProntos(it);
                        contProcessos++;
                        VM.semESC.release();
                        break;
                    }
                }
            }
            else{
                System.out.println("Sem Espaco na memoria para novos programas");
            }
            useList.release();
    }

    //recebe o id de um processo, verifica a existencia do mesmo
    //pede para o gerente de memmoria desalocar esse processo da memoria
    //ao terminar libera o useList
    public void desalocaPCB(int ID){
        try {
            useList.acquire();
        } catch (Exception e) {}
            int cont = -1;
            for(int i=0; i<pcbList.size(); i++){
                if(pcbList.get(i).getId() == ID){                
                    VM.gm.desaloca(pcbList.get(i).getLista());
                    cont = i;
                    break;
                }
            }
            if(cont >= 0){
                pcbList.remove(cont);
                cont = -1;
            }
            useList.release();
    }
}
