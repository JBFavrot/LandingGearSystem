package controler;

import model.DataModel;
import model.GearModel;
import view.SystemView;

import java.io.IOException;
import java.util.*;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 * Controller for gears
 */

class GearController {

    private List<GearModel> gears = null;


    public GearController() {
        gears = DataModel.getInstance().getGears();
    }


    /**
     * Switches between states of the gear system.
     * @throws IOException
     * @throws InterruptedException
     */
    public void changeGearState() throws IOException,InterruptedException {
        Boolean testGearsState=true;

        //Check all gears have the same state
        GearModel.states firstGearState;
        if(gears.size()!=0)
        {
            firstGearState = gears.get(0).getState();
        }
        else
        {
            firstGearState = GearModel.states.problem;
        }

        for(GearModel gear:gears)
        {
            GearModel.states gearState = gear.getState();
            if(gearState != firstGearState)
            {
                testGearsState=false;
            }

        }
        if(testGearsState)
        {
            //Determines which action to do in each case
            switch (firstGearState) {
                case down:
                    closeGears();
                    break;
                case up:
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
    }

    /**
     * Closes the gears, the operation takes 5s
     * @throws IOException
     * @throws InterruptedException
     */
    private void closeGears() throws IOException, InterruptedException {
        for(GearModel gear:gears)
        {
            gear.setState(GearModel.states.movingUp);
        }
        SystemView.refreshGear(gears.get(1).getState());
        System.out.println("Gears " + gears.get(1).getState());
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            throw new InterruptedException(e.getMessage());
        }
        for(GearModel gear:gears)
        {
            gear.setState(GearModel.states.up);
        }
        SystemView.refreshGear(gears.get(1).getState());
        System.out.println("Gears " + gears.get(1).getState());
    }

    /**
     * Opens the gears, the operation takes 5s
     * @throws IOException
     * @throws InterruptedException
     */
    private void openGears() throws IOException, InterruptedException {
        for(GearModel gear:gears)
        {
            gear.setState(GearModel.states.movingDown);
        }
        SystemView.refreshGear(gears.get(1).getState());
        System.out.println("Gears " + gears.get(1).getState());
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            throw new InterruptedException(e.getMessage());
        }
        for(GearModel gear:gears)
        {
            gear.setState(GearModel.states.down);
        }
        SystemView.refreshGear(gears.get(1).getState());
        System.out.println("Gears " + gears.get(1).getState());
    }

    /**
     * In case of problem, it will set the data to "problem"
     */
    private void riseProblem()
    {
        for(GearModel gear:gears)
        {
            gear.setState(GearModel.states.problem);
        }
    }
}
