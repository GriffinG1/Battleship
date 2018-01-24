public class Ship
{
    private int direction;
    private int length;
    private int col;
    private int row;
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    public Ship(int length)
    {
        this.length = length;
        direction = UNSET;
        row = UNSET;
        col = UNSET;
    }
    public boolean isLocationSet()
    {
        if(row > UNSET && col > UNSET)
        {
            return true;
        }
        return false;
    }
    public boolean isDirectionSet()
    {
        if(direction > UNSET)
        {
            return true;
        }
        return false;
    }
    public void setLocation(int row, int col)
    {
        this.row = row - 97;
        this.col = col;
    }
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    public int getRow()
    {
        return Character.forDigit(row + 97, 10);
    }
    public int getCol()
    {
        return col;
    }
    public int getLength()
    {
        return length;
    }
    public int getDirection()
    {
        return direction;
    }
    private String directionToString()
    {
        if(direction == HORIZONTAL)
        {
            return "horizontal";
        }
        else if(direction == VERTICAL)
        {
            return "vertical";
        }
        else
        {
            return "unset direction";
        }
    }
    private String locationToString()
    {
        if(isLocationSet())
        {
            char tempRow = (char) (row + 97);
            return "(" + Character.toUpperCase(tempRow) + ", " + col + ")";
        }
        else
        {
            return "(unset location)";
        }
    }
    public String toString()
    {
        return directionToString() + " ship of length " + length + " at " + locationToString();
    }
}
