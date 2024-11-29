package thread.control;

import util.ThreadUtils;

import static util.ThreadUtils.*;

public class CheckedExceptionMain {
    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedRunnable implements Runnable {
        @Override
        public void run() /*throws Exception*/{
            // throw new Exception();
            // Runnable 인터페이스에 Exception이 throws되어 있지 않아 체크 예외를 던질 수 없다.
            sleep(1000);
        }
    }
}
