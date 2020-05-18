package com.markovskij.lesson2.ui.cities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.markovskij.lesson2.R;
import com.markovskij.lesson2.ui.EventBus;
import com.markovskij.lesson2.ui.events.AddCitiesEvent;
import com.markovskij.lesson2.ui.home.HomeFragment;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;

public class CitiesFragment extends Fragment {

    private CityListAdapter adapter;
    View root;
    private ArrayList<String> list = new ArrayList<>();
    private AddCitiesEvent event;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_city_list, container, false);
        initRecyclerView(root);
        return root;
    }

    @Subscribe
    private void initRecyclerView(View root){
        RecyclerView recyclerView = root.findViewById(R.id.rv_listCity);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CityListAdapter(list,  this);
        recyclerView.setAdapter(adapter);
    }

    @Subscribe
    public void addCity(AddCitiesEvent event){
        adapter.addItem(event.cityName);
    }

//    private ArrayList<String> initData(){
//        for (int i = 0; i < list.size(); i++) {
//            list.add(String.format("%d", i));
//        }
//        return list;
//    }

    @Override
    public void onStart() {
        EventBus.getBus().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        EventBus.getBus().unregister(this);
        super.onStop();
    }
}
