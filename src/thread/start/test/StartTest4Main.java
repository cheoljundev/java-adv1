package thread.start.test;

import static util.MyLogger.log;

public class StartTest4Main {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new CounterRunnable("A", 1000), "Thread-A");
        Thread thread2 = new Thread(new CounterRunnable("B", 500), "Thread-B");
        thread1.start();
        thread2.start();
    }

    static class CounterRunnable implements Runnable {
        private String content;
        private int ms;

        public CounterRunnable(String content, int ms) {
            this.content = content;
            this.ms = ms;
        }

        @Override
        public void run() {
            while (true) {
                log(content);
                try {
                    Thread.sleep(ms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
