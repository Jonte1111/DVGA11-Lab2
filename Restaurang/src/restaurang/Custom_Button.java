package restaurang;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class Custom_Button extends JButton{
	private static final long serialVersionUID = 1L;
	private Graphics2D g2d;
	private Dimension size;
	private Color colorVerm;
	private Color colorGreen;
	private Color color;
	private int width;
	private int height;
	private boolean bokad;
	private int antalPlatser;
	public Custom_Button(String label) {
		super(label);
		colorVerm = new Color(213, 94, 0);
		colorGreen = new Color(0,158,115);
		color = colorGreen;
		enableInputMethods(true);
		size = new Dimension(80,80);
		setPreferredSize(size);
		setContentAreaFilled(false);
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		width = size.width;
		height = size.height;
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//Gör om graphics objektet g till ett graphics2d objekt för mer funktionalitet.
		g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		//Använder origianlstorleken för en JButton
        //int x = ogSize.width + gap;
        //int y = gap;
        
        g2d.setColor(color);
        g2d.fillOval(0, 0, this.width, this.height);
        g2d.setColor(Color.white);
        g2d.drawString(super.getText(), this.width/2, this.height/2);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return size;
	}
	@Override
	public Dimension getMinimumSize() {
		return super.getMinimumSize();
	}
	@Override
	public Dimension getMaximumSize() {
		return super.getMaximumSize();
	}
	public void changeColor() {
		if(this.color == colorVerm)
			this.color = colorGreen;
		else 
			this.color = colorVerm;
		repaint();
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.width;
	}
	public boolean getBokad() {
		return this.bokad;
	}
	public void setBokad() {
		if(this.bokad)
			this.bokad = false;
		else 
			this.bokad = true;
	}
	public void setAntalPlatser(int platser) {
		this.antalPlatser = platser;
	}
	public int getAntalPlatser() {
		return this.antalPlatser;
	}
}

