package restaurang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;



public class View extends JFrame implements Observer{
	private Model model;
	private Controller controller;
	private Custom_Button button;
	private JPanel testPanel;
	private BorderLayout b;
	private JLabel background;
	private ArrayList<Custom_Button> buttonList;
	private BackgroundPanel bp;
	private BufferedImage image;
	private URL bildUrl;
	private JOptionPane confirm;
	private SpinnerModel spinnerModel;
	private JSpinner guestSpinner;
	private JOptionPane guestPane;
	private JPanel showQ;
	private JLabel qNotif;
	public View(Controller controller) throws IOException {
		model = new Model();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 1200);
		setLayout(b = new BorderLayout());
		confirm = new JOptionPane("Message");
		spinnerModel = new SpinnerNumberModel(2,1,6,1);
		guestSpinner = new JSpinner(spinnerModel);
		guestPane = new JOptionPane();
		bildUrl = this.getClass().getResource("/resources/ritning_med_stolar.png");
		image = ImageIO.read(bildUrl);
		bp = new BackgroundPanel(image, BackgroundPanel.SCALED, 0.0f, 0.0f);
		buttonList = new ArrayList<Custom_Button>();
		fillButtons(controller);
		add(bp);
		//Lyssnar efter högerklick
		addMouseListener(controller);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Model model = (Model) o;
		int numberOfGuests = 0;
		int qGuestNum = 0;
		String qOrNot;
		//Ändrar meddelandet som visas när man bokar personer, för att man inte ska missa att det finns folk i kön
		if(model.getStringArr().length > 0)
			qOrNot = "Det finns personer i kön";
		else
			qOrNot = "Kön är tom, välj hur många personer att boka";
		//Lägger till en person i kön om användaren har högerklickat
		if(model.getMousePressed()) {
			Kund Stefan = qMenu();
			if(Stefan != null)
				model.addToKundArr(Stefan);
		}
		//Avbokar bordet om det redan är bokat
		if(buttonList.get(model.getI()).getBokad() && !model.getMousePressed()) {
			buttonList.get(model.getI()).changeColor();
			buttonList.get(model.getI()).setBokad();
		}
		else if(!buttonList.get(model.getI()).getBokad() && !model.getMousePressed()) {
			int tmp = showInput(qOrNot);
			System.out.println(tmp);
			//Om köknappen är tryckt boka bord till en person i kön, ta sedan bort personen från kön
			if(tmp == 7 && model.getStringArr().length > 0) {
				int tmp2 = addFromQ(model.getStringArr());
				if(tmp2 != -1)
					model.getLastCFromQ(tmp2);
				if(tmp2 != -1 && model.getLastCFromQ(tmp2) <= buttonList.get(model.getI()).getAntalPlatser() ) {
					buttonList.get(model.getI()).changeColor();
					buttonList.get(model.getI()).setBokad();
					model.removeFromList(tmp2);
				}
			}
			System.out.println(tmp);
			if(tmp != 0)
				numberOfGuests = tmp;
			System.out.println(tmp);
			//Om antalgäster är mindre än antalplatser boka bordet
			if(numberOfGuests <= buttonList.get(model.getI()).getAntalPlatser() && numberOfGuests != 7 && numberOfGuests != 0) {
				buttonList.get(model.getI()).changeColor();
				buttonList.get(model.getI()).setBokad();
			}
		}
		if(model.checkFull(buttonList) == 1 && !model.getMousePressed()) {
			JOptionPane.showMessageDialog(null, "Restaurangen är full, högerklicka för att lägga till kunder i kön");
		}
		if(numberOfGuests > buttonList.get(model.getI()).getAntalPlatser() && !model.getMousePressed() && numberOfGuests != 7)
			tooManyP();
		model.setMousePressedFalse();
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
			b.setBounds(model.getArr().get(buttonList.indexOf(b)).x, model.getArr().get(buttonList.indexOf(b)).y, b.getWidth(), b.getHeight());
			if(buttonList.indexOf(b) < 10)
				b.setAntalPlatser(4);
			else if(buttonList.indexOf(b) != 12)
				b.setAntalPlatser(2);
			else
				b.setAntalPlatser(6);
			this.add(b);
		}
	}
	public int showInput(String qOrNot) {
		//https://stackoverflow.com/questions/10107422/jspinner-in-joptionpane
		//En Object array gör det möjligt att sätta en text-beskrivning ovanför input fältet
		Object[] antalG = {qOrNot, guestSpinner};
		String[] val = {"Boka bord", "Boka från kön", "Avbryt"};
		int option = JOptionPane.showOptionDialog(null, antalG, qOrNot, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, val, null);
		if(option == JOptionPane.CANCEL_OPTION) {
			return 0;
		}
		else if(option == JOptionPane.NO_OPTION) {
			return 7;
		}
		else if(option == JOptionPane.CLOSED_OPTION) {
			return 0;
		}
		else
			return (Integer) guestSpinner.getValue();
	}
	public void tooManyP() {
		JOptionPane.showMessageDialog(null, "För många personer, ta ett större bord eller sätt i kön");
	}
	public Kund qMenu() {
		JTextField namn = new JTextField();
		Object[] qPopUp = {"Namn: ", namn, "Antal Gäster", guestSpinner};
		int kundUppgifter = JOptionPane.showOptionDialog(null, qPopUp, "Fyll i kunduppgifter", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if(kundUppgifter == JOptionPane.OK_OPTION && namn.getText().length() > 1)
			return new Kund(namn.getText(), (Integer) guestSpinner.getValue());
		else 
			return null;
	}
	public int addFromQ(String[] choices) {
		String input = (String) JOptionPane.showInputDialog(
                null,
                "Vem skall boka bordet",
                "Välj en gäst",
                JOptionPane.PLAIN_MESSAGE,
                null,
                choices,
                choices[0]);
			return Arrays.asList(choices).indexOf(input);
	}
}
