package com.king.sqliPHP.PHP;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.king.sqliPHP.R;
import com.king.sqliPHP.Student;

import java.util.ArrayList;

public class listViewAdapter {
    public ArrayList<Student> personlist;
    Activity activity;

    public listViewAdapter(Activity activity, ArrayList<Student> personlist) {
        super();
        this.activity = activity;
        this.personlist = personlist;
    }

    public int getCount() {
        return personlist.size();
    }

    public Object getItem(int position) {
        return personlist.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();
        if (convertView == null) {
            holder = new ViewHolder();
            holder.mSid = (TextView) convertView.findViewById(R.id.idText);
            holder.mName = (TextView) convertView.findViewById(R.id.NameText);
            holder.mPhone = (TextView) convertView.findViewById(R.id.PhoneText);
            holder.mEmail = (TextView) convertView.findViewById(R.id.EmailText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Student item = personlist.get(position);
        holder.mSid.setText(item.getId());
        holder.mName.setText(item.getName());
        holder.mPhone.setText(item.getPhone());
        holder.mEmail.setText(item.getEmail());
        return convertView;
    }

    private class ViewHolder {
        TextView mSid;
        TextView mName;
        TextView mPhone;
        TextView mEmail;
    }
}
