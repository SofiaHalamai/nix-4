package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Racetrack racetrack = new Racetrack();
        List<Racetrack.Horse> horses = new ArrayList<>();
        horses.add(new Racetrack.Horse("Karl", racetrack));
        horses.add(new Racetrack.Horse("Lucky", racetrack));
        horses.add(new Racetrack.Horse("Velvet", racetrack));
        horses.add(new Racetrack.Horse("Devil", racetrack));
        horses.add(new Racetrack.Horse("Fighter", racetrack));
        horses.add(new Racetrack.Horse("Star", racetrack));

        int bet;
        System.out.println("==============");
        System.out.println("||HORSE RACE||");
        System.out.println("==============");
        for (int i = 0; i < horses.size(); i++) {
            System.out.println((i + 1) + ". " + horses.get(i).getName());
        }
        do {
            System.out.println("Enter the number of horse:");
            bet = input.nextInt();
        } while (bet < 1 || bet > horses.size());
        System.out.println("Race begins in:");
        int num3 = 3;
        for (int i = 1; i <= num3; num3--) {
            System.out.println(num3);
            Thread.sleep(1000);
        }
        racetrack.startRace();
        Thread.sleep(4000);
        System.out.println("The selected horse finished in place: " + racetrack.getPlaceTakenInRace(horses.get(bet-1).getName()));
    }
}