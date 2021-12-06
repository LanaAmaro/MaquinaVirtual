package util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class FilaProntos {
    public Queue<ProcessControlBlock> filaProntos;
    private Semaphore useFila;

    //construtor da fila de prontos
    public FilaProntos(){
        filaProntos = new LinkedList<ProcessControlBlock>();
        useFila = new Semaphore(1);
    }

    //coloca um processo no final da fila de prontos
    //caso useFila esteja com 1
    //libera useFila apos colocar PCB na fila
    public void colocaNaFilaProntos(ProcessControlBlock pcb){
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
        for(ProcessControlBlock it: filaProntos){
            aux = aux+" "+it;
        }
        return aux;
    }
}