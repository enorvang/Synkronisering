package diningphilosophers;

import java.util.concurrent.Semaphore;

public class DiningPhilosophersMain {

	public static void main(String[] args) throws Exception {

		Filosof[] filosofer = new Filosof[5];
		String[] spisepinner = new String[filosofer.length];
		Semaphore mutex = new Semaphore(1);

		for (int i = 0; i < spisepinner.length; i++) {
			if(i % 2 == 0) {
				spisepinner[i] = "Venstre spisepinne";
			}else {
				spisepinner[i] = "Høyre spisepinne";
			}
			
		}

		for (int i = 0; i < filosofer.length; i++) {
			
			filosofer[i] = new Filosof(filosofer.length, i, spisepinner, mutex);

			Thread t = new Thread(filosofer[i], "Filosof " + (i + 1));
			t.start();
		}

	}
}
