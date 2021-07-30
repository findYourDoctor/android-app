package com.abcd.findyourdoctor.slots;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abcd.findyourdoctor.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SlotsAdapter extends RecyclerView.Adapter<SlotsAdapter.ViewHolder> {
    private ArrayList<SlotData> slots;
    public SlotsAdapter(ArrayList<SlotData> slots) {
        this.slots = slots;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bindData(slots.get(position));
    }

    @Override
    public int getItemCount() {
        return slots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Button btnSlots;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            btnSlots = itemView.findViewById(R.id.slotTime);
        }

        public void bindData(SlotData slotData) {
            if (slotData.isAvailable()) {
                btnSlots.setVisibility(View.VISIBLE);
                btnSlots.setText(slotData.getSlotTime());
            } else {
                btnSlots.setVisibility(View.GONE);
            }
        }
    }
}
