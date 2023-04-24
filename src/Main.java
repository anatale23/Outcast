import Classes.*;

public class Main {


    public static void main(String[] args) {

        Player player = new Player();
        WorldMap map = new WorldMap(player);
        Dialog dialog = new Dialog(player);
        int pRow;
        int pCol;

        // this array contains pointers for arrays like areaNames and areaDescriptions
        int[][] pPointer;
        // this array contains area names
        String[] areaNames = map.getAreaNames();
        // this array contains area descriptions
        String[] areaDescription = map.getAreaDescription();

        //print the graphic version of the game's name
        Title.printTitle();
        //print the game's introductory description
        Title.printDescription();
        //variable for exiting the game
        map.populateItems();
        boolean exit = false;
        dialog.askName(player);
        Title.timer(2000);


        int day = 0;

        while (dialog.exit == false) {
            day++;
            map.printMap(player);
            Title.timer(2000);
            System.out.println("DAY " + day + ":");
            pRow = player.getPlayerPosition()[0];
            pCol = player.getPlayerPosition()[1];
            pPointer = map.getParagraphPointer();
            System.out.println(areaNames[pPointer[pRow][pCol]]);
            System.out.println(areaDescription[pPointer[pRow][pCol]]);
            System.out.println();
            map.printObjects(player);
            //here i want a list of stable objects, visible and invisible. The visible objects are in a list.
            //the invisible objects move to the visible objects when discovered.
            //some of the objects are collectable some others not. When collected, they are removed from the list
            //and enter in the inventory. objects discarted from the inventory go in the list of the specific room.
            //Add extra things you can start to interact with. Like particular movable objects, plants, animals, species.

            map.whatYouSee(player);
            dialog.dialog(player);
        }
    }
}

