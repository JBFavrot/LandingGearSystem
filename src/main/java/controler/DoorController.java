package controler;

import model.DataModel;
import model.DoorModel;
import view.SystemView;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 * Controller for doors
 */
class DoorController {
    private List<DoorModel> doors=null;

    public DoorController() {
        doors = DataModel.getInstance().getDoors();
    }

    /**
     * Switches between states of the doors.
     * @throws IOException
     * @throws InterruptedException
     */
    public synchronized void changeDoorState() throws InterruptedException, IOException {
        Boolean testDoorsState=true;

        //Check all gears have the same state
        DoorModel.states firstDoorState;
        if(doors.size()!=0)
        {
            firstDoorState = doors.get(0).getState();
        }
        else
        {
            firstDoorState = DoorModel.states.problem;
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
    }

    /**
     * In case of problem, it will set the data to "problem"
     */
    private void riseProblem() {
        for(DoorModel door:doors)
        {
            door.setState(DoorModel.states.problem);
        }
    }

    /**
     * Opens the doors, the operation takes 5s
     * @throws IOException
     * @throws InterruptedException
     */
    private synchronized void openDoor() throws IOException, InterruptedException {
        for(DoorModel door:doors)
        {
            door.setState(DoorModel.states.movingDown);
        }
        SystemView.refreshDoor(doors.get(0).getState());
        System.out.println("Doors " + doors.get(0).getState());
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
        }
        SystemView.refreshDoor(doors.get(0).getState());
        System.out.println("Doors " + doors.get(0).getState());
    }

    /**
     * Closes the doors, the operation takes 5s
     * @throws IOException
     * @throws InterruptedException
     */
    private synchronized void closeDoor() throws IOException,InterruptedException {
        for(DoorModel door:doors)
        {
            door.setState(DoorModel.states.movingUp);
        }
        SystemView.refreshDoor(doors.get(0).getState());
        System.out.println("Doors " + doors.get(0).getState());
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
        }
        SystemView.refreshDoor(doors.get(0).getState());
        System.out.println("Doors " + doors.get(0).getState());
    }
}
