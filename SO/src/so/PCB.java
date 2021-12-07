package so;

import vm.Interrupt;

import java.util.ArrayList;

public class PCB {
	
    public int id;
    public Interrupt interrupt;
    public ArrayList<Integer> allocatedPages;
    public int pc;
    public StatusPCB status;
    public int[] reg;
	public String name;
    public int nVezesCPU;
    public Interrupt irpt;
    public int valorEscritaLeitura;
    public int posicaoEscritaLeitura;

    public PCB(int id,ArrayList<Integer> allocatedPages,String name) {
    	
        this.allocatedPages = allocatedPages;
        this.id = id;
        this.irpt = Interrupt.noInterrupt;
        this.pc = 0;
        this.status = StatusPCB.READY;
        this.nVezesCPU = 0;
        this.valorEscritaLeitura = 0;
        this.posicaoEscritaLeitura = 0;
        this.reg = new int[10];
        this.name = name;
    }

        //adiciona paginas a um processo existente
        public void addPaginas(ArrayList<Integer> paginas){
            this.allocatedPages = paginas;
        }

        //recebe e modifica o status de um processo
        public void setIrtp(Interrupt irtp){
            this.irpt = irtp;
        }

        //recebe uma interrupt e seta no irtp
        public void setStatus(StatusPCB status){
          this.status = status;
        }


    //recebe e modifica um vetor de registradores de um processo
    public void setRegistradores(int[] registradores){
        this.reg = registradores;
    }

    //recebe e modifica o pc de um processo
    public void setPc(int pc){
        this.pc = pc;
    }

    //recebe o nome de um processo
    public void setNome(String name){
        this.name = name;
    }

    //adiciona 1 ao numero de vezes que o processo passou pela cpu
    public void setNumVezesCpu(){
        this.nVezesCPU++;
    }
    //recebe o valor de escritaLeitura
    public void setValorEscritaLeitura(int escritaLeitura){
        this.valorEscritaLeitura = escritaLeitura;
    } 

    //recebe a posicao de escrita ou leitura
    public void setPosicaoEscritaLeitura(int posicaoEscritaLeitura){
        this.posicaoEscritaLeitura = posicaoEscritaLeitura;
    }

    //retorna a lista de paginas de um processo
    public ArrayList<Integer> getLista(){
        return this.allocatedPages;
    }

    //retorna a interrupt de um processo
    public Interrupt getIrtp(){
        return this.irpt;
    }

    //retorna a lista de registradores do processo
    public int[] getReg(){
        return this.reg;
    }

    //retorna a lista de paginas de um processo
    public ArrayList<Integer> getAllocatedPages() {
        return this.allocatedPages;
    }

    public int getId() {
        return this.id;
    }
    
    
    public int getPc() {
    	
    	return this.pc;
    }

        
    //retorna o valor de escritaLeitura
    public int getValorEscritaLeitura(){
        return this.valorEscritaLeitura;
    } 

    //retorna a posicao de escrita ou leitura
    public int getPosicaoEscritaLeitura(){
        return this.posicaoEscritaLeitura;
    }

    //retorna o status de um processo
    public StatusPCB getStatus(){
        return this.status;
    }

    //retorna o nome de um processo
    public String getNome(){
        return this.name;
    }

    //retorna o numero de vezes que o processo passou pela cpu
    public int getNumVezesCPU(){
        return this.nVezesCPU;
    }

    //imprime o id, pc, estado e paginas de um processo
    @Override
    public String toString(){
        String identi = "id = "+this.id + " ";
        String estadoPc = "pc = "+this.pc + " ";
        String status = "estado = "+this.status + " ";
        String aux = "";
        for(Integer it: allocatedPages){
            aux = aux+" "+it;
        }
        return identi+estadoPc+status+aux;
    }
}

