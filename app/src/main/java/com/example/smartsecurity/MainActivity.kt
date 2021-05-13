package com.example.smartsecurity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.circularreveal.coordinatorlayout.CircularRevealCoordinatorLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnLogout = findViewById<Button>(R.id.btn_logout)



        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@MainActivity, Login::class.java))
            finish()
        }


        val college_gate = findViewById<ImageView>(R.id.img_gate)
        val college_hostel = findViewById<ImageView>(R.id.img_hostel)
        val college_computerlab = findViewById<ImageView>(R.id.img_ComputerLab)
        val college_library = findViewById<ImageView>(R.id.img_library)
        val college_class = findViewById<ImageView>(R.id.img_class)


        college_gate.setOnClickListener {
            Toast.makeText(this, "Clicked on College Gate", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, Qrscanner::class.java)
            intent.putExtra("title", "College gate")
            startActivity(intent)
        }

        college_hostel.setOnClickListener {
            Toast.makeText(this, "Clicked on College Hostel", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, Qrscanner::class.java)
            intent.putExtra("title", "College Hostel")

            startActivity(intent)
        }
        college_computerlab.setOnClickListener {
            Toast.makeText(this, "Clicked on College Computer Lab", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, Qrscanner::class.java)
            intent.putExtra("title", "Computer Lab")

            startActivity(intent)
        }
        college_library.setOnClickListener {
            Toast.makeText(this, "Clicked on College Library", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, Qrscanner::class.java)
            intent.putExtra("title", "College Library")

            startActivity(intent)
        }
        college_class.setOnClickListener {
            Toast.makeText(this, "Clicked on College Class", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, Qrscanner::class.java)
            intent.putExtra("title", "College Class")

            startActivity(intent)
        }




    }
}
