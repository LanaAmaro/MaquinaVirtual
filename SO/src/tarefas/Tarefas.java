package tarefas;

import hardware.memoria.Word;
import so.Programas;
import util.Auxi;
import vm.VM;

/**
 * São sequências pré-definidas de interação com a VM.
 */
public class Tarefas {
    private static Tarefas INSTANCE = new Tarefas();

    // Variaveis
    public Tarefa mainTask;
    public Tarefa fibonacci;
    public Tarefa factorial;
    public Tarefa bubbleSort;
    public Tarefa trapIn;
    public Tarefa trapOut;

    private Tarefas() {
        this.mainTask = new MainTarefas();

        this.fibonacci = new CalculoDeFibonacci();
        this.factorial = new CalculaFatorial();
        this.bubbleSort = new BubbleSortTask();
        this.trapIn = new TrapInTask();
        this.trapOut = new TrapOutTask();
    }

    /**
     * @return instância única do Menu.
     */
    public static Tarefas get() {

        return INSTANCE;
    }
}
