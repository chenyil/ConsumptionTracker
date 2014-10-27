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

    public static final String TABLE_FOOD="food";
    public static final String COLUMN_SERVINGSIZE="servingsize";
    public static final String COLUMN_PACKAGESIZE="packagesize";

    public static final String TABLE_GROUP="group";
    public static final String TABLE_GROUPPROFILE="groupprofile";
    public static final String COLUMN_GID="gid";

    public static final String TABLE_LIMITSGOALS = "limitsgoals";
    public static final String COLUMN_NID = "nid";
    public static final String COLUMN_VALUE= "Value";
    public static final String COLUMN_TYPE="type";
    public static final String COLUMN_PID="pid";

    public static final String TABLE_FOODNUTRIENTS = "foodnutrients";
    public static final String COLUMN_FID="fid";

    public static final String TABLE_PROFILEFOOD="profilefood";
    public static final String COLUMN_AMOUNT="amount";
    public static final String COLUMN_PDATE="purchasedate";
    public static final String COLUMN_FDATE="finishdate";

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
            + COLUMN_UNIT + "text not null"
            + ");";

    private static final String FOOD_CREATE = "create table "
            + TABLE_FOOD + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null,"
            + COLUMN_UNIT + " text not null,"
            + COLUMN_SERVINGSIZE + " real not null,"
            + COLUMN_PACKAGESIZE + " real not null"
            + ");";

    private static final String GROUP_CREATE = "create table "
            + TABLE_GROUP + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null"
            + ");";

    private static final String GROUPFROFILE_CREATE = "create table "
            + TABLE_GROUPPROFILE + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_GID + " integer not null,"
            + COLUMN_PID + " integer not null,"
            + " FOREIGN KEY("+COLUMN_GID+") REFERENCES "+TABLE_GROUP+"("+ COLUMN_ID +"),"
            + " FOREIGN KEY("+COLUMN_PID+") REFERENCES "+TABLE_PROFILES+"("+ COLUMN_ID +")"
            + ");";

    private static final String LIMITSGOALS_CREATE = "create table "
            + TABLE_LIMITSGOALS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_PID + " integer not null,"
            + COLUMN_NID + " integer not null,"
            + COLUMN_VALUE + " integer not null,"
            + COLUMN_TYPE + " text not null,"
            + " FOREIGN KEY("+COLUMN_NID+") REFERENCES "+TABLE_NUTRIENTS+"("+ COLUMN_ID +"),"
            + " FOREIGN KEY("+COLUMN_PID+") REFERENCES "+TABLE_PROFILES+"("+ COLUMN_ID +")"
            + ");";

    private static final String FOODNUTRIENTS_CREAT = "create table "
            + TABLE_FOODNUTRIENTS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_FID + " integer not null,"
            + COLUMN_NID + " integer not null,"
            + " FOREIGN KEY("+COLUMN_FID+") REFERENCES "+TABLE_FOOD+"("+ COLUMN_ID +"),"
            + " FOREIGN KEY("+COLUMN_NID+") REFERENCES "+TABLE_NUTRIENTS+"("+ COLUMN_ID +")"
            + ");";
    private static final String PROFILEFOOD_CREAT ="create table "
            + TABLE_PROFILEFOOD + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_PID + " integer not null,"
            + COLUMN_FID + " integer not null,"
            + COLUMN_AMOUNT + " real not null,"
            + COLUMN_PDATE + " integer not null,"
            + COLUMN_FDATE + " integer not null,"
            + " FOREIGN KEY("+COLUMN_PID+") REFERENCES "+TABLE_PROFILES+"("+ COLUMN_ID +"),"
            + " FOREIGN KEY("+COLUMN_FID+") REFERENCES "+TABLE_FOOD+"("+ COLUMN_ID +")"
            + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(PROFILES_CREATE);
        database.execSQL(NUTRIENTS_CREATE);
        database.execSQL(FOOD_CREATE);
        database.execSQL(GROUP_CREATE);

        database.execSQL(GROUPFROFILE_CREATE);
        database.execSQL(LIMITSGOALS_CREATE);
        database.execSQL(FOODNUTRIENTS_CREAT);
        database.execSQL(PROFILEFOOD_CREAT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion  + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILEFOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODNUTRIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIMITSGOALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPPROFILE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NUTRIENTS);
        onCreate(db);
    }

}