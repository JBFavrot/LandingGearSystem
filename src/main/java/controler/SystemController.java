package controler;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 */
public class SystemController {

    GearController gearController = GearController.getInstance();

    private static SystemController ourInstance = new SystemController();

    public static SystemController getInstance() {
        return ourInstance;
    }

    private SystemController() {
    }


    public void changeSystemState()
    {
        gearController.changeGearState();
    }

}
