package com.mylearning.multithreading;

public class OddEvenMonitor {
    public static final boolean ODD_TURN = true;
    public static final boolean EVEN_TURN = false;
    private boolean turn = ODD_TURN;

    // Need synchronized in order to call wait() so that the key which was hold could be released,
    // see http://stackoverflow.com/questions/2779484 for discussion
    public synchronized void waitTurn(boolean oldTurn) {
        while (turn != oldTurn) {
            try {
                wait();
            } catch(InterruptedException e) {
                System.out.println("InterruptedException in wait(): " + e);
            }
        }
        //Move on, it’s our turn.
    }

    // Need synchronized in order to call notify()
    public synchronized void toggleTurn() {
        // true(1)  ^ true(1) = false(0)
        // false(0) ^ true(1) = true(1)
        turn ^= true;
        notify();
    }

    public static class OddThread extends Thread {
        private final OddEvenMonitor monitor;

        public OddThread(OddEvenMonitor monitor) {
            this.monitor = monitor;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 100; i += 2) {
                monitor.waitTurn(OddEvenMonitor.ODD_TURN);
                System.out.println("i = " + i);
                monitor.toggleTurn();
            }
        }
    }

    public static class EvenThread extends Thread {
        private final OddEvenMonitor monitor;

        public EvenThread(OddEvenMonitor monitor) {
            this.monitor = monitor;
        }

        @Override
        public void run() {
            for (int i = 2; i <= 100; i += 2){
                monitor.waitTurn(OddEvenMonitor.EVEN_TURN);
                System.out.println("i = " + i);
                monitor.toggleTurn();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OddEvenMonitor monitor = new OddEvenMonitor();
        Thread t1 = new OddThread(monitor);
        Thread t2 = new EvenThread(monitor);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

