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

public class GearController {

    private List<GearModel> gears = null;


    public GearController() {
        gears = DataModel.getInstance().getGears();
    }

   


    public int changeGearState() throws IOException,InterruptedException {
        Boolean testGearsState=true;

        //Check all gears have the same state
        GearModel.states firstGearState = null;
        if(gears.size()!=0)
        {
            firstGearState = gears.get(0).getState();
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
        return 0;
    }


    private void closeGears() throws IOException, InterruptedException {
        for(GearModel gear:gears)
        {
            gear.setState(GearModel.states.movingUp);
            System.out.println(gear.toString() + " " + gear.getState());
            SystemView.refreshGear("moving");
        }
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
            System.out.println(gear.toString() + " " + gear.getState());
            SystemView.refreshGear("up");
        }
    }

    private void openGears() throws IOException, InterruptedException {
        for(GearModel gear:gears)
        {
            gear.setState(GearModel.states.movingDown);
            System.out.println(gear.toString() + " " + gear.getState());
            SystemView.refreshGear("moving");
        }
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
            System.out.println(gear.toString() + " " + gear.getState());
            SystemView.refreshGear("down");
        }
    }

    private void riseProblem()
    {
        for(GearModel gear:gears)
        {
            gear.setState(GearModel.states.problem);
        }
    }

    public void add(GearModel gearModel) {
        gears.add(gearModel);
    }

}
