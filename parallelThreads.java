import java.util.Random;

/** Represents solving of array sum with parralelism
 * @author Leon Silas
 * @author www.github.com/leonsilas
 * @version 1.0
*/

public class parallelThreads {

    //keeps threads in check
    private static int multipleTotal = 0;
    private static int totalOne = 0;
    private static int totalTwo = 0;

    public static synchronized void totalUp(int nextNum) {
        multipleTotal += nextNum;
    }
    //main
	public static void main(String[] args) {

        //max value and randomizer
        long startTime = System.nanoTime();
        final int MAX_VALUE = 200000000;
        Random rand = new Random();

        //array creation and filling
        Integer [] bigArray = new Integer[MAX_VALUE];
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = rand.nextInt(10) + 1;
        }

        //single thread sum case [display sum and save compute time]
        Thread t1 = new Thread (new Runnable() {
            public void run() {
                long startTime = System.nanoTime();
                int singleTotal = 0;
                for (int i = 0; i < bigArray.length; i++) {
                    singleTotal += bigArray[i];
                }
                System.out.println("The total calculated using a single thread is: " + singleTotal);
                System.out.println("\tCompleted in " + (System.nanoTime() - startTime) + " nanoseconds.");
            }
        });

        //multiple thread sum case [display sum and save compute time]
        
        Thread t2 = new Thread (new Runnable() {
            public void run() {
                for (int i = bigArray.length - 1; i > bigArray.length / 2; i--) {
                    totalUp(bigArray[i]);
                }
            }
        });
        Thread t3 = new Thread (new Runnable() {
            public void run() {
                for (int i = 0; i <= bigArray.length / 2; i++) {
                    totalUp(bigArray[i]);
                }
            }
        });

        //begin sequence for single
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //begin sequence for multiple
        startTime = System.nanoTime();
        t2.start();
        t3.start();
        try {
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //output
        System.out.println("The total calculated using multiple threads WITH SYNCHRONIZATION is: " + multipleTotal);
        System.out.println("\tCompleted in " + (System.nanoTime() - startTime) + " nanoseconds.");

        //multiple thread METHOD 2
        
        Thread t4 = new Thread (new Runnable() {
            public void run() {
                for (int i = bigArray.length - 1; i > bigArray.length / 2; i--) {
                    totalOne += bigArray[i];
                }
            }
        });
        Thread t5 = new Thread (new Runnable() {
            public void run() {
                for (int i = 0; i <= bigArray.length / 2; i++) {
                    totalTwo += bigArray[i];
                }
            }
        });

        //begin sequence for multiple part 2
        startTime = System.nanoTime();
        t4.start();
        t5.start();
        try {
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int bigTotal = totalOne + totalTwo;

        //output 2 electric boogaloo
        System.out.println("The total calculated using multiple threads WITHOUT SYNCHRONIZATION is: " + bigTotal);
        System.out.println("\tCompleted in " + (System.nanoTime() - startTime) + " nanoseconds.");

	}//end of main

}// end of parallelThreads
