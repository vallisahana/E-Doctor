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

public class Doc_ListAdapter extends ArrayAdapter {

    List list=new ArrayList();
    public Doc_ListAdapter
            (Context context, int resource){
        super(context, resource);
    }
    static class LayoutHandler
    {
        TextView NAME,EMAIL,PASSWORD,PHONE;
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
        Doc_ListAdapter.LayoutHandler layoutHandler;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.doctor_row,parent,false);
            layoutHandler=new Doc_ListAdapter.LayoutHandler();
            layoutHandler.NAME=(TextView)row.findViewById(R.id.txtusername);
            layoutHandler.EMAIL=(TextView)row.findViewById(R.id.txtuseremail);
            layoutHandler.PASSWORD=(TextView)row.findViewById(R.id.txtuserpassword);
            layoutHandler.PHONE=(TextView)row.findViewById(R.id.txtuserphone);
            row.setTag(layoutHandler);
        }
        else{
            layoutHandler=(Doc_ListAdapter.LayoutHandler)row.getTag();
        }
      Dataprovider_Doc DPC = (Dataprovider_Doc) this.getItem(position);
        layoutHandler.NAME.setText(DPC.getUser_name());
        layoutHandler.EMAIL.setText(DPC.getUser_email());
        layoutHandler.PASSWORD.setText(DPC.getUser_password());
        layoutHandler.PHONE.setText(DPC.getUser_phone());
        return row;
    }

}
