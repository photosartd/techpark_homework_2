package ru.mail.homework2.fragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import ru.mail.homework2.adapter_bindet_logic.CubeList;
import ru.mail.homework2.decorators.GridItemDecoration;
import ru.mail.homework2.activities.Homework2;
import ru.mail.homework2.adapter_bindet_logic.NewAdapter;
import ru.mail.homework2.R;

/*
Main fragment with recycler list
 */
public class MainFragment extends Fragment {
    private final String NEXT_POS = "Next Position";
    private int nextPosition = 100;

    public MainFragment() {
        // Required empty public constructor
        Log.d("MainFragmentCreated", "Created");
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            nextPosition = savedInstanceState.getInt(NEXT_POS);
        }
        Log.d("Lifecycle", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Lifecycle", "onCreateView");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Lifecycle", "onViewCreated");
        //Get data
        final List<CubeList.CubeData> data = CubeList.getInstance().getData();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        //setLayoutManager - where and how data of recyclerView will be placed
        //(inside activity_homework2)
        int orientation = getResources().getConfiguration().orientation;
        RecyclerView.LayoutManager thisLayoutManager;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            thisLayoutManager = new GridLayoutManager(getContext(), 3);
        } else {
            thisLayoutManager = new GridLayoutManager(getContext(), 4);
        }
        recyclerView.setLayoutManager(thisLayoutManager);
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.addItemDecoration(new GridItemDecoration(3, 20, false));
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.addItemDecoration(new GridItemDecoration(4, 20, false));
        }
        //adapters
        final NewAdapter adapter = new NewAdapter(data, new NewAdapter.ActionListener() {
            @Override
            public void onItemClick(int id) {
                Activity activity = getActivity();
                if (activity instanceof Homework2) {
                    ((Homework2) activity).showNumberFragment(data.get(id));
                }
                Toast.makeText(getContext(), "id " + id + " tells: Im opened", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

        Button addAnotherCube = view.findViewById(R.id.addAnotherCube);
        addAnotherCube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CubeList.addRandomCube(data);
                adapter.addItem(data, nextPosition);
                Log.d("Position", "Position is: " + nextPosition);
                nextPosition++;
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NEXT_POS, nextPosition);
    }
}