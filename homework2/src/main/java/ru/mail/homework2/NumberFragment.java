package ru.mail.homework2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumberFragment extends Fragment {
    private static final String NUMBER_KEY = "Text";
    private static final String COLOR_KEY = "Cube color";
    private String text;
    private int color;

    public static NumberFragment newInstance(String text, int color) {
        NumberFragment fragment = new NumberFragment();
        Bundle args = new Bundle();
        args.putString(NUMBER_KEY, text);
        args.putInt(COLOR_KEY, color);
        fragment.setArguments(args);
        return fragment;
    }

    public NumberFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.number_parent).setBackgroundColor(color);
        TextView numberTextView = view.findViewById(R.id.number_text);
        numberTextView.setText(text);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(NUMBER_KEY);
            color = getArguments().getInt(COLOR_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_number, container, false);
    }
}