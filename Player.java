public class Player
{
    private Ship[] ships;
    private Grid player = new Grid();
    private Grid computer = new Grid();
    private String name;
    public static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    
    public Player(String name)
    {
        ships = new Ship[SHIP_LENGTHS.length];
        for(int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            ships[i] = new Ship(SHIP_LENGTHS[i]);
        }
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void printMyShips()
    {
        player.printShips();
    }
    public void printOpponentGuesses()
    {
        computer.printStatus();
    }
    public void printMyGuesses()
    {
        player.printStatus();
    }
    public void chooseShipLocation(Ship s, int row, int col, int direction)
    {
        s.setLocation(row, col);
        s.setDirection(direction);
        player.addShip(s);
    }
    public void recordOpponentGuess(int row, int col) {
        if (player.hasShip(row, col))
        {
            computer.markHit(row, col);
        }
        else
        {
            computer.markMiss(row, col);
        }
    }
}
