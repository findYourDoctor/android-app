package com.abcd.findyourdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.abcd.findyourdoctor.doctor.entity.DoctorDetailData;
import com.abcd.findyourdoctor.serverrequest.MockRepository;
import com.squareup.picasso.Picasso;

public class DoctorDetailActivity extends AppCompatActivity {

    private DoctorDetailData doctorDetailData;
    private TextView txtName;
    private ImageView imgProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        doctorDetailData = MockRepository.getInstance().getData(this, DoctorDetailData.class, "doctor_detail.json");
        initialiseView();
        setData();

    }


    private void initialiseView() {
        txtName = findViewById(R.id.txtName);
        imgProfilePic = findViewById(R.id.imgProfilePic);
    }

    private void setData() {
        txtName.setText(doctorDetailData.getName());
        setProfileImage();
    }

    private void setProfileImage() {
        Picasso.get().load(doctorDetailData.getProfilePic()).into(imgProfilePic);
    }
}