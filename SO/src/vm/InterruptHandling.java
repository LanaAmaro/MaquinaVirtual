package vm;

import hardware.cpu.Opcode;

public class InterruptHandling {

    public static boolean checkOverflowMathOperation(int value) {

        return (value > -2147483648 && value < 2147483647);
    }

    public static boolean checkAddressLimits(int value) {

        return (value >= 0 && value <= 1023);
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
