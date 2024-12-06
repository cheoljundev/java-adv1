package thread.sync.test;

public class Immutable {

    // 이 경우 공유자원임에도, 동시성 문제가 발생하지 않음.
    // 변경하지 않아서.

    private final int value;

    public Immutable(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
