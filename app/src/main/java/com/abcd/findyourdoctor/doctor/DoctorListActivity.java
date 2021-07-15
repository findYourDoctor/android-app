package com.abcd.findyourdoctor.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.abcd.findyourdoctor.BaseActivity;
import com.abcd.findyourdoctor.R;
import com.abcd.findyourdoctor.doctor.entity.DoctorData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DoctorListActivity extends BaseActivity {

    private List<DoctorData> doctorDataList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        recyclerView = findViewById(R.id.recyclerDoctorList);
        getDoctorList();
    }

    private void getDoctorList() {

        showProgressDialog();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        /*DoctorData doctorData = new DoctorData();
        doctorData.setName("Doctor Dummy 1");
        doctorData.setRatings("4.5");
        doctorData.setSpeciality("Physician");
        Long timeStamp = System.currentTimeMillis();
        database.child("doctor").child(String.valueOf(timeStamp)).setValue(doctorData);*/

        database.child("doctor").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                hideProgressDialog();
                if (!task.isSuccessful()) {
                    Toast.makeText(DoctorListActivity.this, "Error getting data, " + task.getException(), Toast.LENGTH_SHORT).show();
                } else {

                    for (DataSnapshot user : task.getResult().getChildren()) {
                        DoctorData data = user.getValue(DoctorData.class);
                        Objects.requireNonNull(data).setId(user.getKey());
                        doctorDataList.add(data);
                    }

                    DoctorListAdapter doctorListAdapter = new DoctorListAdapter(doctorDataList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(DoctorListActivity.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(doctorListAdapter);
                }

            }
        });
    }
}