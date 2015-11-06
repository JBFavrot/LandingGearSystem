/**
 * Created by Emma-Louise
 * Model for Gear
 * Is the same as door, we keep different classes as other attributes may come later in the project.
 */
package model;

import model.DataModel;

public class GearModel {
    private DataModel.states gearState = DataModel.states.closed;


    /**
     *Accessor for gearState
     * @return Gear state
     */
    public DataModel.states getState()
    {
        return gearState;
    }

    /**
     * Setter for gearState
     * @param state new state of the gear
     * @return updated state of the gear
     */
    public DataModel.states setState(DataModel.states state)
    {
        gearState=state;
        return state;
    }
}
