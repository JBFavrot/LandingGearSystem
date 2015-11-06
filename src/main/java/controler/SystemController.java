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
    Thread mainThread = null;

    public void changeSystemState() {
        if(mainThread!=null)
        {
            if(doorThread!=null)
            {
                doorThread.interrupt();
            }
            if(gearThread!=null)
            {
                gearThread.interrupt();
            }
            mainThread.interrupt();
        }
        mainThread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Case: " + state);
                switch(state)
                {
                    case 1:
                        doorThread = new Thread(new RunnDoor());
                        doorThread.start();
                        synchronized (doorThread)
                        {
                            try {
                                doorThread.wait();
                                gearThread = new Thread(new RunnGear());
                                gearThread.start();
                                synchronized (gearThread)
                                {
                                    try {
                                        gearThread.wait();
                                        doorThread = new Thread(new RunnDoor());
                                        doorThread.start();
                                    } catch (InterruptedException e) {
                                        state=2;
                                        System.out.println(state);
                                    }

                                }
                            } catch (InterruptedException e) {
                                state=3;
                                System.out.println(state);
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
                                doorThread = new Thread(new RunnDoor());
                                doorThread.start();
                                synchronized (doorThread) {
                                    try {
                                        doorThread.wait();
                                    } catch (InterruptedException e) {
                                        state=3;
                                        System.out.println(state);
                                    }
                                    state = 1;
                                    System.out.println(state);
                                }
                            } catch (InterruptedException e) {
                                state=2;
                                System.out.println(state);
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
                                System.out.println(state);
                            }
                            state = 1;
                            System.out.println(state);
                        }
                        break;
                    default:
                        break;
                }

            }
        });
        mainThread.start();


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
            System.out.println("Doors stopped while moving");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
