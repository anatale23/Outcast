package Classes;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
public class WorldMap {
    private Player player;
    public WorldMap(Player player) {
        this.player = player;
    }
    private static char playerIcon = 'Ѭ';
    private static boolean[][] disMap = new boolean[15][20];
    private static char[][] mapTiles= {
            {'▒','▒','▒','▒','▒','▒','▒','▒','▒','≈','≈','^','▒','▒','▒','▒','▒','▒','▒','▒'},
            {'▒','▒','▒','▒','▒','▒','▒','▒','≈','≈','^','Δ','^','▒','▒','▒','@','Δ','▒','▒'},
            {'▒','▒','▒','▒','▒','▒','▒','▒','^','Δ','Δ','Δ','Δ','^','▒','▒','▒','▒','▒','▓'},
            {'▒','▒','▒','Δ','▒','▒','▒','▒','▒','&','^','^','^','░','░','Δ','Δ','▓','▓','▓'},
            {'=','=','░','░','=','=','▒','▒','▒','▒','▒','▒','▒','▒','▒','≈','Δ','▓','▓','▓'},
            {'▒','▒','▒','=','=','&','▒','▒','▒','≈','#','≈','≈','@','▒','▒','▓','▓','▒','▒'},
            {'▒','▒','▒','▓','▓','▒','▒','▒','≈','≈','▒','▒','▒','▒','▒','▒','▒','▒','▒','▒'},
            {'▓','▒','▒','▓','▒','▒','≈','≈','▒','▒','▒','▒','▒','▒','▒','▒','▒','@','▓','▓'},
            {'▓','▓','▓','▒','▒','≈','▒','▒','▒','▒','Δ','Δ','▒','▒','▒','▓','▓','▓','▓','▓'},
            {'▓','▓','▒','▒','▒','≈','≈','▒','▒','≈','Δ','Δ','Δ','▒','▓','▓','▓','▓','▓','▓'},
            {'▓','▒','▒','▒','▒','▒','▒','≈','≈','▒','▒','Δ','▒','▒','▒','▒','▓','▓','▓','▓'},
            {'▒','▒','▒','▒','▒','@','≈','≈','▒','▒','▒','▒','░','░','░','░','░','░','▓','▓'},
            {'▒','▒','▒','▒','▒','▒','#','▒','▒','▒','░','░','░','░','░','░','░','░','░','░'},
            {'▒','▒','Δ','¥','Δ','░','░','≈','≈','▒','░','░','░','░','░','░','░','░','░','░'},
            {'Δ','Δ','Δ','Δ','Δ','░','░','≈','≈','≈','▒','░','░','░','░','░','░','░','░','░'}};

    private static int row = mapTiles.length;
    private static int col = mapTiles[0].length;
    private static int[][] paragraphPointer= {
            {1,1,1,1,1,1,1,1,1,2,2,3,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,2,2,3,4,3,1,1,1,5,4,1,1},
            {1,1,1,1,1,1,1,1,3,4,4,4,4,3,1,1,1,1,1,6},
            {1,1,1,4,1,1,1,1,1,7,3,3,3,8,8,4,4,6,6,6},
            {9,9,8,8,9,9,1,1,1,1,1,1,1,1,1,2,4,6,6,6},
            {1,1,1,9,9,7,1,1,1,2,0,2,2,5,1,1,6,6,1,1},
            {1,1,1,6,6,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1},
            {6,1,1,6,1,1,2,2,1,1,1,1,1,1,1,1,1,5,6,6},
            {6,6,6,1,1,2,1,1,1,1,4,4,1,1,1,6,6,6,6,6},
            {6,6,1,1,1,2,2,1,1,2,4,4,4,1,6,6,6,6,6,6},
            {6,1,1,1,1,1,1,2,2,1,1,4,1,1,1,1,6,6,6,6},
            {1,1,1,1,1,5,2,2,1,1,1,1,1,8,8,8,8,8,6,6},
            {1,1,1,1,1,1,0,1,1,1,8,8,8,8,8,8,8,8,8,8},
            {1,1,4,10,4,8,8,2,2,1,8,8,8,8,8,8,8,8,8,8},
            {4,4,4,4,4,8,8,2,2,2,1,8,8,8,8,8,8,8,8,8}};

    private static String[] areaNames={
                    "Rugged Crossing",
                    "The Wide Open Spaces",
                    "Calm River",
                    "Steep Slopes",
                    "Crumbling Cliffs",
                    "Abandoned Mystery",
                    "Dense Forest",
                    "Crystal Cavern",
                    "Endless Dunes",
                    "wall",
                    "mine"};
    public static String nothing = "nothing";


    private static String[] areaDescription = new String[11];


    private static ArrayList<ArrayList<ArrayList<String>>> visibleObjects = new ArrayList<ArrayList<ArrayList<String>>>();

