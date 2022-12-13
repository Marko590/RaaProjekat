package com.marko590.raaprojekat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import com.marko590.raaprojekat.R;
import com.marko590.raaprojekat.models.Dessert;

/**
 * Created by Suleiman on 02/03/17.
 */

public class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.DessertVh> {

    private List<Dessert> desserts;
    private Context context;

    public DessertAdapter(Context context) {
        this.context = context;

        desserts = Dessert.prepareDesserts(
                context.getResources().getStringArray(R.array.dessert_names),
                context.getResources().getStringArray(R.array.dessert_descriptions));
    }

    @Override
    public DessertVh onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_dessert, parent, false);
        return new DessertAdapter.DessertVh(view);
    }

    @Override
    public void onBindViewHolder(DessertVh holder, int position) {
        Dessert dessert = desserts.get(position);

        holder.mName.setText(dessert.getName());
        holder.mDescription.setText(dessert.getDescription());
        holder.mFirstLetter.setText(String.valueOf(dessert.getFirstLetter()));

    }

    @Override
    public int getItemCount() {
        return desserts == null ? 0 : desserts.size();
    }

    public static class DessertVh extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mDescription;
        private TextView mFirstLetter;

        public DessertVh(View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.txt_name);
            mDescription = itemView.findViewById(R.id.txt_desc);
            mFirstLetter = itemView.findViewById(R.id.txt_firstletter);
        }
    }
}