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

public class Exam_ListAdapter extends ArrayAdapter {

    List list=new ArrayList();
    public Exam_ListAdapter
            (Context context, int resource){
        super(context, resource);
    }
    static class LayoutHandler
    {
        TextView docname,docspe,docexp,docaddress,docfees,docphone;
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
        Exam_ListAdapter.LayoutHandler layoutHandler;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.exam_row,parent,false);
            layoutHandler=new Exam_ListAdapter.LayoutHandler();
            layoutHandler.docname=(TextView)row.findViewById(R.id.txtexamname);
            layoutHandler.docspe=(TextView)row.findViewById(R.id.txtexamdate);
            layoutHandler.docexp=(TextView)row.findViewById(R.id.txtexamtime);
            layoutHandler.docaddress=(TextView)row.findViewById(R.id.txtexamvenue);
            layoutHandler.docfees=(TextView)row.findViewById(R.id.txtfees);
            layoutHandler.docphone=(TextView)row.findViewById(R.id.txtphone);
            row.setTag(layoutHandler);
        }
        else{
            layoutHandler=(Exam_ListAdapter.LayoutHandler)row.getTag();
        }
        Datapovider_Exam DPC = (Datapovider_Exam) this.getItem(position);
        layoutHandler.docname.setText(DPC.getDocname());
        layoutHandler.docspe.setText(DPC.getDocspec());
        layoutHandler.docexp.setText(DPC.getDocexp());
        layoutHandler.docaddress.setText(DPC.getDocaddres());
        layoutHandler.docfees.setText(DPC.getDocfees());
        layoutHandler.docphone.setText(DPC.getDocphone());
        return row;
    }

}
