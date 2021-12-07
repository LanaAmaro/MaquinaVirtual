package so;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
public class FilaProntos {
    public Queue<PCB> filaProntos;
    private Semaphore useFila;

    //construtor da fila de prontos
    public FilaProntos(){
        filaProntos = new LinkedList<PCB>();
        useFila = new Semaphore(1);
    }

    //coloca um processo no final da fila de prontos
    //caso useFila esteja com 1
    //libera useFila apos colocar PCB na fila
    public void colocaNaFilaProntos(PCB pcb){
        try {            
            useFila.acquire();
        } catch (Exception e) {}
            filaProntos.add(pcb);
            useFila.release();
    }

    //retira o primeiro processo da fila de prontos
    //caso useFila esteja com 1
    //libera useFila apos retirar PCB na fila
    public void retiraDaFilaProntos(){
        try {
            useFila.acquire();
        } catch (Exception e) {}
            filaProntos.remove();        
            useFila.release();
    }

    //imprime a fila de prontos
    @Override
    public String toString(){
        String aux = "";
        for(PCB it: filaProntos){
            aux = aux+" "+it;
        }
        return aux;
    }
}

