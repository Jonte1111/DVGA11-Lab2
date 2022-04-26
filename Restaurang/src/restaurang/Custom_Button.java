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
	private Color color;
	public Custom_Button(String label) {
		super(label);
		color = Color.magenta;
		enableInputMethods(true);
		size = new Dimension(100,100);
		size.width = size.height;
		setPreferredSize(size);
		setContentAreaFilled(false);
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//Gör om graphics objektet g till ett graphics2d objekt för mer funktionalitet.
		g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		//Använder origianlstorleken för en JButton
        //int x = ogSize.width + gap;
        //int y = gap;
        int diameter = size.width;
        g2d.setColor(color);
        g2d.fillOval(0, 0, diameter, diameter);
        g2d.setColor(Color.white);
        g2d.drawString(super.getText(), diameter/2, diameter/2);
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
	public void changeColor(Color c) {
		if(this.color != c)
			this.color = c;
		else 
			this.color = Color.magenta;
		repaint();
	}
	
}

