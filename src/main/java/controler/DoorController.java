package controler;

import model.DataModel;
import model.DoorModel;
import model.GearModel;
import view.SystemView;

import java.io.IOException;
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


    public int changeDoorState() throws InterruptedException, IOException {
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
                    openDoor();
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
        for(DoorModel door:doors)
        {
            door.setState(DoorModel.states.problem);
        }
    }

    private void openDoor() throws IOException, InterruptedException {
        for(DoorModel door:doors)
        {
            door.setState(DoorModel.states.movingDown);
            System.out.println(door.toString() + " " + door.getState());
            //SystemView.refreshDoor();
        }
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            throw new InterruptedException(e.getMessage());
        }
        for(DoorModel door:doors)
        {
            door.setState(DoorModel.states.opened);
            System.out.println(door.toString() + " " + door.getState());
            //SystemView.refreshDoor();
        }
    }

    private void closeDoor() throws IOException,InterruptedException {
        for(DoorModel door:doors)
        {
            door.setState(DoorModel.states.movingUp);
            System.out.println(door.toString() + " " + door.getState());
            //SystemView.refreshDoor();
        }
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            throw new InterruptedException(e.getMessage());
        }
        for(DoorModel door:doors)
        {
            door.setState(DoorModel.states.closed);
            System.out.println(door.toString() + " " + door.getState());
            //SystemView.refreshDoor();
        }
    }
}
