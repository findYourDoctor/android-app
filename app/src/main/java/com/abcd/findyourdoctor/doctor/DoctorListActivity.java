package com.abcd.findyourdoctor.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

public class DoctorListActivity extends AppCompatActivity {

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
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        database.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {

                    for (DataSnapshot user : task.getResult().getChildren()) {
                        Log.d("", "");
                        DoctorData data = user.getValue(DoctorData.class);
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