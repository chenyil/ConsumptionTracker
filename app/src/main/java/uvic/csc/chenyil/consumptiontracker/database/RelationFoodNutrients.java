package uvic.csc.chenyil.consumptiontracker.database;

/**
 * Created by imacadmin on 2014-10-15.
 */
public class RelationFoodNutrients {
    private long id;
    private long nid;
    private long fid;
    private float amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPId() {  return nid; }

    public void setPId(long id) {
        this.nid = id;
    }

    public long getFId() {
        return fid;
    }

    public void setFId(long id) {
        this.fid = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount=amount;
    }
}
