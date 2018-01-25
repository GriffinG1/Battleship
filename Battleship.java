public class Battleship extends ConsoleProgram
{
    public static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    public int rowVal = -1;
    public int col = -1;
    public int direction = -1;
    public void run()
    {
        System.out.println("WELCOME TO BATTLESHIP!");
        System.out.println("======================");
        System.out.println("======================");
        Player player = new Player(readLine("Enter your name: "));
        Player computer = new Player("Computer");
        System.out.println("======================");
        player.printMyShips();
        for(int i = 0; i <= SHIP_LENGTHS.length - 1; i++)
        {
            getRow(player, i);
            getCol(player, i);
            getDirection(player, i);
            setupGrid(player, i);
        }
    }
    public void printUserTurn(Player player)
    {
        println(player.getPlayerName() + "'s Ships: ");
        player.printMyShips();
    }
    public void getRow(Player name, int shipLength)
    {
        println("Current ship length: " + SHIP_LENGTHS[shipLength]);
        String row = readLine("Input a row (A - J): ").toLowerCase();
        rowVal = ((int) row.charAt(0)) - 97;
        for(int i = rowVal; i < (rowVal + SHIP_LENGTHS[shipLength]); i++)
        {
            if(name.playerHasShip(rowVal, i))
            {
                println("Invalid row, please try again. You cannot stack ships on top of each other!");
                row = readLine("Input a row (A - J): ").toLowerCase();
            }
        }
        while(!row.matches("a|b|c|d|e|f|g|h|i|j"))
        {
            println("Invalid row, please try again.");
            row = readLine("Input a row (A - J): ").toLowerCase();
        }
        rowVal = ((int) row.charAt(0)) - 97;
    }
    public void getCol(Player name, int shipLength)
    {
        col = readInt("Input a column (1 - 10): ");
        for(int i = col; i < (col + SHIP_LENGTHS[shipLength]); i++)
        {
            if(name.playerHasShip(i, col))
            {
                println("Invalid column, please try again. You cannot stack ships on top of each other!");
                col = readInt("Input a row (1 - 10): ");
            }
        }
        while(col < 1 || col > 10 || name.playerHasShip(rowVal, col))
        {
            println("Invalid column, please try again.");
            col = readInt("Input a column (1 - 10): ");
        }
    }
    public void getDirection(Player name, int shipLength)
    {
        String dir = readLine("Input a direction (vertical or horizontal): ");
        while(!(dir.equals("vertical") || dir.equals("v")) && !(dir.equals("horizontal") || dir.equals("h")))
        {
            println("Invalid direction, please try again.");
            dir = readLine("Input a direction (vertical or horizontal): ");
        }
        if(dir.toLowerCase().equals("vertical") || dir.toLowerCase().equals("v"))
        {
            direction = 1;
        }
        else
        {
            direction = 0;
        }
    }
    public void setupGrid(Player player, int shipLength)
    {
        Ship ship = new Ship(player.SHIP_LENGTHS[shipLength]);
        println("Row: " + rowVal);
        println("ColumnVal: " + col);
        println("Direction: " + direction);
        player.chooseShipLocation(ship, rowVal, col - 1, direction);
        println(ship);
        player.printMyShips();
        println();
    }
}
