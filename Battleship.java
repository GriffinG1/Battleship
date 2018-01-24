public class Battleship extends ConsoleProgram
{
    public void run()
    {
        Player player = new Player(readLine("Enter your name: "));
        Player computer = new Player("Computer");
        newGame(player);
        printUserTurn(player);
    }
    public void printUserTurn(Player player)
    {
        println(player.getName() + "'s Ships: ");
        player.printMyShips();
    }
    public void newGame(Player player)
    {
        int direction = -1;
        int rowVal;
        player.printMyShips();
        for(int i = 0; i <= player.SHIP_LENGTHS.length - 1; i++)
        {
            Ship ship = new Ship(player.SHIP_LENGTHS[i]); // MUST CHANGE VARIABLE EVERY LOOP
            println("Current ship length: " + player.SHIP_LENGTHS[i]);
            String row = readLine("Input a row (A - J): ").toLowerCase();
            while(!row.matches("a|b|c|d|e|f|g|h|i|j"))
            {
                println("Invalid row, please try again.");
                row = readLine("Input a row (A - J): ").toLowerCase();
            }
            rowVal = ((int) row.charAt(0)) - 97;
            int column = readInt("Input a column (1 - 10): ");
            while(column < 1 || column > 10)
            {
                println("Invalid column, please try again.");
                column = readInt("Input a column (1 - 10): ");
            }
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
            
            println("Row: " + row + " rowVal: " + rowVal);
            
            player.chooseShipLocation(ship, rowVal, column - 1, direction);
            println(ship);
            player.printMyShips();
            println();
        }
    }
}
