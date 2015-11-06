package model;

import java.util.List;
import java.util.Vector;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 * Model for all data
 * Is a singleton
 */
public class DataModel {
    private static DataModel ourInstance = new DataModel();
    private List<GearModel> gears = null;
    private List<DoorModel> doors = null;

    public static DataModel getInstance() {
        return ourInstance;
    }



    private DataModel() {
        gears = new Vector<GearModel>();
        doors = new Vector<DoorModel>();

        GearModel gearFront = new GearModel();
        gears.add(gearFront);
        GearModel gearLeft = new GearModel();
        gears.add(gearLeft);
        GearModel gearRight = new GearModel();
        gears.add(gearRight);

        DoorModel doorFront = new DoorModel();
        doors.add(doorFront);
        DoorModel doorLeft = new DoorModel();
        doors.add(doorLeft);
        DoorModel doorRight = new DoorModel();
        doors.add(doorRight);
    }


    public List<GearModel> getGears() {
        return gears;
    }
    public List<DoorModel> getDoors() {
        return doors;
    }
}
