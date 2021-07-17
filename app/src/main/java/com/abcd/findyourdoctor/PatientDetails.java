package com.abcd.findyourdoctor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.drjacky.imagepicker.ImagePicker;

public class PatientDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
   ImageView imgdp;
   private String mobileNumber;
   private Spinner spinnerGender, spinnerBloodGroup, spinnerMaritalStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paitent_details);
        getDataFromBundle();
        initUI();
        setGenderAdapter();
        setBloodGroupAdapter();
        setMaritalStatusAdapter();

        imgdp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ImagePicker.Companion.with(PatientDetails.this)
                      .crop()
                      .cropOval()
                      .compress(1024)
                      .maxResultSize(105,72)
                      .start();
          }
      });

    }

    private void initUI() {
        spinnerGender=findViewById(R.id.SpinnerGen);
        spinnerBloodGroup=findViewById(R.id.SpinnerBG);
        spinnerMaritalStatus=findViewById(R.id.SpinnerMS);
        imgdp=findViewById(R.id.imgDp);
    }

    private void getDataFromBundle() {
        Bundle extras= getIntent().getExtras();
        if(extras != null) {
            mobileNumber = extras.getString("phone_number");
        }
    }

    private void setGenderAdapter() {
        ArrayAdapter<CharSequence> adapterGender= ArrayAdapter.createFromResource(this,R.array.Gender, android.R.layout.simple_spinner_item);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapterGender);
        spinnerGender.setOnItemSelectedListener(this);
    }

    private void setBloodGroupAdapter() {
        ArrayAdapter<CharSequence> adapterBloodGroup=ArrayAdapter.createFromResource(this,R.array.Blood_Group,android.R.layout.simple_spinner_item);
        adapterBloodGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodGroup.setAdapter(adapterBloodGroup);
        spinnerBloodGroup.setOnItemSelectedListener(this);
    }

    private void setMaritalStatusAdapter() {
        ArrayAdapter<CharSequence> adapterMaritalStatus=ArrayAdapter.createFromResource(this,R.array.Martial_status,android.R.layout.simple_spinner_item);
        adapterMaritalStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaritalStatus.setAdapter(adapterMaritalStatus);
        spinnerMaritalStatus.setOnItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        imgdp.setImageURI(uri);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}