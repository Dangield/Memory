/**
 * 
 * G³ówna klasa gry. Zawiera model, kontroler i widok.
 * @author Daniel Gie³dowski
 *
 */
public class Memory {

	/**
	 * 
	 * Funkcja main. Zawiera konstruktory modelu, kontrolera i widoku.
	 * @param args Argumenty wejœciowe main. U mnie nie u¿ywane.
	 * 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		MemoryModel model = new MemoryModel();
		MemoryController controller = new MemoryController(model);
		MemoryView view = new MemoryView(controller);
		
	}

}
