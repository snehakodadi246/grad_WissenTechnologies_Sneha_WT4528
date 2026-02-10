import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class RaceManager {

    private final ExecutorService executor;
    private final CountDownLatch startSignal;
    private final List<Future<RaceResult>> results;
    private final int totalDistance;

    public RaceManager(int ridersCount, int totalDistance) {
        this.executor = Executors.newFixedThreadPool(ridersCount);
        this.startSignal = new CountDownLatch(1);
        this.results = new ArrayList<>();
        this.totalDistance = totalDistance;
    }

    public void registerRiders(List<Rider> riders) {
        for (Rider rider : riders) {
            rider.setStartSignal(startSignal);
            rider.setRaceDistance(totalDistance);
            results.add(executor.submit(rider));
        }
    }

    public void startRace() throws InterruptedException {
        System.out.println(" Race starting in...");
        for (int i = 3; i >= 1; i--) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("GO ");
        startSignal.countDown(); // all riders start together
    }

    public void showResults() {
        System.out.println("\n Final Results");
        for (Future<RaceResult> f : results) {
            try {
                System.out.println(f.get());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void endRace() {
        executor.shutdown();
        System.out.println(" Race finished");
    }
}
