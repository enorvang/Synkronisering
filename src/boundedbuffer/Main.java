package boundedbuffer;

import java.util.concurrent.Semaphore;

public class Main {

	private final static int BUFFER_KAP = 5;
	public static void main(String[] args) throws InterruptedException {
		Buffer<Integer> buffer = new Buffer<Integer>();
		Semaphore mutex = new Semaphore(1);
		Semaphore full = new Semaphore(0);
		Semaphore empty = new Semaphore(BUFFER_KAP);
		
		Produsent p1 = new Produsent("P1", mutex, empty, full, buffer);
		Konsument k1 = new Konsument("Espen", mutex, empty, full, buffer);
		Konsument k2 = new Konsument("Ingrid", mutex, empty, full, buffer);
		Konsument k3 = new Konsument("Morten", mutex, empty, full, buffer);
		Konsument k4 = new Konsument("Jonas", mutex, empty, full, buffer);
		
		p1.start();
		k1.start();
		k2.start();
		k3.start();
		k4.start();
		
		k1.join();
		k2.join();
		k3.join();
		k4.join();
		p1.join();
		
	}
}
