package com.example.smartsecurity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*


class Register : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //to give reference to all views
        val mobileR=findViewById<EditText>(R.id.editTextPhone)
        val btnR=findViewById<Button>(R.id.btnR)
        val nameR=findViewById<EditText>(R.id.fullName)
        val emailR=findViewById<TextView>(R.id.emailIdRig)
        val passwordR=findViewById<EditText>(R.id.passwordReg)
        val tv_login=findViewById<TextView>(R.id.textView_login)

       //refd refers to firebase database and gives reference of node user
        val refd=FirebaseDatabase.getInstance().getReference("user")


        //when clicked on already have a account then fo back to login
        tv_login.setOnClickListener{
            onBackPressed()
        }

        //to set on click listner to the button
        btnR.setOnClickListener {
            when{

                //if email id block is empty then show please enter mail
                TextUtils.isEmpty(emailR.text.toString().trim { it <= ' ' }) -> run {
                    Toast.makeText(
                        this@Register,
                        "please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                //if password block is empty then show please enter password
                TextUtils.isEmpty(passwordR.text.toString().trim { it <= ' ' }) -> run {
                    Toast.makeText(
                        this@Register,
                        "please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                //if both blocks are filled
                else->{

                    //store the values in two variables email and password
                    val email:String=emailR.text.toString().trim { it <= ' ' }
                    val password:String=passwordR.text.toString().trim { it <= ' ' }

                    //create user in firebase with this username and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task->
                            if (task.isSuccessful){
                                val firebaseuser:FirebaseUser=task.result!!.user!!

                                Toast.makeText(
                                    this@Register,
                                    "You are registered successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                //then go to the main activity page
                                val intent=Intent(this@Register, MainActivity::class.java)
                                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseuser.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            }else{
                                //else show what exception , error occured
                                Toast.makeText(
                                    this@Register,
                                    task.exception!!.message!!.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }
                    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                    val date = Date()
                    val a = formatter.format(date)

                    //userID has a unique id which is a primary key to database
                    val userId=refd.push().key

                    //u us holds all data of regestration
                    val u=User(
                        userId.toString(),
                        nameR.text.toString(),
                        emailR.text.toString(),
                        passwordR.text.toString(),
                        mobileR.text.toString().toInt(),
                        a)

                    //to create a new child node in user section
                    if (userId != null) {
                        refd.child(userId).setValue(u).addOnCompleteListener { Toast.makeText(
                            this@Register,
                            "success",
                            Toast.LENGTH_SHORT
                        ).show() }
                    }


                }
            }
        }


    }
}