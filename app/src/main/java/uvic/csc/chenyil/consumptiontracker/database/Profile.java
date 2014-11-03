package uvic.csc.chenyil.consumptiontracker.database;

/**
 * Created by imacadmin on 2014-09-22.
 */
public class Profile {
    private long id;
    private String name;
    private String gender;
    private int height;
    private int weight;
    private String photo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getGender(){return gender;}

    public int getHeight() {return height;}

    public int getWeight() {return weight;}

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender){ this.gender = gender;}

    public void setHeight(int height){this.height = height;}

    public void setWeight(int weight){this.weight = weight;}

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return (name);
    }
}