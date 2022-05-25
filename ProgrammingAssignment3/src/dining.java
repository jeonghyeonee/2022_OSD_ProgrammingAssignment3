// Use java threads to simulate the Dining Philosophers Problem
// YOUR NAME HERE.  Programming assignment 2 (from ece.gatech.edu) */


class dining {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Starting the Dining Philosophers Simulation\n");
        miscsubs.InitializeChecking();
        // Your code here...

        Philosopher[] philosophers = new Philosopher[miscsubs.NUMBER_PHILOSOPHERS];

        PhilosopherMonitor monitor = new PhilosopherMonitor(miscsubs.NUMBER_PHILOSOPHERS);


        for (int i = 0; i < miscsubs.NUMBER_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, monitor);
            new Thread(philosophers[i]).start();
        }

        while(miscsubs.TotalEats < miscsubs.MAX_EATS){
        }

        System.out.println();

        // End of your code
        System.out.println("Simulation Ends..");
        miscsubs.LogResults();
        }

}

class Philosopher implements Runnable{

    private int id;
    private PhilosopherMonitor monitor;

    public Philosopher(int id, PhilosopherMonitor monitor) {
        this.id = id;
        this.monitor = monitor;
    }


    public void run(){
        try{
            while(miscsubs.TotalEats < miscsubs.MAX_EATS){
                think(); // thinking
                monitor.pickUp(id);     // eating start
                eat(id);
                monitor.putDown(id);    // eating finish
            }
        } catch(InterruptedException e){
            System.out.println("Philosopher " + id + " was interrupted.\n");
        }

    }

    
    private void think(){
        miscsubs.RandomDelay();
    }


    public void eat(int id){
        miscsubs.StartEating(id);
        miscsubs.RandomDelay();
        miscsubs.DoneEating(id);
    }

}

class PhilosopherMonitor{
    private enum State {THINKING, HUNGRY, EATING};

    private State[] philosopherState;

    public PhilosopherMonitor(int num){
        philosopherState = new State[num];
        for (int i = 0; i < num; i++){
            philosopherState[i] = State.THINKING;
        }
    }

    public synchronized void pickUp(int id) throws InterruptedException{
        philosopherState[id] = State.HUNGRY;

        while(neighborEating(id)){
            wait();
        }
        philosopherState[id] = State.EATING;
    }


    private boolean neighborEating(int id){
        int num = philosopherState.length;
        if(philosopherState[(id + 1) % num] == State.EATING){
            return true;
        }

        if(philosopherState[(id + num - 1) % num] == State.EATING){
            return true;
        }

        return false;
    }

    public synchronized void putDown(int id) throws InterruptedException{
        philosopherState[id] = State.THINKING;
        notifyAll();
    }
}