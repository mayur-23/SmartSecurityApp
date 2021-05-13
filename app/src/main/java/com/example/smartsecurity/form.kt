package com.example.smartsecurity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class form : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val namef=findViewById<TextView>(R.id.fullName_form)
        val mobilef=findViewById<EditText>(R.id.editTextPhone_form)
        val btnf=findViewById<Button>(R.id.btnf)
        val emailf=findViewById<TextView>(R.id.emailIdRig_form)
        val reasonf=findViewById<TextView>(R.id.reason_form)
        val vehicalno=findViewById<TextView>(R.id.Vehicalno_Reg_form)

        val refd= FirebaseDatabase.getInstance().getReference("visitors")

        btnf.setOnClickListener {
            when{
                //if email id block is empty then show please enter mail
                TextUtils.isEmpty(emailf.text.toString().trim { it <= ' ' }) -> run {
                    Toast.makeText(
                        this@form,
                        "please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                //if password block is empty then show please enter password
                TextUtils.isEmpty(mobilef.text.toString().trim { it <= ' ' }) -> run {
                    Toast.makeText(
                        this@form,
                        "please enter mobile no",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else->{
                    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                    val date = Date()
                    val a = formatter.format(date)

                    val userId=refd.push().key

                    //u us holds all data of regestration
                    val u=FormD(
                        userId.toString(),
                        namef.text.toString(),
                        emailf.text.toString(),
                        vehicalno.text.toString(),
                        mobilef.text.toString().toInt(),
                        reasonf.text.toString(),
                        a)

                    //to create a new child node in user section
                    if (userId != null) {
                        refd.child(userId).setValue(u).addOnCompleteListener { Toast.makeText(
                            this@form,
                            "success",
                            Toast.LENGTH_SHORT
                        ).show() }
                    }
                }

            }
        }


    }
}