package controler;


import java.io.IOException;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 */
public class SystemController {

    GearController gearController = new GearController();
    DoorController doorController = new DoorController();

    int state=1;

    private static SystemController ourInstance = new SystemController();

    public static SystemController getInstance() {
        return ourInstance;
    }

    private SystemController() {
    }


    GearThread gearThread = null;
    DoorThread doorThread = null;

    public void changeSystemState()
    {
        if(doorThread!=null)
        {
            doorThread.interrupt();
        }
        if(gearThread!=null)
        {
            gearThread.interrupt();
        }
        switch(state)
        {
            case 1:
                doorThread = new DoorThread();
                doorThread.run();

                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }


    }

}

class GearThread extends Thread
{
    GearController gearController = new GearController();
    public void run() {
        try {
            gearController.changeGearState();
        } catch (InterruptedException e) {
            System.out.println("Gears stopped while moving");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DoorThread extends Thread
{
    DoorController doorController = new DoorController();
    public void run() {
        try {
            doorController.changeDoorState();
        } catch (InterruptedException e) {
            System.out.println("Gears stopped while moving");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
