package uvic.csc.chenyil.consumptiontracker.database;

/**
 * Created by imacadmin on 2014-10-15.
 */
public class Food {
    private long id;
    private String name;
    private String unit;
    private int servingSize;
    private int packageSize;

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

    public int getServingSize() {return servingSize;}

    public void setServingSize(int size){this.servingSize=size;}

    public int getPackageSize() {return packageSize;}

    public void setPackageSize(int size){this.packageSize=size;}

}
