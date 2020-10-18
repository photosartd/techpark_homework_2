package ru.mail.homework2.adapter_bindet_logic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import ru.mail.homework2.R;

/*
Adapter for recycler list
 */
public class NewAdapter extends RecyclerView.Adapter<CubeViewHolder> {

    private List<CubeList.CubeData> mData;
    private ActionListener actionListener;

    public NewAdapter(List<CubeList.CubeData> mData, ActionListener actionListener) {
        this.mData = mData;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public CubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        return new CubeViewHolder(view, actionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CubeViewHolder holder, int position) {
        CubeList.CubeData model = mData.get(position);
        holder.mCube.setBackgroundColor(model.getColor());
        holder.mCube.setText(model.getText());
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addItem(List<CubeList.CubeData> mData, int position) {
        this.mData = mData;
        notifyItemInserted(position);
    }

    public interface ActionListener {
        void onItemClick(int id);
    }
}
