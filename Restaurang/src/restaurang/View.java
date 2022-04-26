package restaurang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class View extends JFrame implements Observer{
	private Model model;
	private Controller controller;
	private Custom_Button button;
	private JPanel testPanel;
	private FlowLayout b;
	private ArrayList<Custom_Button> buttonList;
	public View(Controller controller) {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setLayout(b = new FlowLayout());
		buttonList = new ArrayList<Custom_Button>();
		testPanel = new JPanel();
		testPanel.setLayout(new GridLayout(1,1));
		//button = new Custom_Button("Test");
		//button.setForeground(Color.white);
		//button.addActionListener(controller);
		//button.setActionCommand("test");
		//add(button);
		fillButtons(controller);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Model model = (Model) o;
		buttonList.get(model.getI()).changeColor(Color.green);
		
	}
	public void fillButtons(Controller controller) {
		for(Integer i = 1; i < 17; i++) { //1 till 17 för att visa rätt bordsnummer
			String index = i.toString();
			buttonList.add(new Custom_Button(index));
		}
		for(Custom_Button b : buttonList) {
			Integer indexInt = buttonList.indexOf(b);
			String index = indexInt.toString();
			b.addActionListener(controller);
			b.setActionCommand(index);
			this.add(b);
		}
	}

}
