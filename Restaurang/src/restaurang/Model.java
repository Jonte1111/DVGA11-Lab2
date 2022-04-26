package restaurang;

import java.awt.Color;
import java.util.Observable;

public class Model extends Observable{
	private Integer i;
	public Model() {
		i = 0;
	}
	public void indexPressed(int index) {
		this.i = index;
		setChanged();
		notifyObservers();
	}
	public Integer getI() {
		return i;
	}
}
