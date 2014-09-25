package uvic.csc.chenyil.consumptiontracker.database;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by imacadmin on 2014-09-22.
 */
public class ProfileDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_NAME,
            MySQLiteHelper.COLUMN_GENDER, MySQLiteHelper.COLUMN_HEIGHT,MySQLiteHelper.COLUMN_WEIGHT
            };

    public ProfileDataSource (Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Profile createProfile(String name,String gender, int height, int weight) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        values.put(MySQLiteHelper.COLUMN_GENDER, gender);
        values.put(MySQLiteHelper.COLUMN_HEIGHT, height);
        values.put(MySQLiteHelper.COLUMN_WEIGHT, weight);
        long insertId = database.insert(MySQLiteHelper.TABLE_PROFILES, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_PROFILES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Profile newProfile = cursorToProfile(cursor);
        cursor.close();
        return newProfile;
    }

    public void deleteProfile(Profile profile) {
        long id = profile.getId();
        System.out.println("Profile deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_PROFILES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Profile> getAllProfiles() {
        List<Profile > profiles = new ArrayList<Profile>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PROFILES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Profile  comment = cursorToProfile(cursor);
            profiles.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return profiles;
    }

    private Profile cursorToProfile(Cursor cursor) {
        Profile  profile = new Profile();
        profile.setId(cursor.getLong(0));
        profile.setName(cursor.getString(1));
        profile.setGender(cursor.getString(2));
        profile.setHeight(cursor.getInt(3));
        profile.setWeight(cursor.getInt(4));
        return profile;
    }

}
