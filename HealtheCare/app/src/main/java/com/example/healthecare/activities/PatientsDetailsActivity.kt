package com.example.healthecare.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.healthecare.R
import com.example.healthecare.models.PatientsModel
import com.google.firebase.database.FirebaseDatabase

class PatientsDetailsActivity : AppCompatActivity() {
    
    private lateinit var tvPatId: TextView
    private lateinit var tvPatName: TextView
    private lateinit var tvPatAge: TextView
    private lateinit var tvPatSex: TextView
    private lateinit var tvPatAddress: TextView
    private lateinit var tvPatComplaint: TextView
    private lateinit var tvPatDiagnosis: TextView
    private lateinit var tvPatTreatment: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("patId").toString(),
//                intent.getStringExtra("patientsID").toString(),
                intent.getStringExtra("namePat").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("namePat").toString()
            )
        }

    }

    private fun deleteRecord(
        namePat: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Patients").child(namePat)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Patient data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {

        tvPatId = findViewById(R.id.tvPatId)
        tvPatName = findViewById(R.id.tvPatName)
        tvPatAge = findViewById(R.id.tvPatAge)
        tvPatSex = findViewById(R.id.tvPatSex)
        tvPatAddress = findViewById(R.id.tvPatAddress)
        tvPatComplaint = findViewById(R.id.tvPatComplaint)
        tvPatDiagnosis = findViewById(R.id.tvPatDiagnosis)
        tvPatTreatment = findViewById(R.id.tvPatTreatment)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

    }

    private fun setValuesToViews() {

        tvPatId.text  = intent.getStringExtra("patId")
        tvPatName.text = intent.getStringExtra("namePat")
        tvPatAge.text = intent.getStringExtra("agePat")
        tvPatSex.text = intent.getStringExtra("sexPat")
        tvPatAddress.text = intent.getStringExtra("addressPat")
        tvPatComplaint.text = intent.getStringExtra("complaintPat")
        tvPatDiagnosis.text = intent.getStringExtra("diagnosisPat")
        tvPatTreatment.text = intent.getStringExtra("treatmentPat")
    }

    private fun openUpdateDialog(
        patId: String,
   //     patientsID: String,
        namePat: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val etPatName = mDialogView.findViewById<EditText>(R.id.etPatName)
        val etPatAge = mDialogView.findViewById<EditText>(R.id.etPatAge)
        val etPatSex = mDialogView.findViewById<EditText>(R.id.etPatSex)
        val etPatAddress = mDialogView.findViewById<EditText>(R.id.etPatAddress)
        val etPatComplaint = mDialogView.findViewById<EditText>(R.id.etPatComplaint)
        val etPatDiagnosis = mDialogView.findViewById<EditText>(R.id.etPatDiagnosis)
        val etPatTreatment = mDialogView.findViewById<EditText>(R.id.etPatTreatment)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)


        etPatName.setText(intent.getStringExtra("namePat").toString())
        etPatAge.setText(intent.getStringExtra("agePat").toString())
        etPatSex.setText(intent.getStringExtra("sexPat").toString())
        etPatAddress.setText(intent.getStringExtra("addressPat").toString())
        etPatComplaint.setText(intent.getStringExtra("complaintPat").toString())
        etPatDiagnosis.setText(intent.getStringExtra("diagnosisPat").toString())
        etPatTreatment.setText(intent.getStringExtra("treatmentPat").toString())

        mDialog.setTitle("Updating $namePat's Health Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updatePatientData(
                patId,
//                patientsID,
//                namePat,
                etPatName.text.toString(),
                etPatAge.text.toString(),
                etPatSex.text.toString(),
                etPatAddress.text.toString(),
                etPatComplaint.text.toString(),
                etPatDiagnosis.text.toString(),
                etPatTreatment.text.toString()
            )

            Toast.makeText(applicationContext, "Patient Data Updated", Toast.LENGTH_SHORT).show()

            // setting updated data to the textView
            tvPatName.text = etPatName.text.toString()
            tvPatAge.text = etPatAge.text.toString()
            tvPatSex.text = etPatSex.text.toString()
            tvPatAddress.text = etPatAddress.text.toString()
            tvPatComplaint.text = etPatComplaint.text.toString()
            tvPatDiagnosis.text = etPatDiagnosis.text.toString()
            tvPatTreatment.text = etPatTreatment.text.toString()

            alertDialog.dismiss()
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updatePatientData(
        patId: String,
      //  patientsID: String,
        patName: String,
        patAge: String,
        patSex: String,
        patAddress: String,
        patComplaint: String,
        patDiagnosis: String,
        patTreatment: String,
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Patients").child(patName)
        val patInfo = PatientsModel(patId, patName, patAge, patSex, patAddress, patComplaint, patDiagnosis, patTreatment)
       // val patInfo = PatientsModel(patId, patientsID, patName, patAge, patSex, patAddress, patComplaint, patDiagnosis, patTreatment)
        dbRef.setValue(patInfo)
    }

}