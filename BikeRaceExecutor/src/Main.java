import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int ridersCount = 3;
        int totalDistance = 1000; // meters

        RaceManager raceManager = new RaceManager(ridersCount, totalDistance);

        List<Rider> riders = new ArrayList<>();
        riders.add(new Rider("Rider-1", totalDistance));
        riders.add(new Rider("Rider-2", totalDistance));
        riders.add(new Rider("Rider-3", totalDistance));

        raceManager.registerRiders(riders);
        raceManager.startRace();
    }
}
