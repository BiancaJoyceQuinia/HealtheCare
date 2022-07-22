package com.example.healthecare.activities

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.healthecare.models.PatientsModel
import com.example.healthecare.R
//import com.example.healthecare.databinding.ActivityInsertionBinding
import com.google.firebase.database.*

class InsertionActivity : AppCompatActivity() {

    private lateinit var etId: TextView
    private lateinit var etName: TextView
    private lateinit var etAge: TextView
    private lateinit var etAddress: TextView
//    private lateinit var etId: EditText
//    private lateinit var etName: EditText
//    private lateinit var etAge: EditText
//    private lateinit var etAddress: EditText
    private lateinit var etSex: EditText
    private lateinit var etComplaint: EditText
    private lateinit var etDiagnosis: EditText
    private lateinit var etTreatment: EditText
    private lateinit var btnSave: Button

    private lateinit var dbRef: DatabaseReference

    private var isListItemClicked = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etId = findViewById(R.id.etId)
        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        etSex = findViewById(R.id.etSex)
        etAddress = findViewById(R.id.etAddress)
        etComplaint = findViewById(R.id.etComplaint)
        etDiagnosis = findViewById(R.id.etDiagnosis)
        etTreatment = findViewById(R.id.etTreatment)

        btnSave = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Patients")

        val bundle = intent.extras
        if(bundle != null){
            etId.text = "${bundle.getString("patId")}"
            etName.text = "${bundle.getString("namePat")}"
            etAge.text = "${bundle.getString("agePat")}"
            etAddress.text = "${bundle.getString("addressPat")}"
        }


        btnSave.setOnClickListener{
            if(isListItemClicked) {
                savePatientsData()

            }else {
                clearInput()
            }
        }
    }

    private fun clearInput(){
        etName.setText("")
        etAge.setText("")
        etSex.setText("")
        etAddress.setText("")
        etComplaint.setText("")
        etDiagnosis.setText("")
        etTreatment.setText("")

    }

    private fun savePatientsData() {

        val namePat = etName.text.toString()
        val agePat = etAge.text.toString()
        val sexPat = etSex.text.toString()
        val addressPat = etAddress.text.toString()
        val complaintPat = etComplaint.text.toString()
        val diagnosisPat = etDiagnosis.text.toString()
        val treatmentPat = etTreatment.text.toString()

//        val patId = etId.text.toString()
//        val patientsID = dbRef.push().key!!
        val patId = dbRef.push().key!!
        val patients = PatientsModel(patId, namePat, agePat, sexPat, addressPat, complaintPat, diagnosisPat, treatmentPat)
        //val patients = PatientsModel(patId, patientsID, namePat, agePat, sexPat, addressPat, complaintPat, diagnosisPat, treatmentPat)


        if(namePat.isEmpty()){
            etName.error = "Please enter name"
        }
        if(agePat.isEmpty()){
            etAge.error = "Please enter age"
        }
        if(sexPat.isEmpty()){
            etSex.error = "Please enter sex"
        }
        if(addressPat.isEmpty()){
            etAddress.error = "Please enter address"
        }
        if(complaintPat.isEmpty()){
            etComplaint.error = "Please enter complaint"
        }
        if(diagnosisPat.isEmpty()){
            etDiagnosis.error = "Please enter diagnosis"
        }
        if(treatmentPat.isEmpty()){
            etTreatment.error = "Please enter treatment"
        }

        else {
            dbRef.child(namePat).setValue(patients)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show()
                    clearInput()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}