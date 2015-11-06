package controler;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 */
public class SystemController {



    private static SystemController ourInstance = new SystemController();

    public static SystemController getInstance() {
        return ourInstance;
    }

    private SystemController() {
    }

}

class ThreadDoors extends Thread
{

}