package diningphilosophers;

import java.util.concurrent.Semaphore;

public class Filosof implements Runnable {

	private final int ANTALL_FILOSOFER;
	private final int i;
	private final Object[] spisepinner;
	private final Semaphore mutex;

	public Filosof(int antallFilosofer, int i, Object[] spisepinner, Semaphore mutex) {
		super();
		this.ANTALL_FILOSOFER = antallFilosofer;
		this.i = i;
		this.spisepinner = spisepinner;
		this.mutex = mutex;
	}

	/*
	 * En filosof tenker eller spiser. For å spise må han først plukke opp venstre spisepinne, deretter høyre.
	 * Etter han har spist legger han ned høyre spisepinne, deretter venstre.
	 */
	@Override
	public void run() {
		try {
			while (true) {
				tenk();

				mutex.acquire();
				plukkOpp(spisepinner[i]);
				plukkOpp(spisepinner[(i + 1) % ANTALL_FILOSOFER]);
				spis();
				mutex.release();

				
				leggNed(spisepinner[(i + 1) % ANTALL_FILOSOFER]);
				leggNed(spisepinner[i]);
				
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}

	private void tenk() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + ": Tenker...");
		Thread.sleep(((int) (Math.random() * 1000)));
	}

	private void spis() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + ": Spiser...");
		Thread.sleep(((int) (Math.random() * 1000)));
	}

	private void plukkOpp(Object o) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + ": Plukket opp " + o.toString());
		Thread.sleep(((int) (Math.random() * 1000)));
	}

	private void leggNed(Object o) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + ": La ned " + o.toString());
		Thread.sleep(((int) (Math.random() * 1000)));
	}

}
