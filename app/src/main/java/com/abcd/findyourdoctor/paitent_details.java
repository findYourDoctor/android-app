package com.abcd.findyourdoctor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

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

public class paitent_details extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
   ImageView imgdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paitent_details);
        Spinner spinner=findViewById(R.id.SpinnerGen);
        Spinner spinner1=findViewById(R.id.SpinnerBG);
        Spinner spinner2=findViewById(R.id.SpinnerMS);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.Gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.Blood_Group,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.Martial_status,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

      imgdp=findViewById(R.id.imgDp);
      imgdp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ImagePicker.Companion.with(paitent_details.this)
                      .crop()
                      .cropOval()
                      .compress(1024)
                      .maxResultSize(105,72)
                      .start();
          }
      });

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