    public static void populateItems(){
                for (int i = 0; i < row; i++) {
            visibleObjects.add(new ArrayList<ArrayList<String>>());
            for (int j = 0; j < col; j++) {
                visibleObjects.get(i).add(new ArrayList<String>());
                visibleObjects.get(i).get(j).addAll(Arrays.asList("nothing")); // Add an ArrayList of items to the ArrayList at position i, j
            }
        }
    visibleObjects.get(1).get(0).clear();
    visibleObjects.get(1).get(0).addAll(Arrays.asList("salt","rusty_key"));
    }
    // Initialize the array

    public static void printObjects(Player player){
        int pRow = player.getPlayerPosition()[0];
        int pCol = player.getPlayerPosition()[1];

        System.out.print("After a quick glance, you have found: ");
        for (String item : visibleObjects.get(pRow).get(pCol)) {
            if (item == nothing) {

                System.out.print(item+" of significance in this area  ");

            } else {
                if (item == "salt"){item +=" in a jar";}
                System.out.print(item+", ");
            }

        }
        System.out.println("\b\b.");

    }




    public static String[] getAreaDescription(){
        try {
        areaDescription[0] = loadAreaDescription("src/Rugged Crossing.txt");
        areaDescription[1] = loadAreaDescription("src/The Wide Open Spaces.txt");
        areaDescription[2] = loadAreaDescription("src/Calm River.txt");
        areaDescription[3] = loadAreaDescription("src/Steep Slopes.txt");
        areaDescription[4] = loadAreaDescription("src/Crumbling Cliffs.txt");
        areaDescription[5] = loadAreaDescription("src/Abandoned Mystery.txt");
        areaDescription[6] = loadAreaDescription("src/Dense Forest.txt");
        areaDescription[7] = loadAreaDescription("src/Crystal Cavern.txt");
        areaDescription[8] = loadAreaDescription("src/Endless Dunes.txt");
        areaDescription[9] = loadAreaDescription("src/Rugged Crossing.txt");
        areaDescription[10] = loadAreaDescription("src/Rugged Crossing.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while loading the area descriptions: " + e.getMessage());
        }
        return areaDescription;
    }



    public static String loadAreaDescription(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        byte[] fileBytes = Files.readAllBytes(filePath);
        return new String(fileBytes, StandardCharsets.UTF_8);
    }
    public static String[] getAreaNames() {
        return areaNames;
    }
   // public static char[][] getMapTiles(){
    //    return mapTiles;
    //}
    public static int[][] getParagraphPointer(){
        return paragraphPointer;
    }

    public static void printMap(Player player){
        int pRow = player.getPlayerPosition()[0];
        int pCol = player.getPlayerPosition()[1];
        int row = mapTiles.length; // get the number of rows
        int column = mapTiles[0].length; // get the number of columns in the first row
        if (pRow>0){disMap[pRow-1][pCol]=true;}
        if (pRow<14){disMap[pRow+1][pCol]=true;}
        if (pCol>0){disMap[pRow][pCol-1]=true;}
        if (pCol<19){disMap[pRow][pCol+1]=true;}
        String head = "\u001B[97m"+"╭"+"─".repeat(column*2+1)+"╮";
        String foot = "\u001B[97m"+"╰"+"─".repeat(column*2+1)+"╯";
        System.out.println(head);
        for (int i=0;i<row;i++){
            System.out.print("\u001B[97m"+"│ ");
            for (int j = 0;j<column;j++) {
                if (i != pRow || j != pCol) {
                    if (disMap[i][j]) {
                        System.out.print("\u001B[92m" + mapTiles[i][j] + "\u001B[40m" + " ");
                    } else {
                        System.out.print("\u001B[90m" + "█" + " ");

                    }
                } else {
                    System.out.print("\u001B[94m" + playerIcon + " ");
                }
            }
            System.out.println("\u001B[97m"+"│");
        }
        System.out.println(foot);
    }
    public static void whatYouSee(Player player) {
        int pRow = player.getPlayerPosition()[0];
        int pCol = player.getPlayerPosition()[1];
        //char pPos = player.getMapPos();
        System.out.println("Examining your surroundings, you'll find...");
        if(pRow-1>-1){
            System.out.print(areaNames[paragraphPointer[pRow-1][pCol]]);
        }
        else {
            System.out.print("The GREAT VOID");
        }
        System.out.print(" --> N, ");
        if(pRow+1<16){
            System.out.print(areaNames[paragraphPointer[pRow+1][pCol]]);
        }
        else {
            System.out.print("The GREAT VOID");
        }
        System.out.print(" --> S, ");
        if(pCol-1>-1){
            System.out.print(areaNames[paragraphPointer[pRow][pCol-1]]);
        }
        else {
            System.out.print("The GREAT VOID");
        }
        System.out.print(" --> W, ");
        if(pCol+1<21){
            System.out.print(areaNames[paragraphPointer[pRow][pCol+1]]);
        }
        else {
            System.out.print("The GREAT VOID");
        }
        System.out.println(" --> E.");
    }
}


