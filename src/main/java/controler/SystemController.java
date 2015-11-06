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


    Thread gearThread = null;
    Thread doorThread = null;

    public void changeSystemState() {
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
                doorThread = new Thread(new RunnDoor());
                doorThread.start();
                synchronized (doorThread)
                {
                    try {
                        doorThread.wait();
                    } catch (InterruptedException e) {
                        state=3;
                    }
                    gearThread = new Thread(new RunnGear());
                    gearThread.start();
                    synchronized (gearThread)
                    {
                        try {
                            gearThread.wait();
                        } catch (InterruptedException e) {
                            state=2;
                        }
                        doorThread = new Thread(new RunnDoor());
                        doorThread.start();
                    }
                }

                break;
            case 2:
                gearThread = new Thread(new RunnGear());
                gearThread.start();
                synchronized (gearThread)
                {
                    try {
                        gearThread.wait();
                    } catch (InterruptedException e) {
                        state=2;
                    }
                    doorThread = new Thread(new RunnDoor());
                    doorThread.start();
                    synchronized (doorThread) {
                        try {
                            doorThread.wait();
                        } catch (InterruptedException e) {
                            state=3;
                        }
                        state = 1;
                    }
                }
                break;
            case 3:
                doorThread = new Thread(new RunnDoor());
                doorThread.start();
                synchronized (doorThread) {
                    try {
                        doorThread.wait();
                    } catch (InterruptedException e) {
                        state=3;
                    }
                    state = 1;
                }
                break;
            default:
                break;
        }



    }

}

class RunnGear implements Runnable
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

class RunnDoor implements Runnable
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
