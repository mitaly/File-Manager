package com.mit.readingsdcard;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class ActivityTwo extends AppCompatActivity {
    ListView listview;
    ArrayList<SdObjects> listOfContents;
    SdAdapter adapter;
    String path;
    String folderName;

    void initViews(){
        listview=(ListView)findViewById(R.id.listSd);
        listOfContents=new ArrayList<>();

        path= Environment.getExternalStorageDirectory().getAbsolutePath();
        Intent data=getIntent();
        folderName=data.getStringExtra("keyFolderName");

        setTitle(folderName);

        initList(path);
        adapter=new SdAdapter(this,R.layout.list_item,listOfContents);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SdObjects sdOb = listOfContents.get(position);
                String absolutePath = sdOb.getAbsolutePath();

                Intent i=null;
                if(folderName.equals("Recordings")||folderName.equals("Songs"))
                {
                     i = new Intent(ActivityTwo.this, PlayingMusic.class);
                    i.putExtra("keyPath", absolutePath);
                    startActivity(i);
                }
                else if(folderName.equals("Images")){

                    i=new Intent(ActivityTwo.this,OpenImage.class);
                    i.putExtra("keyPath",absolutePath);
                    startActivity(i);
                }
                else if(folderName.equals("Video")){
                    i=new Intent(ActivityTwo.this, PlayingVideo.class);
                    i.putExtra("keyPath", absolutePath);
                    startActivity(i);
                }

            }
        });

  }

    void initList(String path){
        try {
            File file = new File(path);
            File[] filesArray=file.listFiles();
            String fileName;
            int icon=0;
            for(File file1:filesArray) {
                if (file1.isDirectory()) {
                    initList(file1.getAbsolutePath());
                } else {
                        fileName=file1.getName();
                        if ((fileName.endsWith(".mp3"))&&(folderName.equals("Songs"))||((fileName.endsWith(".jpg"))&&(folderName.equals("Images")))||
                                ((fileName.endsWith(".3gpp"))&&(folderName.equals("Recordings"))) || ((folderName.equals("Video"))&&(fileName.endsWith(".mp4")))){

                            if(folderName.equals("Songs")||folderName.equals("Recordings"))
                             icon=R.drawable.music1;

                            else if(folderName.equals("Images"))
                                icon=R.drawable.gallery3;

                            else if(folderName.equals("Video"))
                                icon=R.drawable.videoicon;

                            listOfContents.add(new SdObjects(icon, file1.getName(), file1.getAbsolutePath()));
                        }

                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initViews();
    }
}
