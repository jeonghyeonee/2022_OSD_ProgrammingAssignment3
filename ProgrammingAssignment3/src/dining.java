// Use java threads to simulate the Dining Philosophers Problem
// YOUR NAME HERE.  Programming assignment 2 (from ece.gatech.edu) */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

class dining
{
    public static Philosopher[] philosopher = new Philosopher[5];
    public static void main(String args[])
    {
        System.out.println("Starting the Dining Philosophers Simulation\n");
        miscsubs.InitializeChecking();
        // Your code here...

        ArrayList<Thread> threadArr = new ArrayList<>();

        for (int i=0; i<5; i++){
            Thread thread = new Thread(new Philosopher(i));
            threadArr.add(thread);
        }









        // End of your code
        System.out.println("Simulation Ends..");
        miscsubs.LogResults();
    }

    public static class Philosopher implements Runnable{

        public int[] chopstick = new int[5];

        static Object both = new Object();
        int id;

        Philosopher(int i){
            id = i;
        }


        @Override
        public void run() {

        }
    }

};


