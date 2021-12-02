package hardware.cpu;

import hardware.memoria.Word;

import util.Console;

import hardware.Hardware;
import hardware.memoria.Memory;
import so.MemoryManager;

import vm.InterruptHandling;
import vm.Interrupt;
import vm.TrapHandling;

import java.util.ArrayList;
import java.util.List;

public class CPU extends Thread implements Hardware {
	
    // característica do processador: contexto da CPU ...
    public int pc; 			// ... composto de program counter,
    public Word ir; 		// instruction register,
    public int[] registradores;       	// registradores da CPU

    private Word[] m;   // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.

    public Memory memory; // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.
    public Interrupt interrupt;

    public final int CLOCK = 50; // Tempo em milissegundos para a execução de cada instrução

    private List<Integer> programPages;
    private int id;
    private MemoryManager memoryManager = new MemoryManager();


    public CPU(Word[] _m) {     // ref a MEMORIA e interrupt handler passada na criacao da CPU
        m = _m; 				// usa o atributo 'm' para acessar a memoria.
        registradores = new int[10]; 		// aloca o espaço dos registradores reg = new int[8];
                                // FASE 3: inclusão de mais 2 registros, para a operação TRAP: reg = new int[10];
    }

    public CPU() {
        this.memory = Memory.get();     // usa o atributo 'memory' para apontar para o atributo 'memory' da VM.
        this.registradores = new int[10];
    }

    public void setContext(ArrayList<Integer> paginas, int pc, int id, int[] reg) {
        interrupt = Interrupt.NONE;

        this.programPages = paginas;
        this.pc = pc;
        this.id = id;
        this.registradores = reg;
    }

    /*public void showState(){
        System.out.println("       "+ pc);
        System.out.print("           ");
        for (int i=0; i<10; i++) {
            System.out.print("r"+i);
            System.out.print(": "+registradores[i]+"     ");
        };
        System.out.println("");
        System.out.print("           ");
        //aux.dump(ir);
    }*/

