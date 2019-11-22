package ua.nure.chekhunov.practice5;


/**
 * @author chekhunov
 *
 */
public final class Part1 {

	/**
	 * Default constructor.
	 */
	private Part1() {
	}

	/**
	 * method main start three threads and sleep 1/3s(333)
	 * 
	 * and works 1s(1000)
	 *
	 * @author chekhunov
	 * @throws InterruptedException 
	 *
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread myThreadFirst = new MyThreadFirst();
		myThreadFirst.setName("myThreadFirst");
		

		Thread myThreadTwo = new Thread(new MyThreadTwo());
		myThreadTwo.setName("myThreadTwo");
		
		try {
			myThreadFirst.start();
			myThreadFirst.join(1000);
			myThreadFirst.interrupt();
			System.err.println("Done Thread...\n");
			
			myThreadTwo.start();
			myThreadTwo.join(1000);
			myThreadTwo.interrupt();
			System.err.println("Done Runnable...\n");
			
			Thread.currentThread();
			
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName()
					+ " is interrupted in Part1.main()");
		}
		
	}

	/**
	 * extends class thread
	 *
	 * @author chekhunov
	 *
	 */
	static class MyThreadFirst extends Thread {

		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				System.out.println(Thread.currentThread().getName());
				try {
					sleep(333);
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName()
							+ " is interrupted");
				}
			}
		}
	}

	/**
	 * implements interfaces Runnable
	 *
	 * @author chekhunov
	 *
	 */
	static class MyThreadTwo implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(333);
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName()
							+ " is interrupted");
				}
			}

		}

	}


}