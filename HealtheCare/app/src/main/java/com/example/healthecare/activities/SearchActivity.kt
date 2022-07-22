package com.example.healthecare.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthecare.R
import com.example.healthecare.databinding.ActivitySearchBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SearchActivity :AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var dbRef : DatabaseReference
    private lateinit var tvId: TextView
    private lateinit var tvName: TextView
    private lateinit var tvAge: TextView
    private lateinit var tvAddress: TextView
    private lateinit var btnITR: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvId = findViewById(R.id.tvId)
        tvName = findViewById(R.id.tvName)
        tvAge = findViewById(R.id.tvAge)
        tvAddress = findViewById(R.id.tvAddress)

        btnITR = findViewById(R.id.btnITR)


        binding.btnSearch.setOnClickListener {
            val namePat : String = binding.etResName.text.toString()
            if(namePat.isNotEmpty()){
                searchData(namePat)

            }else{
                goneData()

                binding.etResName.text.clear()
                binding.tvResId.setText("")
                binding.tvResName.setText("")
                binding.tvResAge.setText("")
                binding.tvResAddress.setText("")
                Toast.makeText(this, "Please enter the name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendData() {
        btnITR.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("patId", binding.tvResId.text.toString())
            bundle.putString("namePat", binding.tvResName.text.toString())
            bundle.putString("agePat", binding.tvResAge.text.toString())
            bundle.putString("addressPat", binding.tvResAddress.text.toString())

            val intent = Intent(this, InsertionActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun searchData(patId: String) {

        dbRef = FirebaseDatabase.getInstance().getReference("Patients")
        dbRef.child(patId).get().addOnSuccessListener {

            if(it.exists()){
                val resId = it.child("patId").value
                val resName = it.child("namePat").value
                val resAge = it.child("agePat").value
                val resAddress = it.child("addressPat").value

                Toast.makeText(this, "Successfully Search", Toast.LENGTH_SHORT).show()
                binding.etResName.text.clear()
                binding.tvResId.text = resId.toString()
                binding.tvResName.text = resName.toString()
                binding.tvResAge.text = resAge.toString()
                binding.tvResAddress.text = resAddress.toString()

                visibleData()

                binding.btnITR.setOnClickListener{
                    sendData()
                }

            }else{
                binding.tvResName.setText("")
                goneData()

                binding.etResName.text.clear()

                Toast.makeText(this, "Record Doesn't Exist", Toast.LENGTH_SHORT).show()

            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

        }

    }

    private fun visibleData() {
        //tvId.visibility = View.VISIBLE
        tvName.visibility = View.VISIBLE
        tvAge.visibility = View.VISIBLE
        tvAddress.visibility = View.VISIBLE
        btnITR.visibility = View.VISIBLE
    }

    private fun goneData() {
        //tvId.visibility = View.GONE
        tvName.visibility = View.GONE
        tvAge.visibility = View.GONE
        tvAddress.visibility = View.GONE
        btnITR.visibility = View.GONE
    }
}