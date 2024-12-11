package thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV6 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV6(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        try {
            if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                log("[진입 실패] 이미 처리중인 작업이 있습니다.");
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            //잔고가 출금액보다 적으면 진행 X
            log("[검증 시작] 출금액 : " + amount + " 잔고 : " + balance);
            if (amount > balance) {
                log("[검증 실패] 출금액 : " + amount + " 잔고 : " + balance);
                return false;
            }

            //잔고가 출금액보다 많으면 진행
            log("[검증 완료] 출금액 : " + amount + " 잔고 : " + balance);
            sleep(1000); // 출금 소요 시간
            balance -= amount;
            log("[출금 완료] 출금액 : " + amount + " 잔고 : " + balance);

        } finally {
            lock.unlock();
        }

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}