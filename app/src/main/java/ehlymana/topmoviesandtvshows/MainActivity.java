package ehlymana.topmoviesandtvshows;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
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
    }
}
