package restaurang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{
	private Model model;
	public Controller(Model model) {
		this.model = model;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			int i = Integer.parseInt(e.getActionCommand());
			model.indexPressed(i);
	}
}
