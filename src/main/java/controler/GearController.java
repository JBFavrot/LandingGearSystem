package controler;

import model.DataModel;
import model.DoorModel;
import model.GearModel;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 * Controller for gears
 */

public class GearController {

    private List<GearModel> gears;

    private static GearController ourInstance = new GearController();

    public static GearController getInstance() {
        return ourInstance;
    }

    private GearController() {
    }


    public int changeGearState()
    {
        Boolean testGearsState=true;

        //Check all gears have the same state
        DataModel.states firstGearState = null;
        if(gears.size()!=0)
        {
            firstGearState = gears.get(0).getState();
        }

        for(GearModel gear:gears)
        {
            DataModel.states gearState = gear.getState();
            if(gearState != firstGearState)
            {
                testGearsState=false;
            }
        }
        if(testGearsState)
        {
            switch (firstGearState) {
                case opened:
                    closeGears();
                    break;
                case closed:
                    openGears();
                    break;
                case movingUp:
                    openGears();
                    break;
                case movingDown:
                    closeGears();
                    break;
                case problem:
                    break;
                default:
                    riseProblem();

            }
        }
        return 0;
    }


    private void closeGears()
    {
        for(GearModel gear:gears)
        {
            gear.setState(DataModel.states.movingUp);
        }
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        for(GearModel gear:gears)
        {
            gear.setState(DataModel.states.closed);
        }
    }

    private void openGears()
    {
        for(GearModel gear:gears)
        {
            gear.setState(DataModel.states.movingDown);
        }
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        for(GearModel gear:gears)
        {
            gear.setState(DataModel.states.opened);
        }
    }

    private void riseProblem()
    {
        for(GearModel gear:gears)
        {
            gear.setState(DataModel.states.problem);
        }
    }

    public void add(GearModel gearModel) {
        gears.add(gearModel);
    }

}
