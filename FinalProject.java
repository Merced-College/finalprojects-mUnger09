//import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class FinalProject {
    public static void main(String[] args) {
        System.out.println("Started");
        Player player = new Player();
        Scanner scnr = new Scanner(System.in);
        int action;
        int day = 1;
        int actionsLeft;
        Random rand = new Random();
        Event[] events = EventCreator.getSampleEvents();
        System.out.println("Insert generic zombie outbreak story");
        while (!dead(player)) { //Main game loop
            actionsLeft = 2; //2 actions per day
            System.out.println("--------------------------");
            System.out.println("You awake on day " + day + ".");
            System.out.println("--------------------------");
            player.getPlayerStatus();
            System.out.println("--------------------------");
            if (player.getStatus("Hunger") <= 85 && player.getStatus("Food") > 0) { //If you have food, lose 1 and gain 15 hunger
                player.setStatus("Hunger", player.getStatus("Hunger") + 15);
                player.setStatus("Food", player.getStatus("Food") - 1);
            }
            else { //If you don't have food, lose 10 hunger
                player.setStatus("Hunger", player.getStatus("Hunger") - 10); 
            }
            
            if (player.getStatus("Hunger") < 0) { //if you are below 0 hunger, you start starving.
                player.setStatus("Health", player.getStatus("Health") - 10);
                player.setStatus("Morale", player.getStatus("Morale") - 10);
            }
            while (actionsLeft > 0 && !dead(player)) {
                System.out.println("You have " + actionsLeft + " actions left today. (Type corresponding number)");
                System.out.println("1. Scavenge");
                System.out.println("2. Rest");
                System.out.println("3. Fortify");
                System.out.println("4. Get player info (doesn't use an action)");
                action = scnr.nextInt();
                switch (action) {
                    case 1:
                        player.scavenge();
                        break;
                    case 2:
                        player.rest();
                        break;
                    case 3:
                        player.fortify();
                        break;
                    case 4:
                        System.out.println("You take a look in the mirror.");
                        player.getPlayerStatus();
                        actionsLeft++;
                        break;
                    default:
                        System.out.println("Not a valid action.");
                        continue;
                }
                actionsLeft--;
            }
            if (rand.nextInt(100) <= 40 + (int)(player.getStatus("Morale")/10)) {//random event
                Event e = events[rand.nextInt(events.length - 1)];
                System.out.println("Random Event! " + e.getDescription());
                e.applyEvent(player);
            }
            int zero;
            if (player.getStatus("Fortifications") < 0) {
                zero = 0;
            } else {
                zero = player.getStatus("Fortifications");
            }
            if (rand.nextInt(10 + zero) == 1) {//10% chance for zombie attack with 0 fortification, 5% at 20 fortification
                Event e = events[4];
                System.out.println("Random Event! " + e.getDescription());
                e.applyEvent(player);
            }
            if (day >= 15) {
                System.out.println("After 15 days, a military helicopter flies over your area. You signal to it, and are rescued.");
                break;
            }
            day++;

        }
        if (dead(player)) {
            System.out.println("You died after " + day + " days.");
        }
    }

    public static boolean dead(Player player) {
        return player.getStatus("Health") <= 0 && player.getStatus("Hunger") <= 0 && player.getStatus("Morale") <= 0;
    }
}
