package com.abcd.findyourdoctor.doctor;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abcd.findyourdoctor.BaseActivity;
import com.abcd.findyourdoctor.R;
import com.abcd.findyourdoctor.doctor.entity.DoctorData;
import com.app.chatmodule.messaging.ChatConstant;
import com.app.chatmodule.messaging.viewmodel.ChatViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DoctorListActivity extends BaseActivity {

    private DoctorListViewModel viewModel;
    private List<DoctorData> doctorDataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private boolean shouldFetchOnlineDoctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        viewModel = new ViewModelProvider(this).get(DoctorListViewModel.class);
        recyclerView = findViewById(R.id.recyclerDoctorList);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            shouldFetchOnlineDoctors = extras.getBoolean(ChatConstant.FETCH_ONLINE);
        }
        getDoctorList();
        showBackButton();
        if (shouldFetchOnlineDoctors) {
            setTitle("Online Doctors");
        } else {
            setTitle("Near By Doctors");
        }
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

        if (shouldFetchOnlineDoctors) {
            viewModel.getOnlineDoctors();
            viewModel.getOnlineDoctorLiveData().observe(this, doctorData -> {
                hideProgressDialog();
                DoctorListAdapter doctorListAdapter = new DoctorListAdapter(doctorData);
                recyclerView.setLayoutManager(new LinearLayoutManager(DoctorListActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(doctorListAdapter);
            });
            viewModel.getErrorLiveData().observe(this, message -> {
                hideProgressDialog();
                Toast.makeText(DoctorListActivity.this, "Error getting data, " + message, Toast.LENGTH_SHORT).show();
            });
        } else {
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
}