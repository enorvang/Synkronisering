package boundedbuffer;

import java.util.concurrent.Semaphore;

public class Main {

	private final static int BUFFER_KAP = 5;

	public static void main(String[] args) throws InterruptedException {
		Buffer<Integer> buffer = new Buffer<Integer>();
		Semaphore mutex = new Semaphore(1);
		Semaphore full = new Semaphore(0);
		Semaphore empty = new Semaphore(BUFFER_KAP);

		Produsent produsent = new Produsent(mutex, empty, full, buffer);
		Konsument[] konsumentTraader = {
				new Konsument("Espen", mutex, empty, full, buffer),
				new Konsument("Ingrid", mutex, empty, full, buffer),
				new Konsument("Morten", mutex, empty, full, buffer),
				new Konsument("Hanna", mutex, empty, full, buffer) 
				};

		produsent.start();
		
		for(Konsument k : konsumentTraader) {
			k.start();
		}
		
		for(Konsument k : konsumentTraader) {
			k.join();
		}

		produsent.join();


	}
}
