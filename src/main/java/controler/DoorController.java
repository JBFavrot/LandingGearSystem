package controler;

import model.DoorModel;

import java.util.List;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 * Controller for doors
 */
public class DoorController {
    List<DoorModel> doors;

    int changeGearState()
    {
        Boolean testGearsState;

        //Check all doors have the same state
        for(DoorModel door:doors)
        {
            DoorModel.states gearState = door.getState();
        }
        return 0;
    }
}
