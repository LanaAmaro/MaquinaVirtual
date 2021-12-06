package so;

import hardware.cpu.Opcode;
import hardware.memoria.Word;

public class LogicaProgramas implements Software {
	
	 public int result = 0;

     public static Word[] progMinimo = new Word[] {
             new Word(Opcode.LDI, 0, -1, 999),
             new Word(Opcode.STD, 0, -1, 10),
             new Word(Opcode.STD, 0, -1, 11),
             new Word(Opcode.STD, 0, -1, 12),
             new Word(Opcode.STD, 0, -1, 13),
             new Word(Opcode.STD, 0, -1, 14),
             new Word(Opcode.STOP, -1, -1, -1) };


     public static Word[] fibonacci10 = new Word[] { // mesmo que prog exemplo, so que usa r0 no lugar de r8
             new Word(Opcode.LDI, 1, -1, 0),
             new Word(Opcode.STD, 1, -1, 20), //50
             new Word(Opcode.LDI, 2, -1, 1),
             new Word(Opcode.STD, 2, -1, 21), //51
             new Word(Opcode.LDI, 0, -1, 22), //52
             new Word(Opcode.LDI, 6, -1, 6),
             new Word(Opcode.LDI, 7, -1, 31), //61
             new Word(Opcode.LDI, 3, -1, 0),
             new Word(Opcode.ADD, 3, 1, -1),
             new Word(Opcode.LDI, 1, -1, 0),
             new Word(Opcode.ADD, 1, 2, -1),
             new Word(Opcode.ADD, 2, 3, -1),
             new Word(Opcode.STX, 0, 2, -1),
             new Word(Opcode.ADDI, 0, -1, 1),
             new Word(Opcode.SUB, 7, 0, -1),
             new Word(Opcode.JMPIG, 6, 7, -1),
             new Word(Opcode.STOP, -1, -1, -1)
     };


     // PROGRAMA 1 - Calculo de Fibonacci
     public static Word[] progCalculoDeFibonacci = new Word[] {
     /*0*/ new Word(Opcode.LDI,8,-1, 1),  
     /*1*/ new Word(Opcode.LDI,9,-1, 40),  
     /*2*/ new Word(Opcode.TRAP,8,-1,1),	 
     /*3*/ new Word(Opcode.LDD,0,-1, 40),    // carregar para R0 o valor da quantidade de elementos da Seq. de Fibonacci, o qual estava gravado na posicao 100 da memoria
     /*4*/ new Word(Opcode.LDI,5,-1,41),  	// Proxima posição de memoria para armazenamento dos termos calculados
     /*5*/ new Word(Opcode.LDI,6,-1, 30),    // Posição da trativa de valor menor que 0
     /*6*/ new Word(Opcode.JMPIL,6,0,-1),   // Se R0 < 0, salta para a posição de memoria defina em R6
     /*7*/ new Word(Opcode.LDI,6,-1,31),    // Posição da trativa de valor IGUAL a 0
     /*8*/ new Word(Opcode.JMPIE,6,0,-1),   // Se R0 = 0, salta para a posição de memoria defina em R6
     /*9*/ new Word(Opcode.ADD,4,0,-1),     // R4 = R0, R4 é o contador do programa para os termos já calculados
     /*10*/ new Word(Opcode.LDI,1,-1,0),		// primeiro valor da sequencia
     /*11*/ new Word(Opcode.STX,5,1,-1),	    // a partir de 101 estarão números da seq. de fibonacci
     /*12*/ new Word(Opcode.ADDI,5,-1,1),   // proximo endereco a armazenar proximo numero
     /*13*/ new Word(Opcode.LDI,2,-1,1),	// segundo valor da sequencia
     /*14*/ new Word(Opcode.STX,5,2,-1),
     /*15*/ new Word(Opcode.ADDI,5,-1,1),   // proximo endereco a armazenar proximo numero
     /*16*/ new Word(Opcode.SUBI,4,-1,1),	// Decrementa o contador
     /*17*/ new Word(Opcode.LDI,7,-1,37),	// Posição para o final do programa (COMANDO STOP)
     /*18*/ new Word(Opcode.JMPIE,7,4,-1),	// valida se o contador chegou a ZERO, indicando que já processou todos os termos do fatorial e salta para a posição de memoria defina em R6
     /*19*/ new Word(Opcode.LDI,6,-1,20),	// 17 é posição de memoria do inicio do loop
     /*20*/ new Word(Opcode.LDI,3,-1,0),	// zera R3
     /*21*/ new Word(Opcode.ADD,3,1,-1),	// R3 =+R1, ou seja R3 = R3 + R1
     /*22*/ new Word(Opcode.LDI,1,-1,0),	// zera R1
     /*23*/ new Word(Opcode.ADD,1,2,-1),	// adiciona R2 em R1, ou seja, R1 = R2
     /*24*/ new Word(Opcode.ADD,2,3,-1),	// adiciona em R2 valor de R3 (R2 é o novo valor da serie)
     /*25*/ new Word(Opcode.STX,5,2,-1),	// salva na posição de memoria apontada por R5, o resultado do fatorial (R2)
     /*26*/ new Word(Opcode.ADDI,5,-1,1),	// R5 tem nova posição a armazenar valor no proximo loop
     /*27*/ new Word(Opcode.SUBI,4,-1,1),	// Decrementa o contador
     /*28*/ new Word(Opcode.LDI,7,-1,37),	// Posição para o final do programa (COMANDO STOP)
     /*29*/ new Word(Opcode.JMPIE,7,4,-1),	// valida se o contador chegou a ZERO, indicando que já processou todos os termos de fibonacci e salta para a posição de memoria defina em R6
     /*30*/ new Word(Opcode.JMPIG,6,4,-1), 	// R4 > 0, se R4(contador) for maior que zero jump para R6 (início do loop)
     /*31*/ new Word(Opcode.LDI,7,-1,-1),	// Numero escolhido é menor que ZERO, retorna  -1
     /*32*/ new Word(Opcode.STX,5,7,-1),	// Então grava na proxima posição o valor de retorno -1
     /*33*/ new Word(Opcode.JMP,-1,-1,37),	// salta para o final do programa (comando STOP)
     /*34*/ new Word(Opcode.LDI,7,-1,0),	// Numero escolhido é IGUAL a ZERO, retorna  0
     /*35*/ new Word(Opcode.STX,5,7,-1),	// Então grava na proxima posição o valor de retorno 0
     /*36*/ new Word(Opcode.JMP,-1,-1,37),	// salta para o final do programa (comando STOP)
     /*37*/ new Word(Opcode.STOP,-1,-1,-1)	// FINALIZA O PROGRAMA
     };


