//Matias Unger-Ramirez
//05.19.25
//My program is a survival/resource management game. 

public class EventCreator {
    public static Event[] getSampleEvents() {//Creates all random events and assigns them the values that affect the player
        Event[] events = new Event[5];

        Event e1 = new Event();
        e1.setDescription("A group of survivors raid your shelter!");
        e1.setHealthImpact(-20);
        e1.setMoraleImpact(-10);
        e1.setFortificationImpact(-4);
        e1.setFoodChange(80);
        e1.setAmmoChange(80);
        e1.setFoodIsPercent(true);
        e1.setAmmoIsPercent(true);
        events[0] = e1;

        Event e2 = new Event();
        e2.setDescription("You found an abandoned store with supplies.");
        e2.setHealthImpact(0);
        e2.setFoodChange(10);
        e2.setAmmoChange(5);
        e2.setMoraleImpact(5);
        events[1] = e2;

        Event e3 = new Event();
        e3.setDescription("A survivor passes by and boosts your morale.");
        e3.setMoraleImpact(15);
        events[2] = e3;

        Event e4 = new Event();
        e4.setDescription("Your shelter's roof leaks, damaging fortifications.");
        e4.setFortificationImpact(-5);
        e4.setMoraleImpact(-5);
        events[3] = e4;

        Event e5 = new Event();
        e5.setDescription("You are attacked by a horde of zombies.");
        e5.setFortificationImpact(-5);
        e5.setMoraleImpact(-15);
        e5.setAmmoChange(70);
        e5.setHealthImpact(-15);
        e5.setAmmoIsPercent(true);
        events[4] = e5;

        return events;
    }
}
