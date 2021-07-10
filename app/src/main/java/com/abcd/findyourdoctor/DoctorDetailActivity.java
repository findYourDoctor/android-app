package com.abcd.findyourdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.abcd.findyourdoctor.doctor.entity.DoctorDetailData;
import com.abcd.findyourdoctor.messaging.ui.ChatActivity;
import com.abcd.findyourdoctor.serverrequest.MockRepository;
import com.squareup.picasso.Picasso;

public class DoctorDetailActivity extends AppCompatActivity {

    private DoctorDetailData doctorDetailData;
    private TextView txtName;
    private ImageView imgProfilePic;
    private TextView txtExp;
    private TextView txtLan;
    private ImageView imgSymbol;
    private Button btnChat;
    private Button btnBookSlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        doctorDetailData = MockRepository.getInstance().getData(this, DoctorDetailData.class, "doctor_detail.json");
        initialiseView();
        setData();
        setOnClickListener();
    }

    private void initialiseView() {
        txtName = findViewById(R.id.txtName);
        imgProfilePic = findViewById(R.id.imgProfilePic);
        txtExp = findViewById(R.id.txtExp);
        txtLan=findViewById(R.id.txtLan);
        imgSymbol=findViewById(R.id.imgSymbol);
        btnChat=findViewById(R.id.btnChat);
        btnBookSlot=findViewById(R.id.btnBookSlot);
    }

    private void setData() {
        txtName.setText(doctorDetailData.getName());
        setProfileImage();
        txtExp.setText(doctorDetailData.getExperience());
        txtLan.setText(doctorDetailData.getLanguages());
    }

    private void setProfileImage() {
        Picasso.get().load(doctorDetailData.getProfilePic()).into(imgProfilePic);

    }

    private void setOnClickListener() {
        btnBookSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailActivity.this, ChatActivity.class));
            }
        });

    }
}