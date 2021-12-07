package util;
import hardware.memoria.Word;

public class Auxi {
    
	public void dump(Word w) {
		System.out.print("[ " + w.opc + ", " + w.r1 + ", " + w.r2 + ", " + w.p + " ] \n");
	}
	
	public void dumpMemoria(Word[] m, int ini, int fim) {
		for (int i = ini; i < fim; i++) {
			System.out.print(i);
			System.out.print(":  ");
			dump(m[i]);
		}
	}
	
	public void cargaProgramaParaMemoria(Word[] prog, Word[] mem) {
		for (int i = 0; i < prog.length; i++) {
			mem[i].opc = prog[i].opc;
			mem[i].r1 = prog[i].r1;
			mem[i].r2 = prog[i].r2;
			mem[i].p = prog[i].p;
		}
	}

}