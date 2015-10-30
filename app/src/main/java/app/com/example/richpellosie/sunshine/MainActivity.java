package app.com.example.richpellosie.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id==R.id.action_settings)
        {
            Intent settingsIntent = new Intent(this,SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        if(id==R.id.map_location)
        {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String preferredLocation = prefs.getString(getString(R.string.pref_location_key),getString(R.string.pref_default_value));
            String geoData = "geo:0,0?q=" + preferredLocation;
            Uri geoUri = Uri.parse(geoData);
            mapIntent.setData(geoUri);
            if(mapIntent.resolveActivity(getPackageManager())!=null)
            {
                startActivity(mapIntent);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
