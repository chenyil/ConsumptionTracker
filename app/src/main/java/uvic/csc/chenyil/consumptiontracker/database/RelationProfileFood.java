package uvic.csc.chenyil.consumptiontracker.database;

/**
 * Created by imacadmin on 2014-10-15.
 */
import java.util.Date;
public class RelationProfileFood {
    private long id;
    private long pid;
    private long fid;
    private float amount;
    private Date purchaseTime;
    private Date finishTime;

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

    public Date getPurchaseTime() {return purchaseTime;}

    public Date getFinishTime() {return finishTime;}

    public void setPurchaseTime(Date time) {this.purchaseTime=time;}

    public void setFinishTime(Date time) {this.finishTime=time;}
}
