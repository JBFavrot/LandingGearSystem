package model;


public class DoorModel {

    private DataModel.states doorState = DataModel.states.closed;

    /**
     *Accessor for doorState
     * @return Door state
     */
    public DataModel.states getDoorState()
    {
        return doorState;
    }

    /**
     * Setter for doorState
     * @param state new state of the door
     * @return updated state of the door
     */
    public DataModel.states setDoorState(DataModel.states state)
    {
        doorState=state;
        return state;
    }
}
