package ua.nure.chekhunov.practice5;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public final class Part2 {

	private static final InputStream STD_IN = System.in;

	private static final long TIME_SLEEP = 2;

	private Part2() {
		throw new UnsupportedOperationException("non instance Part2");
	}

	public static void main(String[] args) throws InterruptedException {

		ByteArrayInputStream bais = new ByteArrayInputStream(System.lineSeparator().getBytes());
		System.setIn(bais);

		Thread t = new Thread() {
			@Override
			public void run() {
				Spam.main(null);

			}
		};
		t.start();
		TimeUnit.SECONDS.sleep(TIME_SLEEP);
		// c) дождаться завершения Spam.main
		t.join();

		// d) восстановить системный поток
		System.setIn(STD_IN);
	}
}
