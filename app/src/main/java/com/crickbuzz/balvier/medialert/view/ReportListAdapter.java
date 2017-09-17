package com.crickbuzz.balvier.medialert.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.crickbuzz.balvier.medialert.R;
import com.crickbuzz.balvier.medialert.modal.MedicinePOJO;

import java.util.List;

/**
 * Created by Balvier on 9/17/2017.
 */

public class ReportListAdapter extends BaseAdapter {

    List<MedicinePOJO> medicinePOJOS;
    Context context;

    public ReportListAdapter(Context context, List<MedicinePOJO> medicinePOJOS) {
        this.medicinePOJOS = medicinePOJOS;
        this.context = context;
    }

    @Override
    public int getCount() {
        return medicinePOJOS.size();
    }

    @Override
    public Object getItem(int i) {
        return medicinePOJOS.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.reportlistrow, null);
            holder = new ViewHolder();
            holder.medicineName = (TextView) convertView.findViewById(R.id.medicineName);
            holder.medicineDosage = (TextView) convertView.findViewById(R.id.medicineDosage);
            holder.intakeDAte = (TextView) convertView.findViewById(R.id.intakeDAte);
            holder.inatkeTime = (TextView) convertView.findViewById(R.id.inatkeTime);
            holder.inatkeStatus = (TextView) convertView.findViewById(R.id.inatkeStatus);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MedicinePOJO medicinePOJO = (MedicinePOJO) getItem(position);

        holder.medicineName.setText(medicinePOJO.getMedicineName());
        holder.medicineDosage.setText(String.valueOf(medicinePOJO.getDosage()));
        holder.intakeDAte.setText(medicinePOJO.getDate());
        holder.inatkeTime.setText(medicinePOJO.getTime());
        holder.inatkeStatus.setText(medicinePOJO.isMedicineTaken() ? "Taken" : "Missed");

        return convertView;
    }

    private class ViewHolder {
        TextView medicineName, medicineDosage, intakeDAte, inatkeTime, inatkeStatus;
    }
}
