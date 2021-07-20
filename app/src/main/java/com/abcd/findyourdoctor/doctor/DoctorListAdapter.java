package com.abcd.findyourdoctor.doctor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.chatmodule.DoctorConstants;
import com.abcd.findyourdoctor.DoctorDetailActivity;
import com.abcd.findyourdoctor.R;
import com.abcd.findyourdoctor.doctor.entity.DoctorData;
import com.app.chatmodule.messaging.entity.SecondUserData;
import com.app.chatmodule.messaging.ui.ChatActivity;

import java.util.List;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ViewHolder> {

    private List<DoctorData> doctorDataList;

    public DoctorListAdapter(List<DoctorData> doctorDataList) {
        this.doctorDataList = doctorDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(doctorDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return doctorDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtSpeciality;
        Button btnChat;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            txtName = itemView.findViewById(R.id.txtName);
            btnChat = itemView.findViewById(R.id.Chat_Btn);
            txtSpeciality = itemView.findViewById(R.id.txtDepartment);
        }

        public void bindData(DoctorData doctorData) {
            txtName.setText(doctorData.getName());
            if (doctorData.isOnline()) {
                btnChat.setVisibility(View.VISIBLE);
            } else {
                btnChat.setVisibility(View.GONE);
            }
            txtSpeciality.setText(doctorData.getSpeciality());


            btnChat.setOnClickListener(v -> {
                SecondUserData secondUserData = new SecondUserData();
                secondUserData.setId(doctorData.getId());
                secondUserData.setName(doctorData.getName());
                secondUserData.setImageUrl("");
                Intent intent = new Intent(v.getContext(), ChatActivity.class);
                intent.putExtra(DoctorConstants.DOCTOR_DATA, secondUserData);
                v.getContext().startActivity(intent);
            });
            view.setOnClickListener(view1 -> {
                Intent intent = new Intent(view.getContext(), DoctorDetailActivity.class);
                intent.putExtra(DoctorConstants.DOCTOR_DATA, doctorData);
                view.getContext().startActivity(intent);
            });
        }
    }
}
