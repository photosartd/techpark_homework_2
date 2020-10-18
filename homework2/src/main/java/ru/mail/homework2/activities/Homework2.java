package ru.mail.homework2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import ru.mail.homework2.R;
import ru.mail.homework2.adapter_bindet_logic.CubeList;
import ru.mail.homework2.fragments.MainFragment;
import ru.mail.homework2.fragments.NumberFragment;
/*
Main activity
 */
public class Homework2 extends AppCompatActivity {
    private final String MAIN_TAG = "maintag";
    private final String NUMBER_TAG = "numbertag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2);

        FrameLayout contentParent = findViewById(R.id.content_parent);
        FragmentManager fragmentManager = getSupportFragmentManager();

        MainFragment mainFragment = MainFragment.newInstance();
        //это делаем если только savedInstanceState == null
        if (getSupportFragmentManager().findFragmentById(R.id.content_parent) == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.content_parent, mainFragment, MAIN_TAG)
                    .commit();
        }
    }

    public void showNumberFragment(CubeList.CubeData cube) {
        FragmentManager fm = getSupportFragmentManager();
        NumberFragment numberFragment = NumberFragment.newInstance(cube.getText(), cube.getColor());
        fm.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.content_parent, numberFragment, NUMBER_TAG).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_parent);
        if (currentFragment instanceof NumberFragment) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
