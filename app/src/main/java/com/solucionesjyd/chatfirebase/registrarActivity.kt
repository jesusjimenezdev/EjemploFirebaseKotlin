package com.solucionesjyd.chatfirebase

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registrar.*

data class Usuarios(val user: String, val email: String, val id: String, val avatar: String)

class registrarActivity : AppCompatActivity() {

    private var autentication: FirebaseAuth? = null
    private var database = FirebaseDatabase.getInstance()
    private var conexion = database.reference
    var avatar = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        autentication = FirebaseAuth.getInstance()
        registrarse.setOnClickListener {
            val email = regEmail.text.toString()
            val password = regPass.text.toString()
            val user = regUsuario.text.toString()
            login(email, password, user)
        }

        gatito.setBackgroundColor(Color.GRAY)
        persona.setBackgroundColor(Color.GRAY)
        perrito.setBackgroundColor(Color.GRAY)
        osito.setBackgroundColor(Color.GRAY)

        gatito.setOnClickListener {
            avatar = "gatito"
            gatito.setBackgroundColor(Color.GREEN)
            persona.setBackgroundColor(Color.GRAY)
            perrito.setBackgroundColor(Color.GRAY)
            osito.setBackgroundColor(Color.GRAY)
        }

        persona.setOnClickListener {
            avatar = "person"
            gatito.setBackgroundColor(Color.GRAY)
            persona.setBackgroundColor(Color.GREEN)
            perrito.setBackgroundColor(Color.GRAY)
            osito.setBackgroundColor(Color.GRAY)
        }

        perrito.setOnClickListener {
            avatar = "perrito"
            gatito.setBackgroundColor(Color.GRAY)
            persona.setBackgroundColor(Color.GRAY)
            perrito.setBackgroundColor(Color.GREEN)
            osito.setBackgroundColor(Color.GRAY)
        }

        osito.setOnClickListener {
            avatar = "osito"
            gatito.setBackgroundColor(Color.GRAY)
            persona.setBackgroundColor(Color.GRAY)
            perrito.setBackgroundColor(Color.GRAY)
            osito.setBackgroundColor(Color.GREEN)
        }

    }//fin onCreate

    fun login(email: String, password: String, user: String) {
        autentication!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val Uid = FirebaseAuth.getInstance().currentUser!!.uid

                        Toast.makeText(applicationContext, "Logeado", Toast.LENGTH_SHORT).show()
                        conexion.child("users").child(Uid).setValue(Usuarios(user, email, Uid, avatar))
                        val intent = Intent(this, chatActivity::class.java)
                        intent.putExtra("id", Uid)
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(applicationContext, "El logeo fallo", Toast.LENGTH_SHORT).show()
                    }

                }
    }
}
