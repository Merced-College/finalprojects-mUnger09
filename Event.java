public class Event {
    private String eventDescription;
    private int healthImpact;
    private int fortificationImpact;
    private int moraleImpact;
    private int foodChange;
    private int ammoChange;
    private boolean foodIsPercent = false;
    private boolean ammoIsPercent = false;

    //setters
    public void setDescription(String description) {
        eventDescription = description;
    }

    public void setHealthImpact(int hpImpact) {
        healthImpact = hpImpact;
    }

    public void setMoraleImpact(int mImpact) {
        moraleImpact = mImpact;
    }
    
    public void setFortificationImpact(int fImpact) {
        fortificationImpact = fImpact;
    }
    
    public void setFoodChange(int foodImpact) {
        foodChange = foodImpact;
    }

    public void setAmmoChange(int ammoImpact) {
        ammoChange = ammoImpact;
    }
    
    //getters
    public String getDescription() {
        return eventDescription;
    }

    public int getHealthImpact() {
        return healthImpact;
    }

    public int getMoraleImpact() {
        return moraleImpact;
    }
    
    public int getFortificationImpact() {
        return fortificationImpact;
    }
    
    public int getFoodChange() {
        return foodChange;
    }

    public int getAmmoChange() {
        return ammoChange;
    }

    public void setFoodIsPercent(boolean b) {
        foodIsPercent = b;
    }
    
    public void setAmmoIsPercent(boolean b) {
        ammoIsPercent = b;
    }
    
    public void applyEvent(Player player) {
        player.setStatus("Health", player.getStatus("Health") + getHealthImpact());
        player.setStatus("Morale", player.getStatus("Morale") + getMoraleImpact());
        player.setStatus("Fortifications", player.getStatus("Fortifications") + getFortificationImpact());
        if (!foodIsPercent) {
            player.setStatus("Food", player.getStatus("Food") + getFoodChange());
        }else {
            player.setStatus("Food", player.getStatus("Food") * (getFoodChange()/100));
        }
        if (!foodIsPercent) {
            player.setStatus("Ammo", player.getStatus("Ammo") + getAmmoChange());
        }else {
            player.setStatus("Ammo", player.getStatus("Ammo") * (getAmmoChange()/100));
        }
    }
}