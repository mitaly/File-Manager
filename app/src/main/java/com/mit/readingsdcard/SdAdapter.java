package com.mit.readingsdcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mitaly on 6/30/2016.
 */
public class SdAdapter extends ArrayAdapter<SdObjects> {
    Context cxt;
    int res;
    ArrayList<SdObjects> listOfContacts;

    public SdAdapter(Context context, int resource, ArrayList<SdObjects> objects) {
        super(context, resource, objects);
        cxt=context;
        res=resource;
        listOfContacts=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;

        //Initializing view pointing to layout file list_item
        view= LayoutInflater.from(cxt).inflate(res,parent,false);

        //Objects of ImageView and TextView created:Has reference of views in layout file
        ImageView icon=(ImageView)view.findViewById(R.id.imageViewIcon);
        TextView fileName=(TextView)view.findViewById(R.id.textViewFile);

        SdObjects sdOb=listOfContacts.get(position);
        //Setting the Icon and FileName
        icon.setBackgroundResource(sdOb.getIcon());
        fileName.setText(sdOb.getFileName());

        return view;
    }
}
