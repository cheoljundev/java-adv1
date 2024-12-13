package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMainV4 {

    //static final int TASK_SIZE = 1100; //1.일반
    //static final int TASK_SIZE = 1200; //2.긴급
    static final int TASK_SIZE = 1201; //3.거절

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(100, 200,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000)); // 반드시 ArrayBlockingQueue를 사용해서 제한해야만 max 스레드까지 늘어난다. LinkedBlockingQueue는 capacity가 무제한이므로, 큐에 쌓이기만 하고 기본 스레드로 작업한다.
        printState(es);

        long startMs = System.currentTimeMillis();

        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;
            try {
                es.execute(new RunnableTask(taskName));
                printState(es, taskName);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }

        es.close();
        long endMs = System.currentTimeMillis();
        log("time: " + (endMs - startMs) + "ms");


    }
}
