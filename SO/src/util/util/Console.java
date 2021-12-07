package util;


import java.util.Scanner;

public class Console {
    
    /**
     * Imprime na linha corrente do terminal o valor {@code toString()} de um objeto.
     * 
     * @param _object preferencialmente um objeto do tipo String.
     */
    public static void print(Object _object) {
        System.out.print(_object.toString());
    }


    /**
     * Registra no terminal o valor {@code toString()} de um objeto. No final, 
     * adiciona uma nova linha.
     * 
     * @param _object preferencialmente um objeto do tipo String.
     */
    public static void log(Object _object) {
        String breakLine = "\n";
        Console.print(_object.toString() + breakLine);
    }


    /**
     *
     * {@code toString()} de um objeto. No final, adiciona uma nova linha. 
     * 
     * @param _object preferencialmente um objeto do tipo {@code String}.
     */
    public static void info(Object _object) {
        System.out.println("\n" +
           Colors.cyan(_object.toString()) + "\n"
        );
    }


    /**
     *
     * {@code toString()} de um objeto. No final, adiciona uma nova linha. 
     * 
     * @param _object preferencialmente um objeto do tipo {@code String}.
     */
    public static void debug(Object _object) {
        System.out.println(
             Colors.yellow(_object.toString())
        );
    }

    
    /**
     *
     * {@code toString()} de um objeto. No final, adiciona uma nova linha. 
     * 
     * @param _object preferencialmente um objeto do tipo String.
     */
    public static void warn(Object _object) {
        System.out.println(
            Colors.yellow(_object.toString())
        );
    }


    /**
     *
     * {@code toString()} de um objeto. No final, adiciona uma nova linha.
     * 
     * @param _object preferencialmente um objeto do tipo {@code String}.
     */
    public static void error(Object _object) {
        System.out.println(
                Colors.red(_object.toString())
        );
    }


    public static final Scanner readConsole = new Scanner(System.in);

    /**
     * Obtém valor de entrada inserido no terminal pelo usuário.
     * 
     * @return uma única linha de texto. 
     */
    public static String read() {
        String input = "";
        try {
            if(readConsole.hasNextLine()){
                input = readConsole.nextLine();
            }
        } 
        catch (Exception exception) {
            System.err.format(
                Colors.red(" Error: Console I/O exception: %s%n"),
                exception
            );
        }
        return input;
    }


    /**
     * Pausa o terminal por um tempo determinado.
     * 
     * @param _milliseconds tempo de espera em milissegundos.
     */
    public static void wait(int _milliseconds){
        try{ 
            Thread.sleep(_milliseconds); 
        } catch(Exception e) {
            Thread.currentThread().interrupt();
        }
    }

}