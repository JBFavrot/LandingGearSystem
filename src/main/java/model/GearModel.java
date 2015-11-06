package model;

import model.DataModel;

public class GearModel {
    private DataModel.states gearState = DataModel.states.closed;


    /**
     *Accessor for gearState
     * @return Gear state
     */
    public DataModel.states getDoorState()
    {
        return gearState;
    }

    /**
     * Setter for gearState
     * @param state new state of the gear
     * @return updated state of the gear
     */
    public DataModel.states setDoorState(DataModel.states state)
    {
        gearState=state;
        return state;
    }
}
