//Matias Unger-Ramirez
//05.20.25
//My program is a survival/resource management game. 

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FinalProject {
    //Main method, handles game loop
    public static void main(String[] args) {
        Player player = new Player();
        Scanner scnr = new Scanner(System.in);
        int action;
        int day = 1;
        int actionsLeft;
        ArrayList<ArrayList<String>> summary = new ArrayList<>();
        Random rand = new Random();
        Event[] events = EventCreator.getSampleEvents(); //Creates list of all possible events from EventCreator.java
        System.out.println("Insert generic zombie outbreak story");
        System.out.println("How many days do you think you can survive? ");
        int maxDays = scnr.nextInt();
        while (!isDead(player)) { //Main game loop
            actionsLeft = 2; //2 actions per day
            System.out.println("--------------------------");
            System.out.println("You awake on day " + day + ".");
            System.out.println("--------------------------");
            player.getPlayerStatus();   
            System.out.println("--------------------------");
            summary.add(new ArrayList<String>());
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
                        summary.get(day - 1).add("You scavenged for supplies.");
                        break;
                    case 2:
                        player.rest();
                        summary.get(day - 1).add("You rested.");
                        break;
                    case 3:
                        if (player.getStatus("Food") >= 1) {
                            player.fortify();
                            summary.get(day - 1).add("You fortified your shelter.");
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
                summary.get(day - 1).add(e.getDescription());
            } else {
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
                summary.get(day - 1).add(e.getDescription());
                } else {
                    summary.get(day - 1).add("Nothing else happened today.");
                }
            }
            
            if (day >= maxDays) {//End
                System.out.println("After " + maxDays + " days, a military helicopter flies over your area. You signal to it, and are rescued.");
                summary.get(day - 1).add("You are rescued.");
                break;
            }
            day++;

        }
        if (isDead(player)) {
            System.out.println("You died after " + day + " days.");
        }
        System.out.println("Do you want a summary of this character? (1 = yes, 2 = no)");
        action = scnr.nextInt();
        if (action == 1) { //This prints out a summary of your actions
            for (int i = 0; i < summary.size(); i++) {
                System.out.println("Day " + (i + 1));
                for (String happening : summary.get(i)) {
                    System.out.println(happening);
                }
                System.out.println();
            }
            System.out.println("Input a day to search for. (0 to end)");
            int searchDay = -1;
            while (searchDay != 0) {//Searches for a specific day and prints out the events of that day
                System.out.print("Day: ");
                searchDay = scnr.nextInt();
                if (searchDay == 0) {
                    break;
                } else if (searchDay < 0 || searchDay > summary.size()) {
                    System.out.println("Invalid day. Please enter a number between 1 and " + summary.size());
                } else {
                    System.out.println("Summary for Day " + searchDay + ":");
                    for (String log : summary.get(searchDay - 1)) {
                        System.out.println(log);
                    }
                    System.out.println();
                }
            }
        }
        
    }

    public static boolean isDead(Player player) {//Checks to see if the player is dead or has lost all morale
        return player.getStatus("Health") <= 0 || player.getStatus("Morale") <= 0;
    }
}