     // PROGRAMA 2 - Calcula de Fatorial
     public static Word[] progCalculaFatorial = new Word[] {
      /*0*/ new Word(Opcode.LDI,8,-1, 1),  
      /*1*/ new Word(Opcode.LDI,9,-1, 30),  
      /*2*/ new Word(Opcode.TRAP,8,-1,1),	
      /*3*/ new Word(Opcode.LDD, 0, -1, 30),   // carregar para R0 o valor para calcular o fatorial, o qual estava gravado na posicao 50 da memoria
      /*4*/ new Word(Opcode.LDI, 5, -1, 31),   // Proxima posição de memoria para armazenamento dos termos calculados
      /*5*/ new Word(Opcode.LDI, 6, -1, 23),    // Posição da trativa de valor menor que 0
      /*6*/ new Word(Opcode.JMPIL, 6, 0, -1),   // Se R0 < 0, salta para a posição de memoria defina em R6
      /*7*/ new Word(Opcode.LDI, 6, -1, 25),    // Posição da trativa de valor igual a 0
      /*8*/ new Word(Opcode.JMPIE, 6, 0, -1),   // Se R0 = 0, salta para a posição de memoria defina em R6
      /*9*/ new Word(Opcode.ADD, 1, 0, -1),     // R1 = R0, R1 é o contador do programa para os termos já calculados
      /*10*/ new Word(Opcode.LDI, 2, -1, 1),     // 1 termo do fatorial
      /*11*/ new Word(Opcode.LDI, 3, -1, 1),     // 2 termo do fatorial
      /*12*/ new Word(Opcode.MULT, 2, 3, -1),    // R2 = R2 * R3, termos do fatorial
      /*13*/ new Word(Opcode.SUBI, 1, -1, 1),    // Decrementa o contador
      /*14*/ new Word(Opcode.LDI, 4, -1, 27),    // Posição para o final do programa (COMANDO STOP)
      /*15*/ new Word(Opcode.JMPIE, 4, 1, -1),   // valida se o contador chegou a ZERO, indicando que já processor todos os termos do fatorial
      /*16*/ new Word(Opcode.LDI, 6, -1, 17),    // 15 é posição de mem do inicio do loop
      /*17*/ new Word(Opcode.LDI, 7, -1, 100),   // final, restaura em R7 o valor final da memoria
      /*18*/ new Word(Opcode.ADDI, 3, -1, 1),    // Pega o proximo termo para calcular o fatorial
      /*19*/ new Word(Opcode.MULT, 2, 3, -1),    // R2 = R2 * R3, calcula o proximo termo do fatorial
      /*20*/ new Word(Opcode.SUBI, 1, -1, 1),    // Decrementa o contador
      /*21*/ new Word(Opcode.LDI, 4, -1, 27),    // Posição para o final do programa (COMANDO STOP)
      /*22*/ new Word(Opcode.JMPIE, 4, 1, -1),   // valida se o contador chegou a ZERO, indicando que já processou todos os termos do fatorial
      /*23*/ new Word(Opcode.JMPIG, 6, 1, -1),   // se R1(contador) for maior que zero salta para R6 (início do loop)
      /*24*/ new Word(Opcode.LDI, 2, -1, -1),    // Numero escolhido é menor que ZERO, retorna  -1
      /*25*/ new Word(Opcode.JMP, -1, -1, 27),   // salta para o final do programa (comando STOP)
      /*26*/ new Word(Opcode.LDI, 2, -1, 1),     // Numero escolhido é IGUAL a ZERO, retorna 1
      /*27*/ new Word(Opcode.JMP, -1, -1, 28),   // salta para o final do programa (comando STOP)
      /*28*/ new Word(Opcode.STX, 5, 2, -1),     // salva na posição de memoria apontada por R5, o resultado do fatorial (R2)
      /*29*/ new Word(Opcode.STOP, -1, -1, -1)   // FINALIZA O PROGRAMA
     };


