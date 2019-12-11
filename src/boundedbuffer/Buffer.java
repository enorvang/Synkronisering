package boundedbuffer;

import java.util.LinkedList;

public class Buffer<T> {
	
	private LinkedList<T> liste;
	
	public Buffer() {
		liste = new LinkedList<>();
	}
	
	public void leggTil(T t) {
		liste.add(t);
	}
	
	public T fjern() {
		return liste.removeFirst();
	}
}
