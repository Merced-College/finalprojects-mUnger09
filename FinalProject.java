//Matias Unger-Ramirez
//05.19.25
//My program is a survival/resource management game. 

import java.util.Random;
import java.util.Scanner;

public class FinalProject {
    //Main method, handles game loop
    public static void main(String[] args) {
        Player player = new Player();
        Scanner scnr = new Scanner(System.in);
        int action;
        int day = 1;
        int maxDays = 15;
        int actionsLeft;
        Random rand = new Random();
        Event[] events = EventCreator.getSampleEvents(); //Creates list of all possible events from EventCreator.java
        System.out.println("Insert generic zombie outbreak story");
        while (!isDead(player)) { //Main game loop
            actionsLeft = 2; //2 actions per day
            System.out.println("--------------------------");
            System.out.println("You awake on day " + day + ".");
            System.out.println("--------------------------");
            player.getPlayerStatus();   
            System.out.println("--------------------------");
            if (player.getStatus("Hunger") <= 85 && player.getStatus("Food") > 0) { //If you have food, lose 1 and gain 15 hunger
                player.setStatus("Hunger", player.getStatus("Hunger") + 15);
                player.setStatus("Food", player.getStatus("Food") - 1);
            } else { //If you don't have food, lose 10 hunger
                player.setStatus("Hunger", player.getStatus("Hunger") - 10); 
                System.out.println("You are starving!");
            }
            if (player.getStatus("Hunger") <= 0) { //if you are at or below 0 hunger, you start starving.
                player.setStatus("Health", player.getStatus("Health") - 25);
                player.setStatus("Morale", player.getStatus("Morale") - 10);
            }
            while (actionsLeft > 0 && !isDead(player)) {//Action loop
                System.out.println("You have " + actionsLeft + " actions left today. (Type corresponding number)");
                System.out.println("1. Scavenge");
                System.out.println("2. Rest");
                System.out.println("3. Fortify");
                System.out.println("4. Get player info (doesn't use an action)");
                action = scnr.nextInt();
                switch (action) {//Player actions
                    case 1:
                        player.scavenge();
                        break;
                    case 2:
                        player.rest();
                        break;
                    case 3:
                        if (player.getStatus("Food") >= 1) {
                            player.fortify();
                        } else {
                            System.out.println("You are too hungry to do this right now.");
                            actionsLeft++;
                        }
                        break;
                    case 4:
                        System.out.println("You take a look in the mirror.");
                        player.getPlayerStatus();
                        actionsLeft++;
                        break;
                    default:
                        System.out.println("Not a valid action.");
                        actionsLeft++;
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
            if (player.getStatus("Fortifications") <= 0) {//Sets zero to 0 or your current fortification value
                zero = 0;
            } else {
                zero = player.getStatus("Fortifications");
            }
            if (rand.nextInt(10 + zero) == 1) {//10% chance for zombie attack with 0 fortification, 5% at 20 fortification
                Event e = events[4];
                System.out.println("Random Event! " + e.getDescription());
                e.applyEvent(player);
            }
            if (day >= maxDays) {//End
                System.out.println("After " + maxDays + " days, a military helicopter flies over your area. You signal to it, and are rescued.");
                break;
            }
            day++;

        }
        if (isDead(player)) {
            System.out.println("You died after " + day + " days.");
        }
    }

    public static boolean isDead(Player player) {//Checks to see if the player is dead or has lost all morale
        return player.getStatus("Health") <= 0 || player.getStatus("Morale") <= 0;
    }
}
