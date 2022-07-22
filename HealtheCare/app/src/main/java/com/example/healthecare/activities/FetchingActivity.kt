package com.example.healthecare.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthecare.R
import com.example.healthecare.adapters.PatAdapter
import com.example.healthecare.models.PatientsModel
import com.google.firebase.database.*

class FetchingActivity: AppCompatActivity() {

    private lateinit var patRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var patList: ArrayList<PatientsModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        patRecyclerView = findViewById(R.id.rvPatients)
        patRecyclerView.layoutManager = LinearLayoutManager(this)
        patRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        patList = arrayListOf<PatientsModel>()

        getPatientsData()
    }

    private fun getPatientsData(){
        patRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Patients")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                patList.clear()
                if(snapshot.exists()){
                    for(patSnap in snapshot.children){
                        val patData = patSnap.getValue(PatientsModel::class.java)
                        patList.add(patData!!)
                    }
                    val mAdapter = PatAdapter(patList)
                    patRecyclerView.adapter = mAdapter
///////////////////////////////
                    mAdapter.setOnItemClickListener(object : PatAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingActivity, PatientsDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("patId", patList[position].patId)
                            intent.putExtra("namePat", patList[position].namePat)
                            intent.putExtra("agePat", patList[position].agePat)
                            intent.putExtra("sexPat", patList[position].sexPat)
                            intent.putExtra("addressPat", patList[position].addressPat)
                            intent.putExtra("complaintPat", patList[position].complaintPat)
                            intent.putExtra("diagnosisPat", patList[position].diagnosisPat)
                            intent.putExtra("treatmentPat", patList[position].treatmentPat)
                            startActivity(intent)

                        }

                    })
////////////////////////////////////
                    patRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}