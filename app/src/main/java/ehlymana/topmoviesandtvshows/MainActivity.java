package ehlymana.topmoviesandtvshows;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
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
                }
                else if (!buttonM.isChecked() && !buttonT.isChecked()) {
                    buttonM.setChecked(true);
                }
            }
        });
        buttonT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (buttonT.isChecked() && buttonM.isChecked()) {
                    buttonM.setChecked(false);
                }
                else if (!buttonT.isChecked() && !buttonM.isChecked()) {
                    buttonT.setChecked(true);
                }
            }
        });
        //endregion
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentTVShowList fragmentTVShowList = new FragmentTVShowList();
        Bundle arguments=new Bundle();
        arguments.putParcelableArrayList("TVShowList", TVShowList);
        fragmentTVShowList.setArguments(arguments);
        transaction.replace(R.id.fragment, fragmentTVShowList);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) super.onBackPressed();
        else getFragmentManager().popBackStack();
    }
}
