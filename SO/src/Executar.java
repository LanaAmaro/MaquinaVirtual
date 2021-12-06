import util.Console;
import util.Menu;
import vm.VM;

public class Executar {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Console.info("Iniciando Máquina Virtual... ");
		Console.wait(1200);

		VM.init();

		Menu.get().MostrarMenu();

	} // FIM METODO MAIN
} // FIM CLASSE EXECUTAR