    public void run() { 		// execucao da CPU supoe que o contexto da CPU, vide acima, esta devidamente setado

        Console.debug(" > CPU.run() ");

        while (interrupt == Interrupt.NONE) {// ciclo de instrucoes. acaba cfe instrucao, veja cada caso.

            // FETCH
            ir = memory.data[translate(pc)]; 	// busca posicao da memoria apontada por pc, guarda em ir

            //if debug
            //showState();
            switch (ir.opc) { // EXECUTA INSTRUCAO NO ir - para cada opcode, sua execução



//--------------------------------------------------------------------------------------------------
//Instruções Aritméticas
// --------------------------------------------------------------------------------------------------
                case ADD: // Rd ← Rd + Rs
                    try {
                        registradores[ir.r1] = Math.addExact(registradores[ir.r1],registradores[ir.r2]);
                        pc++;
                    } catch (ArithmeticException sum) {
                        //System.out.println ("Overflow! ");
                        interrupt = Interrupt.OVERFLOW;
                    }
                    break;

                case MULT: // Rd ← Rd * Rs
                    try {
                        registradores[ir.r1] = Math.multiplyExact (registradores[ir.r1],registradores[ir.r2]);
                        pc++;
                    } catch (ArithmeticException mult) {
                        //System.out.println ("Overflow! ");
                        interrupt = Interrupt.OVERFLOW;
                    }
                    break;

                case ADDI: // Rd ← Rd + k
                    try {
                        registradores[ir.r1] = Math.addExact(registradores[ir.r1],ir.p);
                        pc++;
                    } catch (ArithmeticException sum) {
                        //System.out.println ("Overflow! ");
                        interrupt = Interrupt.OVERFLOW;
                    }
                    break;


                case SUB: // Rd ← Rd - Rs
                    try {
                        registradores[ir.r1] = Math.subtractExact(registradores[ir.r1],registradores[ir.r2]);
                        pc++;
                    } catch (ArithmeticException sub) {
                        //System.out.println ("Overflow! ");
                        interrupt = Interrupt.OVERFLOW;
                    }
                    break;

                case SUBI: // Rd ← Rd + k
                    try {
                        registradores[ir.r1] = Math.subtractExact(registradores[ir.r1],ir.p);
                        pc++;
                    } catch (ArithmeticException sub) {
                        //System.out.println ("Overflow! ");
                        interrupt = Interrupt.OVERFLOW;
                    }
                    break;
// --------------------------------------------------------------------------------------------------

// --------------------------------------------------------------------------------------------------
//Instruções JUMP
// --------------------------------------------------------------------------------------------------
                case JMP: //  PC ← k
                    if((ir.p < 0) || (ir.p > 1023)){
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    else {
                        pc = ir.p;
                    }
                    break;

                case JMPI: // PC ← Rs
                    if((ir.p < 0) || (ir.p > 1023)){
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    else {
                        if ((registradores[ir.r1] < 0) || (registradores[ir.r1] > 1023)) {
                            interrupt = Interrupt.INVALID_ADDRESS;
                        } else {
                            pc = registradores[ir.r1];
                        }
                    }
                    break;

                case JMPIG: // If Rc > 0 Then PC ← Rs Else PC ← PC +1
                    // se Rs < 0 ou Rs > 1024, salta para interrupção de endereço invalido
                    if((registradores[ir.r1] < 0) || (registradores[ir.r1] > 1023)){
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    else {
                        // se Rs > 0
                        if ((registradores[ir.r2] > 0)) {
                            pc = registradores[ir.r1];
                        }
                        else {
                            pc++;
                        }
                    }
                    break;

                case JMPIL: // If Rc < 0 then PC ← Rs Else PC ← PC +1
                    // se Rs < 0 ou Rs > 1024, salta para interrupção de endereço invalido
                    if((registradores[ir.r1] < 0) || (registradores[ir.r1] > 1023)){
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    else {
                        if (registradores[ir.r2] < 0) {
                            pc = registradores[ir.r1];
                        }
                        else {
                            pc++;
                        }
                    }
                    break;

                case JMPIE: // If Rc = 0 Then PC ← Rs Else PC ← PC +1
                    // se Rs < 0 ou Rs > 1024, salta para interrupção de endereço invalido
                    if((registradores[ir.r1] < 0) || (registradores[ir.r1] > 1023)){
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    else {
                        if (registradores[ir.r2] == 0) {
                            pc = registradores[ir.r1];
                        } else {
                            pc++;
                        }
                    }
                    break;

                case JMPIM: // PC ← [A]
                    m[ir.p].opc = Opcode.DATA;
                    int valor = memory.data[translate(ir.p)].p;

                    // se [A] < 0 OU [A] > 1024, salta para interrupção de endereço invalido
                    if((valor < 0) || (valor > 1023)){
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    else if((valor > 0) && (valor > 1023)){
                    }
                    else{
                        pc = valor;
                    }
                    break;

                case JMPIGM: // If Rc > 0 then PC ← [A] Else PC ← PC +1
                    valor = ir.p;

                    // se [A] < 0 OU [A] > 1024, salta para interrupção de endereço invalido
                    if((valor < 0) || (valor > 1023)){
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    else{
                        // se Rc < 0 OU Rc > 1024, salta para interrupção de endereço invalido
                        if((registradores[ir.r2] < 0) || (registradores[ir.r2] > 1024)){
                            interrupt = Interrupt.INVALID_ADDRESS;
                        }
                        else if(registradores[ir.r2] > 0){
                            pc = memory.data[translate(valor)].p;
                        }
                        else {
                            pc++;
                        }
                    }
                    break;

                case JMPILM: // If Rc < 0 then PC ← [A] Else PC ← PC +1
                    valor = ir.p;

                    // se [A] < 0 OU [A] > 1024, salta para interrupção de endereço invalido
                    if((valor < 0) || (valor > 1023)){
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    else{
                        if (registradores[ir.r2] < 0) {
                            pc = memory.data[translate(valor)].p;
                        }
                        else {
                            pc++;
                        }
                    }
                    break;

                case JMPIEM: // If  Rc = 0 then PC ← [A] Else PC ← PC +1
                    valor = ir.p;

                    // se [A] < 0 OU [A] > 1024, salta para interrupção de endereço invalido
                    if((valor < 0) || (valor > 1023)){
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    else{
                        if (registradores[ir.r2] == 0) {
                            pc = memory.data[translate(valor)].p;
                        }
                        else {
                            pc++;
                        }
                    }
                    break;

// --------------------------------------------------------------------------------------------------



//--------------------------------------------------------------------------------------------------
//Instruções de Movimentação
// --------------------------------------------------------------------------------------------------
                case LDI: // Rd ← k
                    registradores[ir.r1] = ir.p;
                    pc++;
                    break;


                case LDD: // Rd ← [A]
                    //m[ir.p].opc = Opcode.DATA;
                    valor = ir.p;

                    if ((valor < 0) || (valor > 1023)){ //Endereço Invalido -> (ir.p < 0) || (ir.p > 1024)
                        interrupt  = Interrupt.INVALID_ADDRESS;
                    }
                    else {
                        registradores[ir.r1] = memory.data[translate(valor)].p;
                        pc++;
                    }
                    break;


                case STD: // [A] ← Rs
                    //m[ir.p].opc = Opcode.DATA;
                    valor = ir.p;

                    if ((valor < 0) || (valor > 1023)){ //A está apontando para fora da memória

                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    else {
                        memory.data[translate(valor)].opc = Opcode.DATA;
                        memory.data[translate(valor)].p = registradores[ir.r1];
                        pc++;
                    }
                    break;


                case STX: // [Rd] ←Rs
                    valor = registradores[ir.r1];


                    if ((valor < 0) || (valor > 1023)){ //Rs está apontando para fora da memória
                        
                    	System.out.println("registradores[ir.r1] = " + valor);
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    else {
                        memory.data[translate(valor)].opc = Opcode.DATA;
                        memory.data[translate(valor)].p = registradores[ir.r2];
                        pc++;
                    }
                    break;


                case LDX: // Rd ← [Rs]
                    valor = registradores[ir.r2];

                    if ((valor < 0) || (valor > 1023)){ //Rs está apontando para fora da memória
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    else {
                        registradores[ir.r1] = memory.data[translate(valor)].p;
                        pc++;
                    }
                    break;

// --------------------------------------------------------------------------------------------------



//--------------------------------------------------------------------------------------------------
//		Demais Instruções
// --------------------------------------------------------------------------------------------------
                case SWAP:
                    if(registradores[ir.r1] > registradores[ir.r2]){
                        int T = 0;

                        // TROCAR NOS REGISTRADORES
                        T = registradores[ir.r1];
                        registradores[ir.r1] = registradores[ir.r2];
                        registradores[ir.r2] = T;
                        pc++;
                    }
                    else{
                        pc++;
                        break;
                    }

                case STOP: // por enquanto, para execucao
                    break;


                case TRAP:
                    interrupt = Interrupt.TRAP;
                    pc++;
                    break;

 //--------------------------------------------------------------------------------------------------

                default:
                    interrupt = Interrupt.INVALID_INSTRUCTION;
                    break;
            }


            Console.print(".");  // Simula no terminal o loading da CPU.
        } // FIM DO COMANDO SWITCH FETCH


            // VERIFICA INTERRUPÇÃO !!! - TERCEIRA FASE DO CICLO DE INSTRUÇÕES
        switch (interrupt) {
            case STOP:              // 0
                Console.print("\n"); Console.warn(" > Interrupt.STOP");
                break;

            case INVALID_ADDRESS:       // 1
                InterruptHandling.rotinaEnderecoMemoriaInvalido(interrupt, ir.opc);  //desvia para a rotina de Tratamento
                break;  // finaliza o loop da CPU

            case INVALID_INSTRUCTION:       // 2
                InterruptHandling.rotinaInstrucaoInvalida(interrupt, ir.opc);
                break;  // finaliza o loop da CPU

            case OVERFLOW:                  // 3
                InterruptHandling.rotinaUnderOverFlow(interrupt, ir.opc);
                break;  // finaliza o loop da CPU

            case INVALID_SYSTEM_CALL:
                InterruptHandling.rotinaChamadaDeSistemaInvalida(interrupt, ir.opc);
                break;  // finaliza o loop da CPU

            case TRAP:
                Console.print("\n");
                TrapHandling.trap(registradores, this);
                interrupt = Interrupt.NONE;
                break;
        }

       /* try{
            Thread.sleep(this.CLOCK);
        } catch(Exception e) {
            Thread.currentThread().interrupt();
        }*/

      } // FIM PROCESSO RUN()
    // FIM DA CPU


    /**
     * Converte de um endereço lógico em um endereço físico.
     */
    public int translate(int pc){

        boolean isValid = true;

        int pageSize = memoryManager.pageSize;
        int index = pc / pageSize;
        int res = 0;

        try {
            programPages.get(index);
        }
        catch (Exception e) {
            interrupt = Interrupt.INVALID_ADDRESS;
            isValid = false;
        }

        if(isValid){
            res = (programPages.get(index) * pageSize) + (pc % pageSize);
        }

        return res;
    }

    public static CPU init() {
        Console.debug(" > CPU.init() ");
        return new CPU();
    }




}
