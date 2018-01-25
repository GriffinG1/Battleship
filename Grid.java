public class Grid
{
    private static final String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};
    private Location[][] grid;
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    public Grid()
    {
        grid = new Location[NUM_COLS][NUM_ROWS];
        for (int y = 0; y < NUM_ROWS; y++) 
        {
            for (int x = 0; x < NUM_COLS; x++) 
            {
                grid[x][y] = new Location();
            }
        }
    }
    public void markHit(int row, int col)
    {
        grid[col][row].markHit(); 
    }
    public void markMiss(int row, int col)
    {
         grid[col][row].markMiss();
    }
    public void setStatus(int row, int col, int status)
    {
         grid[col][row].setStatus(status);
    }
    public int getStatus(int row, int col)
    {
        return grid[col][row].getStatus();
    }
    public boolean alreadyGuessed(int row, int col)    
    {
        return !grid[col][row].isUnguessed();
    }
    public void setShip(int row, int col, boolean val)
    {
        grid[col][row].setShip(val);
    }
    public boolean hasShip(int row, int col)
    {
        return grid[col][row].hasShip();
    }
    public Location get(int row, int col)
    {
        return grid[col][row];
    }
    public int numRows()
    {
        return NUM_ROWS;
    }
    public int numCols()
    {
        return NUM_COLS;
    }
    public void printStatus()
    {
        for (int i = 0; i < NUM_COLS; i++) 
        {
            System.out.print(" ");
            if(i != 9) 
            {
                System.out.print((i + 1) + " ");
            }
            else 
            {
                System.out.print(i+1);
            }
        }
        System.out.println("");
        for(int y = 0; y < NUM_ROWS; y++)
        {
            System.out.print(alpha[y] + " ");
            for(int x = 0; x < NUM_COLS; x++)
            {
                if(grid[x][y].checkHit())
                {
                    System.out.print("X ");
                }
                else if (grid[x][y].checkMiss())
                {
                    System.out.print("O ");
                }
                else
                {
                    System.out.print("- ");
                }
            }
            System.out.println("");
        }
    }
    public void printShips()
    {
        System.out.print("");
        for (int i = 0; i < NUM_COLS; i++) 
        {
            System.out.print(" ");
            if(i != 9) 
            {
                System.out.print((i + 1) + " ");
            }
            else 
            {
                System.out.print(i+1);
            }
        }
        System.out.println("");
        for(int y = 0; y < NUM_ROWS; y++)
        {
            System.out.print(alpha[y] + " ");
            for(int x = 0; x < NUM_COLS; x++)
            {
                if(grid[x][y].hasShip())
                {
                    System.out.print("X ");
                }
                else 
                {
                    System.out.print("- ");
                }
            }
            System.out.println("");
        }
    }
    public void addShip(Ship s)
    {
        int length = s.getLength();
        int direction = s.getDirection();
        int row = s.getRow();
        int col = s.getCol();
        if(direction == 1)
        {
            for(int i = row; i < (s.getLength() + row); i++)
            {
                setShip(i, col, true);
            }
        }
        else
        {
            for(int j = col; j < (s.getLength() + col); j++)
            {
                setShip(row, j, true);
            }
        }
    }
}
