package thread.start;

public class BadThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");
        HelloThread helloThread = new HelloThread();

        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
        helloThread.run(); // 이러면 main 스레드에서 run() 실행.
        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
