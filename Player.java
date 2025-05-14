import java.util.HashMap;
import java.util.Random;

public class Player {
    HashMap<String, Integer> status = new HashMap();
    Random rand = new Random();

    public Player() {
        status.put("Health", 80);
        status.put("Hunger", 50);
        status.put("Morale", 50);
        status.put("Ammo", 0);
        status.put("Food", 0);
        status.put("Fortifications", 0);
    }

    public void scavenge() { //get some food and ammo
        System.out.println("You search the area.");
        setStatus("Food", getStatus("Food") + 1 + rand.nextInt(2));
        setStatus("Ammo", getStatus("Ammo") + 2 + rand.nextInt(3));
        setStatus("Morale", getStatus("Morale") + 5);
    }
    public void rest() { //increase health and morale by a small bit
        System.out.println("You rest for a while.");
        setStatus("Health", getStatus("Health") + 5 + rand.nextInt(6));
        setStatus("Morale", getStatus("Morale") + 10);
        setStatus("Food", getStatus("Food") - 1); 
    }
    public void fortify() { //use small amount of food, increase fortifications 
        if (getStatus("Food") >= 1) {
            System.out.println("You reinforce your shelter.");
            setStatus("Fortifications", getStatus("Fortifications") + 2);
            setStatus("Food", getStatus("Food") - 1);
        } else {
            System.out.println("You're too hungry to do this right now.");
        }
    }
    public void setStatus(String playerStatus, int newValue) {
        status.put(playerStatus, newValue);
    }

    public int getStatus(String playerStatus) {
        return status.get(playerStatus);
    }

    public void getPlayerStatus() {
        System.out.println("Health: " + getStatus("Health"));
        System.out.println("Hunger: " + getStatus("Hunger"));
        System.out.println("Morale: " + getStatus("Morale"));
        System.out.println("Ammo: " + getStatus("Ammo"));
        System.out.println("Food: " + getStatus("Food"));
        System.out.println("Fortifications: " + getStatus("Fortifications"));
    }

}
