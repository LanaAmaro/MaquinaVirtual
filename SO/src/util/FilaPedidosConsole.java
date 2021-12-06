package util;
/*
* Serve para organizar uma fila de process control block's com pedidos de IO.
* construido como uma fila do java, possui as funções de colocar na fila e de retirar da fila
* thread save, somente 1 process control block pode ser retirado ou colocado por vez (useFila)
*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import vm.VM;

public class FilaPedidosConsole {
    public Queue<ProcessControlBlock> filaPedidosConsole;
    private Semaphore useFila;

    //construtor da fila de Bloqueados
    public FilaPedidosConsole() {
        filaPedidosConsole = new LinkedList<ProcessControlBlock>();
        useFila = new Semaphore(1);
    }

    //coloca um processo no final da fila de pedidos console
    //caso useFila esteja com 1
    //libera useFila apos colocar PCB na fila
    public void colocaNaFilaPedidosConsole(ProcessControlBlock pcb) {
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
        for (ProcessControlBlock it : filaPedidosConsole) {
            aux = aux+" "+it;
        }
        return aux;
    }
}

