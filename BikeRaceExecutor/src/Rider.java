import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class Rider implements Callable<String> {

    private final String name;
    private final CountDownLatch latch;

    public Rider(String name, CountDownLatch latch) {
        this.name = name;
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        latch.await(); // all riders start together

        int distance = 0;
        while (distance < 1000) {
            int speed = 50 + (int)(Math.random() * 100);
            distance += speed;
            Thread.sleep(200);
        }
        return name + " finished the race";
    }
}
