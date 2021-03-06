package ru.mail.homework2.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
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

import java.util.List;
import java.util.Objects;

import ru.mail.homework2.adapter_bindet_logic.CubeList;
import ru.mail.homework2.decorators.GridItemDecoration;
import ru.mail.homework2.adapter_bindet_logic.NewAdapter;
import ru.mail.homework2.R;

/*
Main fragment with recycler list
 */
public class MainFragment extends Fragment {
    private final String NEXT_POS = "Next Position";
    private int nextPosition = 100;
    private NewAdapter adapter;
    private List<CubeList.CubeData> data;

    public MainFragment() {
        // Required empty public constructor
        Log.d("MainFragmentCreated", "Created");
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //Get data
        data = CubeList.getData();

        if (context instanceof NewAdapter.ActionListener) {
            NewAdapter.ActionListener actionListener = ((NewAdapter.ActionListener) context);
            adapter = new NewAdapter(data, actionListener);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Lifecycle", "onViewCreated");


        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        //setLayoutManager - where and how data of recyclerView will be placed
        //(inside activity_homework2)
        int orientation = getResources().getConfiguration().orientation;
        RecyclerView.LayoutManager thisLayoutManager;
        Resources res = Objects.requireNonNull(getContext()).getResources();
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            thisLayoutManager = new GridLayoutManager(getContext(), res.getInteger(R.integer.span_count_portrait));
        } else {
            thisLayoutManager = new GridLayoutManager(getContext(), res.getInteger(R.integer.span_count_landscape));
        }
        recyclerView.setLayoutManager(thisLayoutManager);
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.addItemDecoration(new GridItemDecoration(res.getInteger(R.integer.span_count_portrait),
                    res.getInteger(R.integer.spacing_px), res.getBoolean(R.bool.include_edge)));
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.addItemDecoration(new GridItemDecoration(res.getInteger(R.integer.span_count_landscape),
                    res.getInteger(R.integer.spacing_px), res.getBoolean(R.bool.include_edge)));
        }
        //adapters
        recyclerView.setAdapter(adapter);

        Button addAnotherCube = view.findViewById(R.id.addAnotherCube);
        addAnotherCube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CubeList.addRandomCube();
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