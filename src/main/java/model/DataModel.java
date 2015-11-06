package model;

/**
 * Created by Jean-Baptiste on 06/11/2015.
 */
public class DataModel {
    private static DataModel ourInstance = new DataModel();

    public static DataModel getInstance() {
        return ourInstance;
    }

    private DataModel() {
    }


}
