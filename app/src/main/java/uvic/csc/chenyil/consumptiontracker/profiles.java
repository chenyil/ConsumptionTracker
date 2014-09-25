package uvic.csc.chenyil.consumptiontracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import java.util.List;
import java.util.Random;

import android.app.ListActivity;

import android.view.View;
import android.widget.ArrayAdapter;

import uvic.csc.chenyil.consumptiontracker.database.Profile;
import uvic.csc.chenyil.consumptiontracker.database.ProfileDataSource;


public class profiles extends ListActivity {
    private ProfileDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        datasource = new ProfileDataSource(this);
        datasource.open();

        List<Profile> values = datasource.getAllProfiles();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Profile> adapter = new ArrayAdapter<Profile>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Profile> adapter = (ArrayAdapter<Profile>) getListAdapter();
        Profile profile = null;
        switch (view.getId()) {
            case R.id.add:
                String[] profiles = new String[] { "Dad", "Mom", "Me" };
                int nextInt = new Random().nextInt(3);
                // save the new profile to the database
                profile = datasource.createProfile(profiles[nextInt],"Male",180,150);
                adapter.add(profile);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    profile = (Profile) getListAdapter().getItem(0);
                    datasource.deleteProfile(profile);
                    adapter.remove(profile);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }
    private void newProfile()
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profiles, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_new:
                newProfile();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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