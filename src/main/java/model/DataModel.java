package model;

import java.util.List;
import java.util.Vector;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 */
public class DataModel {
    private static DataModel ourInstance = new DataModel();
    private List<GearModel> gears = null;
    private List<DoorModel> doors = null;

    public static DataModel getInstance() {
        return ourInstance;
    }

    private GearModel gearFront = new GearModel();
    private GearModel gearLeft = new GearModel();
    private GearModel gearRight = new GearModel();

    private DoorModel doorFront = new DoorModel();
    private DoorModel doorLeft = new DoorModel();
    private DoorModel doorRight = new DoorModel();



    private DataModel() {
        gears = new Vector<GearModel>();
        doors = new Vector<DoorModel>();

        gears.add(gearFront);
        gears.add(gearLeft);
        gears.add(gearRight);

        doors.add(doorFront);
        doors.add(doorLeft);
        doors.add(doorRight);
    }


    public List<GearModel> getGears() {
        return gears;
    }
    public List<DoorModel> getDoors() {
        return doors;
    }
}
