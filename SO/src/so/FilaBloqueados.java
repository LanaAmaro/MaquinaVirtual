package so;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class FilaBloqueados {
    public List<PCB> filaBloqueados;
    private Semaphore useFila;

    //construtor da fila de Bloqueados
    public FilaBloqueados() {
        filaBloqueados = new ArrayList<PCB>();
        useFila = new Semaphore(1);
    }

    //coloca um processo na fila de bloqueados
    //caso useFila esteja com 1
    //libera useFila apos colocar PCB na fila de bloqueados
    public void colocaNaFilaBloqueados(PCB pcb){
        try {            
            useFila.acquire();
        } catch (Exception e) {}
            filaBloqueados.add(pcb);
            useFila.release();
    }

    //retira um processo da fila de bloqueados atraves do ID
    //caso useFila esteja com 1
    //libera useFila apos colocar PCB na fila de bloqueados
    public void retiraDaFilaBloqueados(int ID){
        try {
            useFila.acquire();
        } catch (Exception e) {}
            int cont = -1;
            for(int i=0; i<filaBloqueados.size(); i++){
                if(filaBloqueados.get(i).getId() == ID){
                    cont = i;
                    break;
                }
            }
            if(cont >= 0){
                filaBloqueados.remove(cont);
                cont = -1;
            }
            useFila.release();
    }

    //imprime a fila de Bloqueados
    @Override
    public String toString(){
        String aux = "";
        for(PCB it: filaBloqueados){
            aux = aux+" "+it;
        }
        return aux;
    }
}
