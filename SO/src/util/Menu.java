package util;

import hardware.memoria.Memory;
import hardware.memoria.Word;
import so.Programas;
import tarefas.CalculoDeFibonacci;
import tarefas.Tarefas;
import vm.VM;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Menu {
    private static Menu INSTANCE = new Menu();

    private String header = "\n\n\n================== MENU ==================";


    public String[] options = {
            "Digite o valor de uma das opções abaixo:\n",

            "1. Executar Menu Tarefas",
            "0. Encerrar"
    };

    private Menu() {}

    public void MostrarMenu() {
        int input;
        do {
            showOptions(this.options);

            // Variável input recebe o valor inserido pelo terminal
            Console.print("\n > Digite a opção: ");
            input = Integer.parseInt(Console.read());

            switch (input) {
                case 0:
                    Console.info("Encerrando...");
                    Console.wait(800);
                    break;

                case 1:
                    Tarefas.get().mainTask.run();
                    break;

                default:
                    Console.error(" Input inválido. Input: "+input);
                    break;

            }
        }
        while (input != 0);

    }

    private void showOptions(String[] options) {
        Console.log(this.header);

        for (int i=0; i<options.length; i++) {
            showOption(options[i]);
        }

    }

    private void showOption(String option) {
        Console.log(option);
    }


    /**
     * @return instância única do Menu.
     */
    public static Menu get() {
        return INSTANCE;
    }

}
