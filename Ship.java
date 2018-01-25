public class Ship
{
    private int row;
    private int col;
    private int length;
    private int direction;
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public Ship(int length)
    {
        this.length = length;
        row = UNSET;
        col = UNSET;
        direction = UNSET;
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
        this.row = row;
        this.col = col;
    }
    public int getDirection()
    {
        return direction;
    }
    public int getRow()
    {
        return row;
    }
    public int getCol()
    {
        return col;
    }
    public int getLength()
    {
        return length;
    }
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    private String directionToString()
    {
        if(direction == HORIZONTAL)
        {
            return "horizontal";
        }
        if(direction == VERTICAL)
        {
            return "vertical";
        }
        return "unset direction";
    }
    private String locationToString()
    {
        if(this.isLocationSet() == false)
        {
            return "unset location";
        }
        return  row + ", " + col;
    }
    public String toString()
    {
        return directionToString() + " ship of length " + length + " at " + "(" + locationToString() + ")";
    }
}
