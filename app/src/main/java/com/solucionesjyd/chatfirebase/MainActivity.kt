package com.solucionesjyd.chatfirebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var autenticacion: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autenticacion = FirebaseAuth.getInstance()

        registrar.setOnClickListener {
            val intent = Intent(this, registrarActivity::class.java)
            startActivity(intent)
        }

        entrar.setOnClickListener {

            val email = email.text.toString()
            val password = password.text.toString()
            login(email, password)

        }

    }

    private fun login(email: String, password: String) {
        autenticacion.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val idUser = FirebaseAuth.getInstance().currentUser!!.uid
                val intent = Intent(this, chatActivity::class.java)
                intent.putExtra("id", idUser)
                startActivity(intent)
            } else {
                Toast.makeText(this, "fallo el logeo", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
