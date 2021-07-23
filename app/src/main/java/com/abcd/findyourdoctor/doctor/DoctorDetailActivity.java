package com.abcd.findyourdoctor.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abcd.findyourdoctor.BaseActivity;
import com.abcd.findyourdoctor.R;
import com.abcd.findyourdoctor.doctor.entity.DoctorData;
import com.abcd.findyourdoctor.doctor.entity.DoctorDetailData;
import com.abcd.findyourdoctor.doctor.entity.Education;
import com.app.chatmodule.DoctorConstants;
import com.app.chatmodule.messaging.entity.SecondUserData;
import com.app.chatmodule.messaging.ui.ChatActivity;
import com.abcd.findyourdoctor.serverrequest.MockRepository;
import com.squareup.picasso.Picasso;

public class DoctorDetailActivity extends BaseActivity {

    private DoctorDetailData doctorDetailData;
    private TextView txtName;
    private ImageView imgProfilePic;
    private TextView txtExp;
    private TextView txtLan;
    //    private ImageView imgSymbol;
    private Button btnChat;
    private Button btnBookSlot;
    private DoctorData doctorData;
    private LinearLayout layoutEducationContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        doctorDetailData = MockRepository.getInstance().getData(this, DoctorDetailData.class, "doctor_detail.json");
        initialiseView();
        setData();
        setOnClickListener();
        if (getIntent().getExtras() != null) {
            doctorData = getIntent().getParcelableExtra(DoctorConstants.DOCTOR_DATA);
        }
        showBackButton();
        setTitle("Doctor Detail");
    }

    private void initialiseView() {
        txtName = findViewById(R.id.txtName);
        imgProfilePic = findViewById(R.id.imgProfilePic);
        txtExp = findViewById(R.id.txtExp);
        txtLan = findViewById(R.id.txtLan);
        btnChat = findViewById(R.id.btnChat);
        btnBookSlot = findViewById(R.id.btnBookSlot);
        layoutEducationContainer = findViewById(R.id.educationContainer);
    }

    private void setData() {
        txtName.setText(doctorDetailData.getName());
        setProfileImage();
        txtExp.setText(doctorDetailData.getExperience());
        txtLan.setText(doctorDetailData.getLanguages());

        for (Education education : doctorDetailData.getEducation()) {
            View view = LayoutInflater.from(this).inflate(R.layout.education_view, null);
            TextView txtDegree = view.findViewById(R.id.txtDegree);
            TextView txtCollege = view.findViewById(R.id.txtCollege);

            txtDegree.setText(education.getEducationName());
            txtCollege.setText(education.getInstitute() + ", " + education.getPassingYear());
            layoutEducationContainer.addView(view);
        }
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
                SecondUserData secondUserData = new SecondUserData();
                secondUserData.setId(doctorData.getId());
                secondUserData.setName(doctorData.getName());
                secondUserData.setImageUrl("");
                Intent intent = new Intent(DoctorDetailActivity.this, ChatActivity.class);
                intent.putExtra(DoctorConstants.DOCTOR_DATA, secondUserData);
                startActivity(intent);
            }
        });

    }
}