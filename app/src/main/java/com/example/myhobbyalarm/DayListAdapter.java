package com.example.myhobbyalarm;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.CustomViewHolder>{
    private ArrayList<ToDoItem> arrayList;

    public DayListAdapter(ArrayList<ToDoItem> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_entry, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
        holder.tvSet[0].setText(arrayList.get(position).getTITLE());
        holder.tvSet[1].setText(arrayList.get(position).getDETAIL());
        holder.tvSet[2].setText(arrayList.get(position).getTYPE());
        holder.tvSet[3].setText(arrayList.get(position).getTIME());
        holder.tvSet[4].setText(arrayList.get(position).getDATE());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentName = holder.tvSet[0].getText().toString();
                Toast.makeText(v.getContext(), currentName, Toast.LENGTH_LONG).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());

//                Intent intent = new Intent(getContext(), View_Note.class);
//                intent.putExtra(getString(R.string.rodId), id);
//                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null) ? (arrayList.size()) : (0);
    }

    private void remove(int adapterPosition) {
        try{
            arrayList.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView[] tvSet = new TextView[5];
        protected int[] tvSet_Id = {R.id.tvTitle, R.id.tvDetail, R.id.tvType, R.id.tvTime, R.id.tvDate};

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvSet[0] = itemView.findViewById(tvSet_Id[0]);
            this.tvSet[1] = itemView.findViewById(tvSet_Id[1]);
            this.tvSet[2] = itemView.findViewById(tvSet_Id[2]);
            this.tvSet[3] = itemView.findViewById(tvSet_Id[3]);
            this.tvSet[4] = itemView.findViewById(tvSet_Id[4]);
        }
    }
}
