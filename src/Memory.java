/**
 * 
 * @author Daniel
 *
 */
public class Memory {

	/**
	 * co�tam
	 * @param args
	 */
	public static void main(String[] args) {
		
		MemoryModel model = new MemoryModel();
		MemoryController controller = new MemoryController(model);
		MemoryView view = new MemoryView(controller);
		controller.registerView(view);
		
	}

}
