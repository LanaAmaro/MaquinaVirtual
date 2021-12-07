package vm;

import java.util.concurrent.Semaphore;

import hardware.cpu.Opcode;
import so.PCB;
import so.StatusPCB;


public class InterruptHandling {
    private Semaphore Tratando; 
    public static boolean checkOverflowMathOperation(int value) {

        return (value > -2147483648 && value < 2147483647);
    }

    public static boolean checkAddressLimits(int value) {

        return (value >= 0 && value <= 1023);
    }


    public void trataIO(int id){
        try {
            Tratando.acquire();
        } catch (Exception e) {}
            for(PCB it: VM.fb.filaBloqueados){
                if(it.getId() == id){
                    it.setStatus(StatusPCB.READY);
                    VM.fb.retiraDaFilaBloqueados(id);
                    VM.fp.colocaNaFilaProntos(it);
                    break;
                }
            }
            VM.cpu.irptIO = Interrupt.noInterruptIO;
            //VM.gm.dump(0,80);
            VM.semESC.release();
            Tratando.release();
    }


    // Endereço inválido: programa do usuário acessando endereço fora de limites permitidos;
    public static void rotinaEnderecoMemoriaInvalido(Interrupt codInterrupt, Opcode instruction){
        System.out.println("Endereço de memória de dados inválido para a instrução " + instruction + "!\n");
    }

    // Instrução inválida: a instrução carregada é inválida;
    public static void rotinaInstrucaoInvalida(Interrupt codInterrupt, Opcode instruction){
        System.out.println("A instrução " + instruction + " é invalida!");
    }

    // Overflow em operação matemática;
    public static void rotinaUnderOverFlow(Interrupt codInterrupt, Opcode instruction){
        System.out.println("A instrução " + instruction + " gerou Overflow!");
    }

    public static void rotinaChamadaDeSistemaInvalida(Interrupt codInterrupt, Opcode instruction){
        System.out.println("O código da chamada de sistema " + instruction + " é invalido!");
    }


}
