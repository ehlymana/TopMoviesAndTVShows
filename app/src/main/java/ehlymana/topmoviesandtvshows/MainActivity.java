package ehlymana.topmoviesandtvshows;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentTVShowList.OnItemClick, FragmentMovieList.OnItemClick {
    final ArrayList<TMDbObject> movieList=new ArrayList<>();
    final ArrayList<TMDbObject> TVShowList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //region Toggle Buttons Logic
        final ToggleButton buttonM=(ToggleButton)findViewById(R.id.buttonM);
        final ToggleButton buttonT=(ToggleButton)findViewById(R.id.buttonT);
        buttonM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (buttonM.isChecked() && buttonT.isChecked()) {
                    buttonT.setChecked(false);
                    changeFragment(2);
                }
                else if (!buttonM.isChecked() && !buttonT.isChecked()) {
                    buttonM.setChecked(true);
                    changeFragment(2);
                }
            }
        });
        buttonT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (buttonT.isChecked() && buttonM.isChecked()) {
                    buttonM.setChecked(false);
                    changeFragment(1);
                }
                else if (!buttonT.isChecked() && !buttonM.isChecked()) {
                    buttonT.setChecked(true);
                    changeFragment(1);
                }
            }
        });
        //endregion
        changeFragment(1);
    }
    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) super.onBackPressed();
        else getFragmentManager().popBackStack();
    }
    void changeFragment (int type) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle arguments=new Bundle();
        if (type==1) {
            FragmentTVShowList fragmentTVShowList = new FragmentTVShowList();
            arguments.putParcelableArrayList("TVShowList", TVShowList);
            fragmentTVShowList.setArguments(arguments);
            transaction.replace(R.id.fragment, fragmentTVShowList);
        }
        else if (type==2) {
            FragmentMovieList fragmentMovieList = new FragmentMovieList();
            arguments.putParcelableArrayList("movieList", movieList);
            fragmentMovieList.setArguments(arguments);
            transaction.replace(R.id.fragment, fragmentMovieList);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onItemClickedMovie (int pos) {
        Intent intent = new Intent(MainActivity.this, ActivityDetails.class);
        intent.putExtra("type", "movie");
        intent.putExtra("name", movieList.get(pos).name);
        MainActivity.this.startActivity(intent);
    }
    @Override
    public void onItemClickedTVShow (int pos) {
        Intent intent = new Intent(MainActivity.this, ActivityDetails.class);
        intent.putExtra("type", "TVShow");
        intent.putExtra("name", TVShowList.get(pos).name);
        MainActivity.this.startActivity(intent);
    }
}
