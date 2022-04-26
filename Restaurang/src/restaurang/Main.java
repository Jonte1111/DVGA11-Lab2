package restaurang;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();
		Controller controller = new Controller(model);
		View view = new View(controller);
		model.addObserver(view);

	}

}
