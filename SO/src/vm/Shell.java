package vm;
import java.util.Scanner;
/*
public class Shell extends Thread {
    public static Boolean verOs;
    private boolean menu1;
    private boolean menu2;
    private boolean menu3;
    private boolean menu4;
    public boolean console;
    public int entradaConsole;
    private int opcao;
    private String entrada;
    private String versao = "VM - SisoP  [versao 1.5]\n";
    private Scanner in = new Scanner(System.in);


    //contrutor da shell
    public Shell(){
        menu1 = true;
        menu2 = true;
        menu3 = true;
        menu4 = true;
        console = false;
        verOs = false;
    }

    //converte a entrada para inteiro e envia esse inteiro para verificaracao de cada menu
    private int entradaCorreta(String entrada){
        int opcao = -1;
        if(entrada.toUpperCase().equals("ENT")){
            return -987569561;
        }
        try {
            opcao = Integer.parseInt(entrada);
        } catch (Exception e) {
            return opcao;
        }
        return opcao;
    }

    //converte a entrada para inteiro e envia true caso seja e false caso nao seja
    private boolean entradaConsole(String entrada){
        try {
            opcao = Integer.parseInt(entrada);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //run da thread shell, contem toda a logica da shell
    public void run(){

        //cria os programas com os tamanhos
        //Programas p1 = new Programas(LogicaProgramas.p1Fibonacci, 29, "Fibonacci");
        //Programas p2 = new Programas(LogicaProgramas.p2FibonacciComJMP , 40, "Fibonacci com Jump");
        //Programas p3 = new Programas(LogicaProgramas.p2FibonacciComJMPLeitura , 45, "Fibonacci com Jump e leitura");
        //Programas p4 = new Programas(LogicaProgramas.p3Fatorial , 40, "Fatorial");
        //Programas p5 = new Programas(LogicaProgramas.p3FatorialLeituraEscrita , 45, "Fatorial com leitura e escrita");
        //Programas p6 = new Programas(LogicaProgramas.p4BubbleSort , 60, "Bubble sort");


        //logica do menu da shell
        System.out.println("\n"+versao);
        while(true){
            while (menu1){
                System.out.println("\n\n-----Opcoes da VM-----\n-----Informe uma opcao para continua-----");
                System.out.println("\n-----1 = Ver os programas rodando no sistema-----");
                System.out.println("-----2 = Rodar Programas-----");
                System.out.println("-----3 = Encerrar a shell-----");
                entrada = in.nextLine();
                opcao = entradaCorreta(entrada);

                if(opcao == 1){
                    System.out.println("\n-----Programas rodando no sistema-----");
                    System.out.println("-----0 = Voltar ao menu anterior----");
                    menu2 = true;
                    verOs = true;
                    while(menu2){
                        entrada = in.nextLine();
                        opcao = entradaCorreta(entrada);
                        if(opcao == 0){
                            verOs = false;
                            System.out.println("\n\n-----Voltando ao menu principal-----");
                            menu2 = false;
                        }
                        else if(opcao == -987569561){
                            menu3 = true;
                            while (menu3){
                                System.out.println("\n-----Informe um numero inteiro para-----");
                                System.out.println("-----O programa: "+VM.console.nome+", com ID = "+VM.console.id+"-----");
                                System.out.print("-----Inteiro = ");
                                entrada = in.nextLine();
                                opcao = entradaCorreta(entrada);
                                if(entradaConsole(entrada)){
                                    entradaConsole = Integer.parseInt(entrada);
                                    VM.console.consoleEntrada.release();
                                    menu3 = false;
                                    System.out.println("\n\n-----Numero informado correto, seguindo com o programa: "+VM.console.nome+"-----");
                                }
                                else{
                                    System.out.println("\n\n*****Numero informado não é um inteiro, por favor, informe um inteiro*****");
                                }
                            }
                        }
                        else{
                            if(VM.console.mandouPedido){
                                menu3 = true;
                                while (menu3){
                                    System.out.println("\n\n*****Digite \"ent\" para informar um inteiro para o programa: "+VM.console.nome+"*****");
                                    entrada = in.nextLine();
                                    opcao = entradaCorreta(entrada);
                                    if(opcao == -987569561){
                                        menu4 = true;
                                        while (menu4){
                                            System.out.println("\n-----Informe um numero inteiro para-----");
                                            System.out.println("-----O programa: "+VM.console.nome+", com ID = "+VM.console.id+"-----");
                                            System.out.print("-----Inteiro = ");
                                            entrada = in.nextLine();
                                            opcao = entradaCorreta(entrada);
                                            if(entradaConsole(entrada)){
                                                entradaConsole = Integer.parseInt(entrada);
                                                VM.console.consoleEntrada.release();
                                                menu3 = false;
                                                menu4 = false;
                                                System.out.println("\n\n-----Numero informado correto, seguindo com o programa: "+VM.console.nome+"-----");
                                            }
                                            else{
                                                System.out.println("\n\n*****Numero informado não é um inteiro, por favor, informe um inteiro*****");
                                            }
                                        }
                                    }
                                }
                            }
                            else{
                                System.out.println("\n\n*****Opcao invalida, digite uma opcao valida*****");
                                System.out.println("-----0 = Voltar ao menu anterior----");
                            }
                        }
                    }
                }
                else if(opcao == 2){
                    menu2 = true;
                    while (menu2){
                        System.out.println("\n\n-----Programas-----\n-----Informe um programa para rodar-----");
                        //System.out.println("\n-----1 = Programa "+p1.getNome()+"----");
                        //System.out.println("-----2 = Programa "+p2.getNome()+"----");
                        //System.out.println("-----3 = Programa "+p3.getNome()+"----");
                        //System.out.println("-----4 = Programa "+p4.getNome()+"----");
                        //System.out.println("-----5 = Programa "+p5.getNome()+"----");
                        //System.out.println("-----6 = Programa "+p6.getNome()+"----");
                        System.out.println("-----0 = Voltar ao menu anterior----");
                        entrada = in.nextLine();
                        opcao = entradaCorreta(entrada);

                        if(opcao == 0){
                            System.out.println("\n\n-----Voltando ao menu principal-----");
                            menu2 = false;
                        }
                        else if(opcao == 1){
                            System.out.println("\n---------------Programa "+p1.getNome()+" Colocado no SO--------------");
                            VM.pm.alocaPCB(p1);
                        }
                        else if(opcao == 2){
                            System.out.println("\n---------------Programa "+p2.getNome()+" Colocado no SO--------------");
                            VM.pm.alocaPCB(p2);
                        }
                        else if(opcao == 3){
                            System.out.println("\n---------------Programa "+p3.getNome()+" Colocado no SO--------------");
                            VM.pm.alocaPCB(p3);
                        }
                        else if(opcao == 4){
                            System.out.println("\n---------------Programa "+p4.getNome()+" Colocado no SO--------------");
                            VM.pm.alocaPCB(p4);
                        }
                        else if(opcao == 5){
                            System.out.println("\n---------------Programa "+p5.getNome()+" Colocado no SO--------------");
                            VM.pm.alocaPCB(p5);
                        }
                        else if(opcao == 6){
                            System.out.println("\n---------------Programa "+p6.getNome()+" Colocado no SO--------------");
                            VM.pm.alocaPCB(p6);
                        }
                        else if(opcao == -987569561){
                            menu3 = true;
                            while (menu3){
                                System.out.println("\n-----Informe um numero inteiro para-----");
                                System.out.println("-----O programa: "+VM.console.nome+", com ID = "+VM.console.id+"-----");
                                System.out.print("-----Inteiro = ");
                                entrada = in.nextLine();
                                opcao = entradaCorreta(entrada);
                                if(entradaConsole(entrada)){
                                    entradaConsole = Integer.parseInt(entrada);
                                    VM.console.consoleEntrada.release();
                                    menu3 = false;
                                    System.out.println("\n\n-----Numero informado correto, seguindo com o programa: "+VM.console.nome+"-----");
                                }
                                else{
                                    System.out.println("\n\n*****Numero informado não é um inteiro, por favor, informe um inteiro*****");
                                }
                            }
                        }
                        else{
                            if(VM.console.mandouPedido){
                                menu3 = true;
                                while (menu3){
                                    System.out.println("\n\n*****Digite \"ent\" para informar um inteiro para o programa: "+VM.console.nome+"*****");
                                    entrada = in.nextLine();
                                    opcao = entradaCorreta(entrada);
                                    if(opcao == -987569561){
                                        menu4 = true;
                                        while (menu4){
                                            System.out.println("\n-----Informe um numero inteiro para-----");
                                            System.out.println("-----O programa: "+VM.console.nome+", com ID = "+VM.console.id+"-----");
                                            System.out.print("-----Inteiro = ");
                                            entrada = in.nextLine();
                                            opcao = entradaCorreta(entrada);
                                            if(entradaConsole(entrada)){
                                                entradaConsole = Integer.parseInt(entrada);
                                                VM.console.consoleEntrada.release();
                                                menu3 = false;
                                                menu4 = false;
                                                System.out.println("\n\n-----Numero informado correto, seguindo com o programa: "+VM.console.nome+"-----");
                                            }
                                            else{
                                                System.out.println("\n\n*****Numero informado não é um inteiro, por favor, informe um inteiro*****");
                                            }
                                        }
                                    }
                                }
                            }
                            else{
                                System.out.println("\n\n*****Programa invalida, digite um programa valido*****");
                            }
                        }
                    }
                }
                else if(opcao == 3){
                    System.out.print("-----Encerrando VM");
                    try { Thread.sleep (1000); } catch (InterruptedException ex) {}
                    System.out.print("-");
                    try { Thread.sleep (1000); } catch (InterruptedException ex) {}
                    System.out.print("-");
                    try { Thread.sleep (1000); } catch (InterruptedException ex) {}
                    System.out.print("-");
                    try { Thread.sleep (1000); } catch (InterruptedException ex) {}
                    System.out.print("-");
                    try { Thread.sleep (1000); } catch (InterruptedException ex) {}
                    System.out.print("\n\n-----VM Encerrada-----\n\n");
                    System.exit(0);
                }
                else if(opcao == -987569561){
                    menu3 = true;
                    while (menu3){
                        System.out.println("\n-----Informe um numero inteiro para-----");
                        System.out.println("-----O programa: "+VM.console.nome+", com ID = "+VM.console.id+"-----");
                        System.out.print("-----Inteiro = ");
                        entrada = in.nextLine();
                        if(entradaConsole(entrada)){
                            entradaConsole = Integer.parseInt(entrada);
                            VM.console.consoleEntrada.release();
                            menu3 = false;
                            System.out.println("\n\n-----Numero informado correto, seguindo com o programa: "+VM.console.nome+"-----");
                        }
                        else{
                            System.out.println("\n\n*****Numero informado não é um inteiro, por favor, informe um inteiro*****");
                        }
                    }
                }
                else{
                    if(VM.console.mandouPedido){
                        menu3 = true;
                        while (menu3){
                            System.out.println("\n\n*****Digite \"ent\" para informar um inteiro para o programa: "+VM.console.nome+"*****");
                            entrada = in.nextLine();
                            opcao = entradaCorreta(entrada);
                            if(opcao == -987569561){
                                menu4 = true;
                                while (menu4){
                                    System.out.println("\n-----Informe um numero inteiro para-----");
                                    System.out.println("-----O programa: "+VM.console.nome+", com ID = "+VM.console.id+"-----");
                                    System.out.print("-----Inteiro = ");
                                    entrada = in.nextLine();
                                    opcao = entradaCorreta(entrada);
                                    if(entradaConsole(entrada)){
                                        entradaConsole = Integer.parseInt(entrada);
                                        VM.console.consoleEntrada.release();
                                        menu3 = false;
                                        menu4 = false;
                                        System.out.println("\n\n-----Numero informado correto, seguindo com o programa: "+VM.console.nome+"-----");
                                    }
                                    else{
                                        System.out.println("\n\n*****Numero informado não é um inteiro, por favor, informe um inteiro*****");
                                    }
                                }
                            }
                        }
                    }
                    else{
                        System.out.println("\n\n*****opcao invalida, digite uma opcao valida*****");
                    }
                }
            }
        }
    }
}*/