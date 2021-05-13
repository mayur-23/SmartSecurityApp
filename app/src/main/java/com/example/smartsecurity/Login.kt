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

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tv_create=findViewById<TextView>(R.id.textView_Create)
        val btnL=findViewById<Button>(R.id.button_LoginMain)


        val emailR=findViewById<TextView>(R.id.emailIdRig)
        val passwordR=findViewById<TextView>(R.id.passwordReg)


        tv_create.setOnClickListener{
            startActivity(Intent(this@Login,Register::class.java))
        }
        btnL.setOnClickListener {
            when{
                TextUtils.isEmpty(emailR.text.toString().trim{it <= ' '}) -> run {
                    Toast.makeText(
                        this@Login,
                        "please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(passwordR.text.toString().trim{it <= ' '}) -> run {
                    Toast.makeText(
                        this@Login,
                        "please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else->{
                    val email:String=emailR.text.toString().trim { it <= ' ' }
                    val password:String=passwordR.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task->
                            if (task.isSuccessful){
                                val firebaseuser: FirebaseUser =task.result!!.user!!

                                Toast.makeText(
                                    this@Login,
                                    "You are login successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent=Intent(this@Login,MainActivity::class.java)
                                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id",firebaseuser.uid)
                                intent.putExtra("email_id",email)
                                startActivity(intent)
                                finish()
                            }else{
                                Toast.makeText(this@Login,task.exception!!.message!!.toString(),
                                    Toast.LENGTH_SHORT).show()
                            }

                        }
                }
            }
        }
    }
}