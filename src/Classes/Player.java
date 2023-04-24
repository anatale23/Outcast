package Classes;

import java.util.ArrayList;

public class Player {
    //-----------------name-----------------
    private static String name;

    public static void setName(String newName) {
        name = newName;
    }

    public static String getName() {
        return name;
    }

    //--------------position----------------
    private static int[] playerPosition = {0, 0};

    public static void setPlayerPosition(int[] newPlayerPosition) {
        playerPosition = newPlayerPosition;
    }

    public static int[] getPlayerPosition() {
        return playerPosition;
    }

    //--------------------inventory----------------------
    private static ArrayList<String> inventory;
    public static void setInventory(ArrayList<String> newInventory) {

        inventory = newInventory;
    }

    public static ArrayList<String> getInventory() {

        return inventory;
    }

    //------------------collectableItems-----------------

    private static String[] collectableItems = {"salt", "map", "rope"};

    public static String[] getCollectableItems() {
        return collectableItems;
    }

    public void askName(Player player) {
    }
}





