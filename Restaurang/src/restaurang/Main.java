package restaurang;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Model model = new Model();
		Controller controller = new Controller(model);
		View view = new View(controller);
		model.addObserver(view);

	}

}
