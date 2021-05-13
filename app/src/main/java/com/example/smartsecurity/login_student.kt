package com.example.smartsecurity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class login_student : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_student)

       // val tv_create_s=findViewById<TextView>(R.id.textView_Create)
        val btnL_s=findViewById<Button>(R.id.login_btn_student)
        val emailR_s=findViewById<TextView>(R.id.emailId_student)
        val passwordR_s=findViewById<TextView>(R.id.password_student)

        btnL_s.setOnClickListener {
            when{
                TextUtils.isEmpty(emailR_s.text.toString().trim{it <= ' '}) -> run {
                    Toast.makeText(
                        this@login_student,
                        "please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(passwordR_s.text.toString().trim{it <= ' '}) -> run {
                    Toast.makeText(
                        this@login_student,
                        "please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else->{
                    val emails:String=emailR_s.text.toString().trim { it <= ' ' }
                    val passwords:String=passwordR_s.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emails,passwords).addOnCompleteListener {
                        task ->
                        if (task.isSuccessful){
                            val firebaseuser: FirebaseUser =task.result!!.user!!

                            Toast.makeText(
                                this@login_student,
                                "You are login successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent= Intent(this@login_student,student_qr_generator::class.java)
                            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id",firebaseuser.uid)
                            intent.putExtra("email_id",emails)
                            startActivity(intent)
                            finish()

                        }else{
                            Toast.makeText(this@login_student,task.exception!!.message!!.toString(),
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }


    }
}