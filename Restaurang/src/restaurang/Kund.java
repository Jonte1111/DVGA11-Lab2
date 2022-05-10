package restaurang;

public class Kund {
	private String namn;
	private int antal;
	public Kund(String namn, int antal) {
		this.namn = namn;
		this.antal = antal;
	}
	public String getNamn() {
		return this.namn;
	}
	public int getAntal() {
		return this.antal;
	}
}
