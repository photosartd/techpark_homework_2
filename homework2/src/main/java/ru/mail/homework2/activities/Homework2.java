package ru.mail.homework2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;

import java.util.List;

import ru.mail.homework2.R;
import ru.mail.homework2.adapter_bindet_logic.CubeList;
import ru.mail.homework2.adapter_bindet_logic.NewAdapter;
import ru.mail.homework2.fragments.MainFragment;
import ru.mail.homework2.fragments.NumberFragment;

/*
Main activity
 */
public class Homework2 extends AppCompatActivity implements NewAdapter.ActionListener {
    private final String LAST_ELEMENT_BEFORE_KILL = "Last element";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework2);

        FragmentManager fragmentManager = getSupportFragmentManager();

        MainFragment mainFragment = new MainFragment();
        //это делаем если только savedInstanceState == null
        if (getSupportFragmentManager().findFragmentById(R.id.content_parent) == null) {
            String MAIN_TAG = "maintag";
            fragmentManager
                    .beginTransaction()
                    .add(R.id.content_parent, mainFragment, MAIN_TAG)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        int lastElement = CubeList.getData().size();
        outState.putInt(LAST_ELEMENT_BEFORE_KILL, lastElement);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(LAST_ELEMENT_BEFORE_KILL)) {
            int lastElement = savedInstanceState.getInt(LAST_ELEMENT_BEFORE_KILL);
            List<CubeList.CubeData> data = CubeList.getData();
            for (int i = data.size() + 1; i <= lastElement; i++) {
                String text = String.valueOf(i);
                int color = (i % 2 == 1) ? Color.RED : Color.BLUE;
                data.add(new CubeList.CubeData(text, color));
            }
        }
    }

    private void showNumberFragment(CubeList.CubeData cube) {
        FragmentManager fm = getSupportFragmentManager();
        NumberFragment numberFragment = NumberFragment.newInstance(cube.getText(), cube.getColor());
        String NUMBER_TAG = "numbertag";
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

    @Override
    public void onItemClick(int id) {
        showNumberFragment(CubeList.getData().get(id));
    }
}
