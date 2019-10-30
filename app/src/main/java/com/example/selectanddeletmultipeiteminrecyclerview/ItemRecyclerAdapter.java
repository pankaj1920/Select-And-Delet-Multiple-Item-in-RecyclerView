package com.example.selectanddeletmultipeiteminrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.Item_VH> {

    ArrayList<Item_Model_Class> itemList;
    MainActivity mainActivity;
    Context context;

    public ItemRecyclerAdapter(ArrayList<Item_Model_Class> itemList, MainActivity mainActivity, Context context) {
        this.itemList = itemList;
        this.mainActivity = mainActivity;
        this.context = context;
    }


    @NonNull
    @Override
    public Item_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listt, parent, false);
        return new Item_VH(view, mainActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull Item_VH holder, int position) {

        holder.profile_image.setImageResource(itemList.get(position).getProfile_image());
        holder.name.setText(itemList.get(position).getName());
        holder.email.setText(itemList.get(position).getEmail());

        // wh
        if (mainActivity.is_in_Action_mode == false){

            holder.checkBox.setVisibility(View.GONE);

        }else {

            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class Item_VH extends RecyclerView.ViewHolder {


        ImageView profile_image;
        TextView name, email;
        CheckBox checkBox;
        MainActivity mainActivity;
        CardView CardView;

        public Item_VH(@NonNull View itemView, final MainActivity mainActivity) { //  here we have give the MainActivity Variable to handle checkbox event
            super(itemView);

            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            CardView = (CardView) itemView.findViewById(R.id.CardView);

            this.mainActivity = mainActivity;

            // when ever this card view is long Pressed onLongClick method will invoke in mainActivity
            CardView.setOnLongClickListener(mainActivity);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int psotion = getAdapterPosition();

                    mainActivity.prepareSelection(v,psotion);
                }
            });
        }
    }

    public void updateAdapter(ArrayList<Item_Model_Class> list){

        // now we have to remove object avialable in this list from adapter

        for (Item_Model_Class item : list){

            itemList.remove(item);
        }

        notifyDataSetChanged();
    }

}
