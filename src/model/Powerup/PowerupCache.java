package model.Powerup;

import model.Powerup.PowerupOptions.HealthRestore;

import java.util.ArrayList;
import java.util.Hashtable;

public class PowerupCache {
    private static Hashtable<String, PowerupVisitor> powerupMap = new Hashtable<String, PowerupVisitor>();



    private static final ArrayList<String> powerupList = new ArrayList<>();

    public static PowerupVisitor getPowerup(String powerUpName) {
        PowerupVisitor cachedPowerup = powerupMap.get(powerUpName);
        return (PowerupVisitor) cachedPowerup.clone();
    }

    // Run This as part of the board construction
    public static void loadCache() {
        // Default Valid Tiles
        HealthRestore healthRestorePowerup = new HealthRestore();
        powerupMap.put(HealthRestore.class.getSimpleName(), healthRestorePowerup);
        powerupList.add(HealthRestore.class.getSimpleName());
    }

    public static ArrayList<String> getPowerupList() {
        return powerupList;
    }
}
