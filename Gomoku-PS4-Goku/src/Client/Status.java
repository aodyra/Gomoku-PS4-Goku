package Client;

public class Status {
	private boolean bool;
	public Status(boolean bool) {
		this.bool = bool;
	}
	public void set(boolean bool) {
		this.bool = bool; 
	}
	public boolean get() {
		return this.bool;
	}
}
