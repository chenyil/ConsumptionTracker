package uvic.csc.chenyil.consumptiontracker.database;

/**
 * Created by imacadmin on 2014-09-21.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_PROFILES = "profiles";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER= "gender";
    public static final String COLUMN_HEIGHT= "height";
    public static final String COLUMN_WEIGHT= "weight";

    public static final String TABLE_NUTRIENTS = "nutrients";
    public static final String COLUMN_UNIT = "unit";

    public static final String TABLE_LIMITSGOALS = "limitsgoals";
    public static final String COLUMN_NID = "nid";
    public static final String COLUMN_NVALUE= "NutrientValue";
    public static final String COLUMN_TYPE="type";
    public static final String COLUMN_PID="pid";

//    private static final String DATABASE_NAME = "profiles.db";
//    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "appdata.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String PROFILES_CREATE = "create table "
            + TABLE_PROFILES + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null,"
            + COLUMN_GENDER + " text not null,"
            + COLUMN_HEIGHT + " integer not null,"
            + COLUMN_WEIGHT + " integer not null"
            + ");";

    private static final String NUTRIENTS_CREATE = "create table "
            + TABLE_NUTRIENTS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null,"
            + COLUMN_UNIT + "text not null,"
            + ");";

    private static final String LIMITSGOALS_CREATE = "create table "
            + TABLE_LIMITSGOALS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NID + " REFERENCES "+TABLE_NUTRIENTS+"("+ COLUMN_ID +"),"
            + COLUMN_NVALUE + " integer not null,"
            + COLUMN_TYPE + " text not null,"
            + COLUMN_PID + " FOREIGN KEY("+COLUMN_PID+") REFERENCES "+TABLE_PROFILES+"("+ COLUMN_ID +"),"
            + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(PROFILES_CREATE);
        database.execSQL(NUTRIENTS_CREATE);
        database.execSQL(LIMITSGOALS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion  + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
        onCreate(db);
    }

}