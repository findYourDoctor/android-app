package com.abcd.findyourdoctor.doctor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.abcd.findyourdoctor.doctor.entity.DoctorData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DoctorListViewModel(application: Application) : AndroidViewModel(application) {
    private val activeDoctorList: ArrayList<DoctorData> = ArrayList()
    val onlineDoctorLiveData: MutableLiveData<ArrayList<DoctorData>> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getOnlineDoctors() {
        FirebaseDatabase.getInstance().getReference("doctor").orderByChild("online").equalTo(true)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    activeDoctorList.clear()
                    for (user in snapshot.children) {
                        val data = user.getValue(DoctorData::class.java)
                        data?.id = user.key
                        data?.let { activeDoctorList.add(it) }
                    }

                    onlineDoctorLiveData.value = activeDoctorList
                }

                override fun onCancelled(error: DatabaseError) {
                    errorLiveData.value = error.message
                }

            })
    }
}