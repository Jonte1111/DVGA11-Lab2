package restaurang;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable{
	private Integer index;
	private ArrayList<Point> arr;
	private ArrayList<Kund> kundArr;
	private Boolean mousePressed;
	public Model() {
		index = 0;
		arr = new ArrayList<Point>();
		kundArr = new ArrayList<Kund>();
		coordinates();
		mousePressed = false;
	}
	public void indexPressed(int index) {
		this.index = index;
		setChanged();
		notifyObservers();
	}
	public Integer getI() {
		return index;
	}
	public void coordinates() {
		//Fasta koordinater för knapparna då programmet inte behöver scale:a
		arr.add(new Point(77, 243));
		arr.add(new Point(77,550));
		arr.add(new Point(157,650));
		arr.add(new Point(77,750));
		arr.add(new Point(157,850));
		arr.add(new Point(77,950));
		arr.add(new Point(552,1050));
		arr.add(new Point(707,1050));
		arr.add(new Point(857,1050));
		arr.add(new Point(1011,1050));
		arr.add(new Point(667,920)); //11
		arr.add(new Point(758,920));
		arr.add(new Point(899,920));
		arr.add(new Point(1063,788));
		arr.add(new Point(1063,683));
		arr.add(new Point(1063,596));
	}
	public ArrayList<Point> getArr() {
		return arr;
	}
	public ArrayList<Kund> getKundArr() {
		return kundArr;
	}
	public void addToKundArr(Kund kund) {
		kundArr.add(kund);
	}
	public int checkFull(ArrayList<Custom_Button> buttonArr) {
		for(Custom_Button b : buttonArr) {
			if(!b.getBokad())
				return 0;
		}
		return 1;
	}
	public void mousePressed(Boolean press) {
		System.out.println("Högerklick");
		this.mousePressed = press;
		setChanged();
		notifyObservers();
	}
	public boolean getMousePressed() {
		return mousePressed;
	}
	public void setMousePressedFalse() {
		this.mousePressed = false;
	}
	public String[] getStringArr() {
		ArrayList<String> stringArr = new ArrayList<String>();
		for(Kund k : kundArr) {
			stringArr.add(k.getNamn() + " " + String.valueOf(k.getAntal()));
			System.out.println(stringArr.get(0));
		}
		String[] stringArr2 = stringArr.toArray(new String[0]);
		return stringArr2;
		
	}
	public void removeFromList(int index) {
		kundArr.remove(index);
	}
	public int getLastCFromQ(int index) {
		String string = getStringArr()[index];
		String subString = string.substring(string.length() -1);
		int antalGuest = Integer.parseInt(subString);
		System.out.println(antalGuest);
		return antalGuest;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawString("Ett meddelande", 70, 20);
	}
}
