package thread.executor;

import java.util.Random;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class RunnableMain {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "Thread-1");
        thread.start();
        thread.join();
        int result = task.value; //Runnable의 run 메서드는 값을 반환하지 않아서 직접 값을 구해야 하는 불편함이 존재한다.
        log("result value=" + result);
    }

    static class MyRunnable implements Runnable {

        int value;

        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);
            value = new Random().nextInt(10);
            log("create value=" + value);
            log("Runnable 완료");
        }
    }
}
