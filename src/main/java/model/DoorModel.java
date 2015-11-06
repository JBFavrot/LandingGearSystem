package model;

public class DoorModel {
    private static final int OPEN = 1;
    private static final int CLOSED = 0;

    int doorState = CLOSED;

    /**
     * Changes the door state
     * @return new door state
     */
    public int changeDoorState()
    {
        if (doorState==CLOSED) doorState=OPEN;
        else doorState=CLOSED;
        return doorState;

    }

    /**
     *
     * @return Door state
     */
    public int getDoorState()
    {
        return doorState;
    }
}
