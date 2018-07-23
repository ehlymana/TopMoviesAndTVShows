package ehlymana.topmoviesandtvshows;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ActivityDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        TMDbObject item = intent.getParcelableExtra("item");
        TextView name = (TextView)findViewById(R.id.nameDetails);
        ImageView image = (ImageView)findViewById(R.id.imageDetails);
        TextView description = (TextView)findViewById(R.id.descriptionDetails);
        name.setText(item.getName());
        Picasso.get().load(item.getImage()).into(image);
        description.setText(item.getDescription());
    }
    //ako ne bude radilo onda probaj super.onBackPressed();
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}