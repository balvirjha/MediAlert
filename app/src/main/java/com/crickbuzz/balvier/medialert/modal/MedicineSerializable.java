package com.crickbuzz.balvier.medialert.modal;

import com.crickbuzz.balvier.medialert.modal.MedicinePOJO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Balvier on 9/17/2017.
 */

public class MedicineSerializable implements Serializable {
    List<MedicinePOJO> medicinePOJOS;

    public MedicineSerializable(List<MedicinePOJO> medicinePOJOS) {
        this.medicinePOJOS = medicinePOJOS;
    }

    public List<MedicinePOJO> getMedicinePOJOS() {
        return medicinePOJOS;
    }

    public void setMedicinePOJOS(List<MedicinePOJO> medicinePOJOS) {
        this.medicinePOJOS = medicinePOJOS;
    }
}
