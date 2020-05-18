package com.markovskij.lesson2.ui.cities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.markovskij.lesson2.R;

import java.util.ArrayList;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {
    private ArrayList<String> list;
    private Fragment fragment;

    public CityListAdapter(ArrayList<String> list, Fragment fragment) {
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        TextView textElement = holder.getTextElement();
        textElement.setText(list.get(position));
        if(fragment != null){
            fragment.registerForContextMenu(textElement);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    //добавить пункт списка

    public void addItem(String element){
        list.add(element);
        notifyItemChanged(list.size()-1);
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView city;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView){
            city = itemView.findViewById(R.id.cityListTextView);
        }
        public TextView getTextElement(){
            return city;
        }

    }



}
