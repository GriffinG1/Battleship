public class Player
{
    public static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    private Ship[] ships;
    private Grid player = new Grid();
    private Grid opponent = new Grid();
    private String playerName;
    public Player(String name)
    {
        ships = new Ship[SHIP_LENGTHS.length];
        for(int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            ships[i] = new Ship(SHIP_LENGTHS[i]);
        }
        name = playerName;
    }
    public String getPlayerName()
    {
        return playerName;
    }
    public void chooseShipLocation(Ship s, int row, int col, int direction)
    {
        s.setLocation(row, col);
        s.setDirection(direction);
        player.addShip(s);
    }
    public void printMyShips()
    {
        player.printShips();
    }
    public void printOpponentGuesses()
    {
        opponent.printStatus();
    }
    public void printMyGuesses()
    {
        player.printStatus();
    }
    public void recordOpponentGuess(int row, int col)
    {
        if(player.hasShip(row, col))
        {
            opponent.markHit(row, col);
        }
        else{
            opponent.markMiss(row, col);
        }
    }
    
}
