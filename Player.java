//Matias Unger-Ramirez
//05.19.25
//My program is a survival/resource management game. 

import java.util.HashMap;
import java.util.Random;

public class Player {
    HashMap<String, Integer> status = new HashMap<String,  Integer>();
    Random rand = new Random();

    public Player() {//Initializes Player with the following stats
        status.put("Health", 80);
        status.put("Hunger", 50);
        status.put("Morale", 50);
        status.put("Ammo", 0);
        status.put("Food", 0);
        status.put("Fortifications", 0);
    }

    public void scavenge() { //get some food and ammo
        int newAmmo = 2 + rand.nextInt(3);
        int newFood = 1 + rand.nextInt(2);
        System.out.println("You search the area and find: ");
        System.out.println(newFood + " food");
        System.out.println(newAmmo + " ammo");
        setStatus("Food", getStatus("Food") + newFood);
        setStatus("Ammo", getStatus("Ammo") + newAmmo);
        setStatus("Morale", getStatus("Morale") + 5);
    }
    public void rest() { //increase health and morale by a small bit
        System.out.println("You rest for a while.");
        setStatus("Health", getStatus("Health") + 5 + rand.nextInt(6));
        setStatus("Morale", getStatus("Morale") + 10);
        setStatus("Food", getStatus("Food") - 1); 
    }
    public void fortify() { //use small amount of food, increase fortifications 
        System.out.println("You reinforce your shelter.");
        setStatus("Fortifications", getStatus("Fortifications") + 2);
        setStatus("Food", getStatus("Food") - 1);
    }
    public void setStatus(String playerStatus, int newValue) {//Sets a specific player status
        status.put(playerStatus, newValue);
    }

    public int getStatus(String playerStatus) {//Returns a specific status
        return status.get(playerStatus);
    }

    public void getPlayerStatus() {//Prints out every status of Player
        System.out.println("Health: " + getStatus("Health"));
        System.out.println("Hunger: " + getStatus("Hunger"));
        System.out.println("Morale: " + getStatus("Morale"));
        System.out.println("Ammo: " + getStatus("Ammo"));
        System.out.println("Food: " + getStatus("Food"));
        System.out.println("Fortifications: " + getStatus("Fortifications"));
    }

}
