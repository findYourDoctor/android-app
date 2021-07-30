package com.abcd.findyourdoctor.slots;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abcd.findyourdoctor.R;

import org.jetbrains.annotations.NotNull;

public class SlotFragment extends Fragment {

    private static final String SLOT_DATA = "slot_data";
    private RecyclerView recyclerSlots;

    private SlotWithDayData slotWithDayData;

    public SlotFragment() {
    }

    public static SlotFragment newInstance(SlotWithDayData slotWithDayData) {
        SlotFragment fragment = new SlotFragment();
        Bundle args = new Bundle();
        args.putParcelable(SLOT_DATA, slotWithDayData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            slotWithDayData = getArguments().getParcelable(SLOT_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slot, container, false);
        initUI(view);
        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        SlotsAdapter slotsAdapter = new SlotsAdapter(slotWithDayData.getSlots());
        recyclerSlots.setLayoutManager(new GridLayoutManager(requireActivity(), 3));
        recyclerSlots.setAdapter(slotsAdapter);
    }

    private void initUI(View view) {
        recyclerSlots = view.findViewById(R.id.recyclerSlots);
    }

}