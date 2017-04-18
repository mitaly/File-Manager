package com.mit.readingsdcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityOne extends AppCompatActivity {

    ListView listView;
    ArrayList<SdObjectsActOne> list;
    SdAdapterActOne adapterActOne;

    void initViews(){
        listView=(ListView)findViewById(R.id.listSdItems);
        list=new ArrayList<>();

        list.add(new SdObjectsActOne(R.drawable.folder1,"Songs"));
        list.add(new SdObjectsActOne(R.drawable.folder1,"Recordings"));
        list.add(new SdObjectsActOne(R.drawable.folder1,"Images"));
        list.add(new SdObjectsActOne(R.drawable.folder1,"Video"));

        adapterActOne=new SdAdapterActOne(this,R.layout.list_item,list);
        listView.setAdapter(adapterActOne);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SdObjectsActOne ob=list.get(position);
                String folderName=ob.getFolderName();
                Intent i=new Intent(ActivityOne.this,ActivityTwo.class);
                i.putExtra("keyFolderName",folderName);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        initViews();
    }
}
