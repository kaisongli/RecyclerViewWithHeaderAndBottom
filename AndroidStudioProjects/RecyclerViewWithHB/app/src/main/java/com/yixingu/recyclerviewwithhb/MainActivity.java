package com.yixingu.recyclerviewwithhb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yixingu.recyclerviewwithhb.adapter.HeadBottomAdapter;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HeadBottomAdapter adapter;
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        adapter = new HeadBottomAdapter(this);
        //List布局
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }
}