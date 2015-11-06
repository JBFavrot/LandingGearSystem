package controler;


import java.io.IOException;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 * Main controller for the system
 * Is a singleton
 */
public class SystemController {

    private int state=1;

    private static SystemController ourInstance = new SystemController();

    public static SystemController getInstance() {
        return ourInstance;
    }

    private SystemController() {
    }


    private Thread gearThread = null;
    private Thread doorThread = null;
    private Thread mainThread = null;


    /**
     * This function is the main program thread, it deals with the opening and closing of the doors and gears.
     * To keep track of the current state in case of new command during another command, it uses states.
     */
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
                        doorThread = new Thread(new RunnableDoor());
                        doorThread.start();
                        synchronized (doorThread)
                        {
                            try {
                                doorThread.wait();
                                gearThread = new Thread(new RunnableGear());
                                gearThread.start();
                                synchronized (gearThread)
                                {
                                    try {
                                        gearThread.wait();
                                        doorThread = new Thread(new RunnableDoor());
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
                        gearThread = new Thread(new RunnableGear());
                        gearThread.start();
                        synchronized (gearThread)
                        {
                            try {
                                gearThread.wait();
                                doorThread = new Thread(new RunnableDoor());
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
                        doorThread = new Thread(new RunnableDoor());
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

/**
 * Runnable class to change gears state
 */
class RunnableGear implements Runnable
{
    private GearController gearController = new GearController();
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

/**
 * Runnable class to change Door states
 */
class RunnableDoor implements Runnable
{
    private DoorController doorController = new DoorController();
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
