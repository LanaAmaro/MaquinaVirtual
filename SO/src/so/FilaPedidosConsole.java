package so;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import vm.VM;

public class FilaPedidosConsole {
    public Queue<PCB> filaPedidosConsole;
    private Semaphore useFila;

    //construtor da fila de Bloqueados
    public FilaPedidosConsole() {
        filaPedidosConsole = new LinkedList<PCB>();
        useFila = new Semaphore(1);
    }

    //coloca um processo no final da fila de pedidos console
    //caso useFila esteja com 1
    //libera useFila apos colocar PCB na fila
    public void colocaNaFilaPedidosConsole(PCB pcb) {
        try {
            useFila.acquire();
        } catch (Exception e) {}
            filaPedidosConsole.add(pcb);
            VM.semConsole.release();
            useFila.release();
    }

    //retira um processo do inicio da fila de pedidos console
    //caso useFila esteja com 1
    //libera useFila apos colocar PCB na fila
    public void retiraDaFilaPedidosConsole() {
        try {
            useFila.acquire();
        } catch (Exception e) {}
            filaPedidosConsole.remove();
            useFila.release();
    }

    // imprime a fila de pedidos console
    @Override
    public String toString() {
        String aux = "";
        for (PCB it : filaPedidosConsole) {
            aux = aux+" "+it;
        }
        return aux;
    }
}

