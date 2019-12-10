package boundedbuffer;

import java.util.concurrent.Semaphore;

public class Produsent extends Thread {
	private Semaphore mutex;
	private Semaphore empty;
	private Semaphore full;
	private Buffer<Integer> buffer;


	public Produsent( Semaphore mutex, Semaphore empty, Semaphore full, Buffer<Integer> buffer) {
		this.mutex = mutex;
		this.empty = empty;
		this.full = full;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		int verdi = 0;
		do {
			try {
				//venter til det er plass i bufferen / at en konsument har gjort en tillatelse tilgjengelig i semaforen empty.
				empty.acquire();
				//henter l�sen
				mutex.acquire();

				verdi++;
				buffer.leggTil(verdi);
				System.out.println("Produserte: " + verdi);
				Thread.sleep(20);
				
				//frigir l�sen
				mutex.release();
				//frigir en tillatelse til semaforen full
				full.release();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} while (true);
	}
}
