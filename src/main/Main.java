package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	private static final int NUM_THREADS = 10;
	private static final int DEFAULT_SIZE = 100;
	/**
	 * @param args
	 */
	
	public static void help(){
		System.out.println("thumb - Generate thumbnails based on entropy.");
		System.out.println("Usage:");
		
		System.out.println("    thumb file1.png file2.png                  Generate square " + DEFAULT_SIZE + "x" + DEFAULT_SIZE + "  pixel thumbs");
		System.out.println("    thumb --size 50 file1.png file2.png        Generate square 50x50 pixel thumbs");
	}
	
	public static void main(String[] args) {
		
		if(args.length == 0){
			help();
			System.exit(0);
		}
		
		int size = DEFAULT_SIZE;
		int startArgs = 0;
		if(args[0].equals("--size")){
			size = Integer.parseInt(args[1]);
			startArgs = 2;
		}

		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		for(int i = startArgs; i < args.length; i++){
			executor.execute(new Worker(args[i], size));
		}
		
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
		
        System.exit(0);
	}

}
