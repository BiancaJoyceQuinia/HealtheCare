package com.example.healthecare.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.healthecare.R
import com.example.healthecare.models.PERModel
import com.google.firebase.database.DatabaseReference

class PatientEnrollmentRecord : AppCompatActivity() {

    private lateinit var perLName: EditText
    private lateinit var perMName: EditText
    private lateinit var perFName: EditText
    private lateinit var perSuffix: EditText
    private lateinit var perAge: EditText
    private lateinit var perSex: EditText
    private lateinit var perBirthdate: EditText
    private lateinit var perBirthplace: EditText
//    private lateinit var perCivilStatus: Spinner
    private lateinit var btnSave: Button

    private lateinit var dbRef: DatabaseReference
    private var isListItemClicked = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.per_form)

        val perCivilStatus: Spinner = findViewById(R.id.perCivilStatus)
        perLName = findViewById(R.id.perLName)
        perMName = findViewById(R.id.perMName)
        perFName = findViewById(R.id.perFName)
        perSuffix = findViewById(R.id.perSuffix)
        perAge = findViewById(R.id.perAge)
        perSex = findViewById(R.id.perSex)
        perBirthdate = findViewById(R.id.perBirthdate)
        perBirthplace = findViewById(R.id.perBirthplace)

        btnSave = findViewById(R.id.btnSave)


//        val customList = listOf("First", "Second", "Third", "Fourth")
//        val adapter = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, customList)
//        perCivilStatus.adapter = adapter

        perCivilStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@PatientEnrollmentRecord,
                    "You selected ${adapterView?.getItemAtPosition(position).toString()}",
                    Toast.LENGTH_LONG).show()

                perCivilStatus.onItemSelectedListener = this
            }
        }

        btnSave.setOnClickListener{
            if(isListItemClicked) {
                savePatientsData()

            }

    }
}

    private fun savePatientsData() {

        val PERLName = perLName.text.toString()
        val PERMName = perMName.text.toString()
        val PERFName = perFName.text.toString()
        val PERSuffix = perSuffix.text.toString()
        val PERAge = perAge.text.toString()
        val PERSex = perSex.text.toString()
        val PERBirthdate = perBirthdate.text.toString()
        val PERBirthplace = perBirthplace.text.toString()
        val PERCivilStatus = 

    }
}
