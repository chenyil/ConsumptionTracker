package uvic.csc.chenyil.consumptiontracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import uvic.csc.chenyil.consumptiontracker.database.Profile;
import uvic.csc.chenyil.consumptiontracker.database.ProfileDataSource;


public class profiledetails extends Activity {

    private ProfileDataSource datasource;
    private EditText mName;
    private Spinner mGender;
    private EditText mHeight;
    private EditText mWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_details);

        datasource = new ProfileDataSource(this);
        datasource.open();

        mName = (EditText)findViewById(R.id.profile_name);
        mHeight = (EditText)findViewById(R.id.profile_height);
        mWeight = (EditText)findViewById(R.id.profile_weight);
        mGender = (Spinner)findViewById(R.id.gender_spinner);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profiledetails, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createProfileByInput() {

        Profile profile = null;
        profile = datasource.createProfile("Name","Male",180,150);


    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
