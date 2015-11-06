package controler;

import model.DataModel;
import model.DoorModel;
import model.GearModel;

import java.util.List;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 * Controller for doors
 */
public class DoorController {
    private List<DoorModel> doors=null;

    public DoorController() {
        doors = DataModel.getInstance().getDoors();
    }


    public int changeDoorState() throws InterruptedException {
        Boolean testDoorsState=true;

        //Check all gears have the same state
        DoorModel.states firstDoorState = null;
        if(doors.size()!=0)
        {
            firstDoorState = doors.get(0).getState();
        }

        for(DoorModel door:doors)
        {
            DoorModel.states doorState = door.getState();
            if(doorState != firstDoorState)
            {
                testDoorsState=false;
            }
        }
        if(testDoorsState)
        {
            switch (firstDoorState) {
                case opened:
                    closeDoor();
                    break;
                case closed:
                    openDoor();
                    break;
                case movingUp:
                    openDoor();
                    break;
                case movingDown:
                    closeDoor();
                    break;
                case problem:
                    break;
                default:
                    riseProblem();
                    break;

            }
        }
        return 0;
    }

    private void riseProblem() {

    }

    private void openDoor() {

    }

    private void closeDoor() {

    }
}
