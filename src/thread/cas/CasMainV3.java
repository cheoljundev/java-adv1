package thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CasMainV3 {

    private static final int THREAD_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println("start value = " + atomicInteger.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = atomicInteger.get();

        System.out.println(atomicInteger.getClass().getSimpleName() + " resultValue = " + result);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;

        do {
            getValue = atomicInteger.get(); // 0
            sleep(100); // 스레드 동시 실행을 위한 대기
            log("getValue: " + getValue); // 0
            result = atomicInteger.compareAndSet(getValue, getValue+1); // 값이 바뀌었는지 0인지 체크해서 값 변경
            log("result: " + result);
        } while (!result);

        // return atomicInteger.get()은, 다른 스레드가 그 사이 값을 변경할 수 있으므로 getValue+1을 사용.
        return getValue+1;
    }
}
