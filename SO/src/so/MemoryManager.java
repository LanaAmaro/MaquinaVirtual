package so;

import hardware.cpu.Opcode;
import hardware.memoria.Memory;
import hardware.memoria.Word;
import util.ProcessControlBlock;
import vm.VM;

import java.util.ArrayList;

public class MemoryManager {
    private int nPagi;
    public static boolean[] availableFrames;


    //contrutor do gerente de memoria, 
    //ao ser iniciado cria os frames a partir do tamanho da memoria / pelo tamanho da pagina, 
    //inicia todos os frames com true ou false
    public MemoryManager() {
        for (int i = 0; i < VM.m.length; i++) {
			VM.m[i] = new Word(Opcode.___, -1, -1, -1);
		};
        availableFrames = new boolean[(VM.MEM_SIZE / VM.PAGE_SIZE)];
        for (int i = 0; i < availableFrames.length; i++) {
            if(i%2 == 0){
                availableFrames[i] = true;  // false = ocupado || true = livre
            }else{
                availableFrames[i] = true;
            }        
        }
    }

        //verifica se existe espaco em memoria para alocar o programa informado pelo gerente de processos
        public boolean verificaEspaco(int tamProg){
            int aux;
            if(tamProg%VM.PAGE_SIZE == 0){
                aux = ((tamProg/VM.PAGE_SIZE));
            }
            else{
                aux = ((tamProg/VM.PAGE_SIZE)+1);
            }
            int cont = 0;       
            for(int i=0; i<availableFrames.length; i++){
                if(availableFrames[i] == true){
                    cont++;
                }
            }
            if(cont >= aux){
                return true;
            }
            return false;             
        }

        //ao receber um programa, calcula o tamanho dele e aloca em memoria
        //retorna as paginas que esse programa irá usar em memoria
    public ArrayList<Integer> alocacao(Word[] p, int tamProg) {
        if(tamProg%VM.PAGE_SIZE == 0){
            nPagi = ((tamProg/VM.PAGE_SIZE));
        }
        else{
            nPagi = ((tamProg/VM.PAGE_SIZE)+1);
        }
        int cont = 0;
        int posProg = 0;

        ArrayList <Integer> paginas = new ArrayList<>();
        for(int i=0; i<availableFrames.length; i++){
            if(availableFrames[i] == true){
                cont++;
                availableFrames[i] = false;
                paginas.add(i);
                for (int j=(i*VM.PAGE_SIZE); j<(i+1)*VM.PAGE_SIZE; j++) {
                    if(posProg < p.length){
                        VM.m[j].opc = p[posProg].opc;
                        VM.m[j].r1 = p[posProg].r1;
                        VM.m[j].r2 = p[posProg].r2;
                        VM.m[j].p = p[posProg].p;
                        posProg++;
                    }
                    else{
                        break;
                    }
                }
            }
            if(cont == nPagi){ 
                return paginas;
            }
        }    
        return null;
    }

    //recebe uma lista de paginas de um processo do gerente de processos
    //e desaloca esse programa da memoria
    public void desaloca(ArrayList<Integer> paginas){
        for(Integer p: paginas){
            for(int j=0; j<availableFrames.length; j++){
                if(p == j){
                    availableFrames[j] = true;
                    for (int k=(j*VM.PAGE_SIZE); k<(j+1)*VM.PAGE_SIZE; k++) {
                        VM.m[k] = new Word(Opcode.___, -1, -1, -1);
                    }
                }
            }
        }
    }

    //formata a impressao do dump de memoria
    public void dump(Word w) {
    System.out.print("[ " + w.opc + ", " + w.r1 + ", " + w.r2 + ", " + w.p + " ] " + "\n");
    }

    //imprime umm dump da memoria, na posicao ini até o fim informado por parametro
    public void dump(int ini, int fim) {
        for (int i = ini; i < fim; i++) {
            System.out.print(i + ": ");
            dump(VM.m[i]);
        }
    }

    //recebe o id de um processo e imprime esse processo com as informacoes do numero de vezes que passou pela cpu
    //o nome e a causa da interrupcao do processo
    public void dump(int id) {
        for(ProcessControlBlock it: VM.pm.pcbList){
            if(it.getId() == id){
                System.out.println("\nPrograma "+it.getNome()+", ID = "+it.getId()+", Numero de vezes que passou na CPU = "+it.getNumVezesCPU()+", Causa da interrupcao = "+it.getIrtp());
                for(Integer p: it.getLista()){
                    for(int j=0; j<availableFrames.length; j++){
                        if(p == j){
                            for (int k=(j*VM.PAGE_SIZE); k<(j+1)*VM.PAGE_SIZE; k++) {
                                System.out.print(k + ": ");
                                dump(VM.m[k]);
                            }
                        }
                    }
                }
            }
        }        
    }
}



