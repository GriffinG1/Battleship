public class Location
{
    private boolean hasShip;
    private int status;
    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;

    public Location()
    {
        status = 0;
    }
    public boolean checkHit()
    {
        if(status == HIT)
        {
            return true;
        }
        return false;
    }
    public boolean checkMiss()
    {
        if(status == MISSED)
        {
            return true;
        }
        return false;
    }
    public boolean isUnguessed()
    {
        if(status == UNGUESSED)
        {
            return true;
        }
        return false;
    }
    public void markHit()
    {
        status = 1;
    }
    public void markMiss()
    {
        status = 2;
    }
    public boolean hasShip()
    {
        return hasShip;
    }
    public void setShip(boolean val)
    {
        hasShip = true;
    }
    public void setStatus(int status)
    {
        this.status = status;
    }
    public int getStatus()
    {
        return status;
    }
}
