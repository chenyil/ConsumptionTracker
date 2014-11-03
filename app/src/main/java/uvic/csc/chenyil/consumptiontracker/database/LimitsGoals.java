package uvic.csc.chenyil.consumptiontracker.database;

/**
 * Created by imacadmin on 2014-10-15.
 */
public class LimitsGoals {
    private long id;
    private long pid;
    private long nid;
    private float amount;
    private String type;

    LimitsGoals(long id,long pid, long nid, float amount, String type){
        setId(id);
        setPId(pid);
        setNId(nid);
        setAmount(amount);
        setType(type);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPId() {  return pid; }

    public void setPId(long id) {
        this.pid = id;
    }

    public long getNId() {
        return nid;
    }

    public void setNId(long id) {
        this.nid = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount=amount;
    }

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}
}
