package controler;

import java.io.IOException;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 */
public class SystemController {

    GearController gearController = new GearController();

    private static SystemController ourInstance = new SystemController();

    public static SystemController getInstance() {
        return ourInstance;
    }

    private SystemController() {
    }


    Thread gearThread = null;

    public void changeSystemState()
    {
        if(gearThread!=null)
        {
            gearThread.interrupt();
        }
        gearThread = new Thread( new Runnable() {
            public void run() {
                try {
                    gearController.changeGearState();
                } catch (InterruptedException e) {
                    System.out.println("Gears stopped while moving");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        gearThread.start();

    }

}
