package com.mit.readingsdcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mitaly on 6/30/2016.
 */
public class SdAdapterActOne extends ArrayAdapter<SdObjectsActOne> {

    Context cxt;
    int res;
    ArrayList<SdObjectsActOne> list;

    public SdAdapterActOne(Context context, int resource, ArrayList<SdObjectsActOne> objects) {
        super(context, resource, objects);
        cxt=context;
        res=resource;
        list=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view= LayoutInflater.from(cxt).inflate(res,parent,false);

        ImageView icon=(ImageView)view.findViewById(R.id.imageViewIcon);
        TextView folderName=(TextView)view.findViewById(R.id.textViewFile);

        SdObjectsActOne ob=list.get(position);
        icon.setBackgroundResource(ob.getIcon());
        folderName.setText(ob.getFolderName());

        return view;
    }
}
