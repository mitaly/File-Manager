package com.mit.readingsdcard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class OpenImage extends AppCompatActivity {

    ImageView image;

    void initViews(){
        image=(ImageView)findViewById(R.id.imageViewImage);

        Intent data=getIntent();
        String url=data.getStringExtra("keyPath");
        image.setImageURI(Uri.parse(url));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_image);
        initViews();
    }
}
