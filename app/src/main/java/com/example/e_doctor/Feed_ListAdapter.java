package com.example.e_doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Feed_ListAdapter extends ArrayAdapter {

    List list=new ArrayList();
    public Feed_ListAdapter
            (Context context, int resource){
        super(context, resource);
    }
    static class LayoutHandler
    {
        TextView name,feed,rate;
    }
    public  void add(Object object){
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row=convertView;
        Feed_ListAdapter.LayoutHandler layoutHandler;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.feed_row,parent,false);
            layoutHandler=new Feed_ListAdapter.LayoutHandler();
            layoutHandler.name=(TextView)row.findViewById(R.id.txtname);
            layoutHandler.feed=(TextView)row.findViewById(R.id.txtfeedback);
            //layoutHandler.rate=(TextView)row.findViewById(R.id.txtrate);

            row.setTag(layoutHandler);
        }
        else{
            layoutHandler=(Feed_ListAdapter.LayoutHandler)row.getTag();
        }
        Datapovider_Feed DPC = (Datapovider_Feed) this.getItem(position);
        layoutHandler.name.setText(DPC.getName());
        layoutHandler.feed.setText(DPC.getFeedback());
       // layoutHandler.rate.setText(DPC.getRate());

        return row;
    }

}
