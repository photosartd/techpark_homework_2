package ru.mail.homework2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CubeViewHolder extends RecyclerView.ViewHolder {

    private final NewAdapter.ActionListener actionListener;
    public final TextView mCube;

    public CubeViewHolder(@NonNull View itemView, NewAdapter.ActionListener actionListener) {
        super(itemView);
        this.mCube = itemView.findViewById(R.id.cube);
        this.actionListener = actionListener;
    }

    public void onBind(final int id) {
        mCube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionListener.onItemClick(id);
            }
        });
    }
}
