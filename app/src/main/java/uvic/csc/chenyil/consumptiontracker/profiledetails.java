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

    boolean isNew=true;

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

        fillData();
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

    public void createProfileByInput(View view){

        String name = mName.getText().toString();
        String gender = mGender.getSelectedItem().toString();
        int height = Integer.parseInt(mHeight.getText().toString());
        int weight = Integer.parseInt(mWeight.getText().toString());

        if (!isNew) {
            Bundle extras = getIntent().getExtras();
            long id = extras.getLong("ID");
            datasource.updateRow(id, name, gender, height, weight);
        }
        else{
            Profile profile = null;
            profile = datasource.createProfile(name,gender,height,weight);
        }

        finish();
    }

    private void fillData()
    {
        Bundle extras = getIntent().getExtras();
        if (extras==null)
        {
            return;
        }
        isNew=false;
        mName.setText(extras.getString("Name"));
        mHeight.setText(Integer.toString(extras.getInt("Height")));
        mWeight.setText(Integer.toString(extras.getInt("Weight")));
        for (int i = 0; i < mGender.getCount(); i++) {
            String s = (String) mGender.getItemAtPosition(i);
            if (s.equalsIgnoreCase(extras.getString("Gender"))){
                mGender.setSelection(i);
            }
        }
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