     // PROGRAMA 3 - Ordenação de Vetores com Bubble Sort
     public static Word[] progOrdenacaoDeVetoresComBubbleSort = new Word[] {
    	     
    		    /*0*/ new Word(Opcode.LDI,8,-1, 1),  
    	        /*1*/ new Word(Opcode.LDI,9,-1, 32),  
    	        /*2*/ new Word(Opcode.TRAP,8,-1,1),	
    	        
    	        /*3*/ new Word(Opcode.LDX,6,9,-1),
    	        /*4*/ new Word(Opcode.ADDI,9,-1,1),
    	        /*5*/ new Word(Opcode.TRAP,8,-1,1),
    	        /*6*/ new Word(Opcode.SUBI,6,-1,1),
    	        /*7*/ new Word(Opcode.JMPIEM,-1,6,9),
    	        /*8*/ new Word(Opcode.JMP,-1,-1,4),
    		 
    			/*9*/  new Word(Opcode.LDD,5,-1,32),
        		/*10*/ new Word(Opcode.SUBI,5,-1,2), 

        		/*11*/ new Word(Opcode.LDD,0,-1,32),
        		/*12*/ new Word(Opcode.LDI,1,-1,33),   
        		/*13*/ new Word(Opcode.LDI,2,-1,34),    
        		/*14*/ new Word(Opcode.LDX,3,1,-1),	   
        		/*15*/ new Word(Opcode.LDX,4,2,-1),
        		/*16*/ new Word(Opcode.SUBI,0,-1,2), 
        		/*17*/ new Word(Opcode.SWAP,3,4,-1), 
        		/*18*/ new Word(Opcode.STX,1,3,-1),
        		/*19*/ new Word(Opcode.STX,2,4,-1),

        		/*20*/ new Word(Opcode.JMPIEM,-1,0,28),

        		/*21*/ new Word(Opcode.LDX,3,2,-1),
        		/*22*/ new Word(Opcode.ADDI,1,-1,1),
        		/*23*/ new Word(Opcode.ADDI,2,-1,1),
        		/*24*/ new Word(Opcode.LDX,4,2,-1),
        		/*25*/ new Word(Opcode.SUBI,0,-1,1),
        		/*26*/ new Word(Opcode.SWAP,3,4,-1), 
        		/*27*/ new Word(Opcode.JMP,-1,-1,18),
        		
        		/*28*/ new Word(Opcode.JMPIEM,-1,5,31),
        		/*29*/ new Word(Opcode.SUBI,5,-1,1),
        		/*30*/ new Word(Opcode.JMP,-1,-1,11),
        		/*31*/ new Word(Opcode.STOP,-1,-1,-1),                

     };
     
     public static Word[] testeTrap = new Word[] {

     		/*0*/ new Word(Opcode.LDI,8,-1, 1),       //Coloca 1 no reg 8 (leitura)
     		/*1*/ new Word(Opcode.LDI,9,-1, 50),      //Coloca posição de memória 
     		/*2*/ new Word(Opcode.TRAP,8,9,-1),
     		/*3*/ new Word(Opcode.LDI,0,-1,2021),
     		/*4*/ new Word(Opcode.STD,0,-1,51),
     		/*5*/ new Word(Opcode.LDI,8,-1, 2),
     		/*6*/ new Word(Opcode.LDI,9,-1, 51),
     		/*7*/ new Word(Opcode.TRAP,8,9,-1),
     		/*8*/ new Word(Opcode.STOP,-1,-1,-1),
    	 		 
     };

     public static Word[] trapIn = new Word[]{
             new Word(Opcode.LDI, 8, -1, 1),
             new Word(Opcode.LDI, 9, -1, 4),
             new Word(Opcode.TRAP, -1, -1, -1),
             new Word(Opcode.STOP, -1, -1, -1)
     };

     public static Word[] trapOut = new Word[]{
             new Word(Opcode.LDI, 0, -1, 999),
             new Word(Opcode.STD, 0, -1, 10),
             new Word(Opcode.LDI, 8, -1, 2),
             new Word(Opcode.LDI, 9, -1, 10),
             new Word(Opcode.TRAP, -1, -1, -1),
             new Word(Opcode.STOP, -1, -1, -1)
     };
}
