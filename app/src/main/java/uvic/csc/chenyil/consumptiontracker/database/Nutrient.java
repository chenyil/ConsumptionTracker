package uvic.csc.chenyil.consumptiontracker.database;

/**
 * Created by imacadmin on 2014-10-15.
 */
public class Nutrient {
    private long id;
    private String name;
    private String unit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {return unit;}

    public void setUnit(String unit){this.unit=unit;}
}
