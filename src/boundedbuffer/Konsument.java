package boundedbuffer;

import java.util.concurrent.Semaphore;

public class Konsument extends Thread {
	private String navn;
	private Semaphore mutex;
	private Semaphore empty;
	private Semaphore full;
	private Buffer<Integer> buffer;
	

	public Konsument(String navn, Semaphore mutex, Semaphore empty, Semaphore full, Buffer<Integer> buffer) {
		this.navn = navn;
		this.mutex = mutex;
		this.empty = empty;
		this.full = full;
		this.buffer = buffer;
	
	}

	@Override
	public void run() {

		do {
			try {
				// venter til det er et objekt i bufferen (alts� at produsent har frigitt en
				// tillatelse til semaforen full)
				full.acquire();
				// henter l�sen
				mutex.acquire();

				int verdi = (int) buffer.fjern();
				System.out.println("\"" + navn + "\"" + " konsumerte: " + verdi);
				Thread.sleep(20);

				// frigir l�sen
				mutex.release();
				// frigj�r en tillatelse til semaforen empty.
				empty.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);

	}

}
