/**
 * Created by Emma-Louise
 * Model for Gear
 * Is the same as door, we keep different classes as other attributes may come later in the project.
 */
package model;

public class GearModel {
    public enum states{
        movingUp,
        movingDown,
        down,
        up,
        problem
    }

    private states gearState = states.up;


    /**
     *Accessor for gearState
     * @return Gear state
     */
    public states getState()
    {
        return gearState;
    }

    /**
     * Setter for gearState
     * @param state new state of the gear
     * @return updated state of the gear
     */
    public states setState(states state)
    {
        gearState=state;
        return state;
    }
}
