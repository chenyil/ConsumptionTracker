package uvic.csc.chenyil.consumptiontracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import java.util.List;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;

import android.app.ListActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import uvic.csc.chenyil.consumptiontracker.database.Profile;
import uvic.csc.chenyil.consumptiontracker.database.ProfileDataSource;


public class profiles extends ListActivity {
    private ProfileDataSource datasource;
    private static final int DELETE_ID = Menu.FIRST + 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        datasource = new ProfileDataSource(this);
        datasource.open();

        updateAdapter();
    }

    // Opens the second activity if an entry is clicked
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent i = new Intent(this, profiledetails.class);
        Profile profile = (Profile) l.getAdapter().getItem((int)id);
        i.putExtra("Name",profile.getName());
        i.putExtra("Gender",profile.getGender());
        i.putExtra("Height", profile.getHeight());
        i.putExtra("Weight", profile.getWeight());
        i.putExtra("ID",profile.getId());
        startActivity(i);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case DELETE_ID:
                datasource.deleteProfile((Profile) getListView().getSelectedItem());
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void updateAdapter()
    {
        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        List<Profile> values = datasource.getAllProfiles();
        ArrayAdapter<Profile> adapter = new ArrayAdapter<Profile>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    private void newProfile()
    {
        Intent i = new Intent(this,profiledetails.class);
        startActivity(i);
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
        updateAdapter();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}