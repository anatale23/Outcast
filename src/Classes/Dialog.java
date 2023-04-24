package Classes;

import java.util.Scanner; //Scanner class for input
import java.util.HashMap; //Key-Value WorldMap class
import java.util.Arrays; //Class for dealing with arrays

public class Dialog {
    private Player player;
    public static boolean exit;
    public static int promptLen;
    public Dialog (Player player) {
        this.player = player;
    }
    public static void askName(Player player) {
        Scanner nameInput = new Scanner(System.in);
        System.out.print("Your name: ");
        String name = nameInput.nextLine();
        player.setName(name);
        System.out.println("Good luck, " + name);
        System.out.println("Let your journey begin");
    }

    public static void dialog(Player player) {
        int pRow = player.getPlayerPosition()[0];
        int pCol = player.getPlayerPosition()[1];

            String directions[] = new String[] {"n","e","w","s","north", "east", "west", "south"}; //String array for directions
            HashMap<String,String[]> mapCorr = new HashMap<>(); //WorldMap declaration to correlate actions with objects
            mapCorr.put("go", directions); //WorldMap storing of "go" key and directions string array value
            //while (exit==false){
                System.out.print("What to do: ");
                Scanner playerInput = new Scanner (System.in);
                String prompt = playerInput.nextLine();
                String words[] = prompt.toLowerCase().split(" ");
                promptLen = words.length;
                if (promptLen==1){
                    if (words[0].equals("exit")){exit=true;}
                    if (words[0].equals("inv")||words[0].equals("inventory")){
                        System.out.print("This is what you have: ");
                        try {
                            for (String item : player.getInventory()) {
                                System.out.print(item + ", ");
                            }
                            System.out.println("\b\b.");
                        }catch (NullPointerException e){
                            System.out.println("nothing.");
                        }
                    }

                }
                if(promptLen>1){
                for (int i = 0; i<promptLen; i++){
                    System.out.println(words[i]);
                }

                //if (words[0].equals("get")){
                //    if (Arrays.asList(word[1]).contains(word[1]))
                    //check if word[1] is both in the player's location and in collectableItems
                    //if positive, remove the element from the scene and put it in inventory
                    //if negative, write the phrase "you can't do that"
                //}
                if (words[0].equals("go")) {
                    if (Arrays.asList(mapCorr.get("go")).contains(words[1])) {
                        if (words[1].charAt(0) == 'n') {
                            pRow--;
                            if (pRow < 0) {
                                pRow = 0;
                            }
                        }
                        ;
                        if (words[1].charAt(0) == 's') {
                            pRow++;
                            if (pRow > 15) {
                                pRow = 15;
                            }
                        }
                        ;
                        if (words[1].charAt(0) == 'w') {
                            pCol--;
                            if (pCol < 0) {
                                pCol = 0;
                            }
                        }
                        ;
                        if (words[1].charAt(0) == 'e') {
                            pCol++;
                            if (pCol > 20) {
                                pCol = 20;
                            }
                        }
                        ;
                        System.out.println("Let's go " + words[1]);
                        player.setPlayerPosition(new int[]{pRow, pCol});
                    } else {
                        System.out.println("I didn't understand where you want to go");
                    }
                }
                //}
            }
        }
    }




