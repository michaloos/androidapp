package com.example.projekt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekt.R;
import com.example.projekt.EntityClass.SlowkoModel;
import com.example.projekt.UpdateData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SlowkoAdapter extends RecyclerView.Adapter<SlowkoAdapter.ViewHolder> implements Filterable{
    Context context;
    List<SlowkoModel> list;
    DeleteItem deletItem;
    List<SlowkoModel> listALl;

    public SlowkoAdapter(Context context, List<SlowkoModel> list,DeleteItem deleteItem) {
        this.context = context;
        this.list = list;
        this.deletItem = deleteItem;
        this.listALl = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.slowo.setText(list.get(position).getSlowo());
        holder.tlumaczenie.setText(list.get(position).getTlumaczenie());

        //aktualizowanie słowa
        holder.update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateData.class);
                intent.putExtra("id",String.valueOf(list.get(position).getId()));
                intent.putExtra("slowo",String.valueOf(list.get(position).getSlowo()));
                intent.putExtra("tlumaczenie",String.valueOf(list.get(position).getTlumaczenie()));
                context.startActivity(intent);
            }
        });

        //usuwanie słowa
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletItem.itemDelete(position,list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    //filtr do wyszukiwania słów na liście w recycler view
    Filter filter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<SlowkoModel> filteredlist = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredlist.addAll(listALl);
            }else{
                for(SlowkoModel slowkoModel: listALl){
                    if(slowkoModel.getSlowo().toLowerCase().contains(constraint.toString().toLowerCase())
                            ||slowkoModel.getTlumaczenie().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredlist.add(slowkoModel);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredlist;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((Collection<? extends SlowkoModel>) results.values);
            notifyDataSetChanged();
        }
    };

    public interface DeleteItem{
        void itemDelete(int position, int id);
    }

    //elementy jednego elementu na liście w recycler view
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView slowo;
        TextView tlumaczenie;
        Button update_button;
        Button delete_button;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            slowo = itemView.findViewById(R.id.slowko_rv);
            tlumaczenie = itemView.findViewById(R.id.tlumaczenie_rv);
            update_button = itemView.findViewById(R.id.button_update_rv);
            delete_button = itemView.findViewById(R.id.button_delete_rv);
        }
    }
}
