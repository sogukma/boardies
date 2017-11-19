package testingarea;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Threads{

	static class Zaehlen implements Runnable{
		
		
		
		@Override
		public void run() {
			for(int i = 0; i < 10; i++)
			{
				System.out.println(Thread.currentThread().getName()+ " z�hlt: "+ i);

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}

	
	public static void main(String args[])
	{
		System.out.println("---------START");
		ExecutorService executor = Executors.newFixedThreadPool(2);
			
		Thread tr = new Thread(new Zaehlen());
		Thread tr2 = new Thread(new Zaehlen());
		
		tr.setName("Client 1");
		tr2.setName("Client 2");
		
		//Thread starten
		//tr.start();
		//tr2.start();
		executor.execute(tr);
		executor.execute(tr2);
		executor.shutdown();
		
		try {
			//auf sterben von Threads warten
			//tr.join();
			//tr2.join();
			while(!executor.awaitTermination(1, TimeUnit.SECONDS))
			{
				System.out.println("System l�uft noch.");
			}

			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("---------END");
		
	}

	
}
