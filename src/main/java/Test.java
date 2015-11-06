import java.io.IOException;

import controler.*;
import view.*;
import model.*;

import javax.swing.JFrame;


public class Test {

	public static void main(String[] args) throws IOException {

        //Initialisation

        GearModel gearFront = new GearModel();
        GearModel gearLeft = new GearModel();
        GearModel gearRight = new GearModel();

        GearController gearController = GearController.getInstance();

        gearController.add(gearFront);
        gearController.add(gearLeft);
        gearController.add(gearRight);

        //Display

		SystemView pannel = new SystemView();

        //Tests

	}

}
