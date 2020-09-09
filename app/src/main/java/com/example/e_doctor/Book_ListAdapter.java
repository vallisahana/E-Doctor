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

public class Book_ListAdapter extends ArrayAdapter {

    List list=new ArrayList();
    public Book_ListAdapter
            (Context context, int resource){
        super(context, resource);
    }
    static class LayoutHandler
    {
        TextView name,date,time,problem,phone,docname,docphone;
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
        Book_ListAdapter.LayoutHandler layoutHandler;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.book_row,parent,false);
            layoutHandler=new Book_ListAdapter.LayoutHandler();
            layoutHandler.name=(TextView)row.findViewById(R.id.txtname);
            layoutHandler.date=(TextView)row.findViewById(R.id.txtdate);
            layoutHandler.time=(TextView)row.findViewById(R.id.txttime);
            layoutHandler.problem=(TextView)row.findViewById(R.id.txtproblem);
            layoutHandler.phone=(TextView)row.findViewById(R.id.txtphone);
            layoutHandler.docname=(TextView)row.findViewById(R.id.txtdocname);
            layoutHandler.docphone=(TextView)row.findViewById(R.id.txtdocphone);
            row.setTag(layoutHandler);
        }
        else{
            layoutHandler=(Book_ListAdapter.LayoutHandler)row.getTag();
        }
        Datapovider_Book DPC = (Datapovider_Book) this.getItem(position);
        layoutHandler.name.setText(DPC.getName());
        layoutHandler.date.setText(DPC.getDate());
        layoutHandler.time.setText(DPC.getTime());
        layoutHandler.problem.setText(DPC.getProblem());
        layoutHandler.phone.setText(DPC.getPhone());
        layoutHandler.docname.setText(DPC.getDocname());
        layoutHandler.docphone.setText(DPC.getDocphone());
        return row;
    }

}
