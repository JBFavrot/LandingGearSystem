/**
 * Created by Emma-Louise
 * Model for Door
 * Is the same as gear, we keep different classes as other attributes may come later in the project.
 */

package model;


public class DoorModel {

    private DataModel.states doorState = DataModel.states.closed;

    /**
     *Accessor for doorState
     * @return Door state
     */
    public DataModel.states getState()
    {
        return doorState;
    }

    /**
     * Setter for doorState
     * @param state new state of the door
     * @return updated state of the door
     */
    public DataModel.states setState(DataModel.states state)
    {
        doorState=state;
        return state;
    }
}
