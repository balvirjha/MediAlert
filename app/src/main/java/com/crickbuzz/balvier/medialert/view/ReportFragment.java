package com.crickbuzz.balvier.medialert.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.crickbuzz.balvier.medialert.R;
import com.crickbuzz.balvier.medialert.modal.MedicinePOJO;
import com.crickbuzz.balvier.medialert.modal.MedicineSerializable;

import java.util.List;

/**
 * Created by Balvier on 9/17/2017.
 */

public class ReportFragment extends Fragment {
    private View mRoot;
    private ListView reportList;
    List<MedicinePOJO> medicinePOJOS;
    ReportListAdapter reportListAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().getSerializable("medicineData") != null) {
            medicinePOJOS = ((MedicineSerializable) getArguments().getSerializable("medicineData")).getMedicinePOJOS();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.reportfragment, container, false);
        return mRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        reportList = (ListView) mRoot.findViewById(R.id.reportListView);
        if (medicinePOJOS != null) {
            reportListAdapter = new ReportListAdapter(getActivity(), medicinePOJOS);
            reportList.setAdapter(reportListAdapter);
        }

    }
}
