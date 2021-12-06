package so;

import vm.Interrupt;

import java.util.ArrayList;

public class PCB {
    public int id;
    public Interrupt interrupt;
    public ArrayList<Integer> allocatedPages;

    // CPU context
    public int pc;
    public Status status;
    public int[] reg;
	public String name;

    public PCB(int id, ArrayList<Integer> allocatedPages,String name) {
        this.allocatedPages = allocatedPages;
        this.id = id;
        this.interrupt = Interrupt.NONE;
        this.pc = 0;
        this.status = Status.READY;
        this.reg = new int[10];
        this.name = name;
    }

    //retorna a lista de paginas de um processo
    public ArrayList<Integer> getAllocatedPages() {
        return this.allocatedPages;
    }

    public int getId() {
        return this.id;
    }
    
    public void setPc(int valor) {
    	
    	this.pc = valor;
    }
    
    public int getPc() {
    	
    	return this.pc;
    }

}

enum Status {
    READY, BLOCKED, RUNNING
}
