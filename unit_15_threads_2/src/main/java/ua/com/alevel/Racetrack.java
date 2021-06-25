package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

public class Racetrack {

    private List<Horse> horses = new ArrayList<>();
    private final Map<String, Integer> raceResult;
    private final AtomicInteger rankedPlaceInRace;

    public Racetrack() {
        raceResult = new ConcurrentHashMap<>();
        rankedPlaceInRace = new AtomicInteger(1);
    }

    public int getPlaceTakenInRace(String name) {
        return raceResult.get(name);
    }

    public void addHorse(Horse horse) {
        this.horses.add(horse);
    }

    public synchronized void startRace() {
        for (Horse horse : horses) {
            new Thread(horse).start();
        }
    }

    static class Horse implements Runnable {

        private String name;
        private final Phaser phaser;
        private Racetrack racetrack;

        public Horse(String name, Racetrack racetrack) {
            this.racetrack = racetrack;
            racetrack.addHorse(this);
            this.name = name;
            this.phaser = new Phaser();
            phaser.register();
        }

        @Override
        public void run() {
            int distance = 0;
            phaser.arriveAndAwaitAdvance();
            while (true) {
                distance += distanceAndMillisecCalculationRandom(100, 200);
                if (distance >= 1000) {
                    racetrack.raceResult.put(this.name, racetrack.rankedPlaceInRace.getAndIncrement());
                    System.out.println(getName() + " has finished the race!");
                    phaser.arriveAndDeregister();
                    break;
                }
                try {
                    Thread.sleep(distanceAndMillisecCalculationRandom(400, 500));
                } catch (InterruptedException e) {
                    System.out.println("Current thread loop interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        }

        private int distanceAndMillisecCalculationRandom(int min, int max) {
            Random random = new Random();
            return random.nextInt((max + 1) - min) + min;
        }

        public String getName() {
            return name;
        }
    }
}
