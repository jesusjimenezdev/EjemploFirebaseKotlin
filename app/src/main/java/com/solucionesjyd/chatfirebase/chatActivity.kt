package com.solucionesjyd.chatfirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chat.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class Chat(val usuario: String, val mensaje: String, val fecha: String, val avatar: String)

class chatActivity : AppCompatActivity() {

    private var autenticacion: FirebaseAuth? = null
    private var database = FirebaseDatabase.getInstance()
    private var conexion = database.reference
    var lista = ArrayList<userDatos>()
    var nom = ""
    var avatar = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        autenticacion = FirebaseAuth.getInstance()
        var adapter = userListAdapter(this, lista)
        list.adapter = adapter
        val b: Bundle = intent.extras
        val idUser = b.getString("id")

        conexion.child("users").child(idUser)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(p0: DataSnapshot?) {
                        nom = p0!!.child("user").getValue().toString()
                        avatar = p0!!.child("avatar").getValue().toString()
                    }

                })


        enviar.setOnClickListener {
            val message = mensaje.text.toString()
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val date = Date()
            val fecha = dateFormat.format(date)
            val id = conexion.push().key
            conexion.child("MENSAJES").child(id).setValue(Chat(nom, message, fecha, avatar))
            mensaje.setText("")
        }

        conexion.child("MENSAJES").addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                val mensaje = p0!!.child("mensaje").value.toString()
                val nombre = p0!!.child("usuario").value.toString()
                val fecha = p0!!.child("fecha").value.toString()
                val imagen = p0!!.child("avatar").value.toString()
                lista.add(userDatos(nombre, mensaje, fecha, imagen))
                adapter.notifyDataSetChanged()
            }

            override fun onChildRemoved(p0: DataSnapshot?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.salir -> {
                finish()
                return true
            }
            R.id.acerca -> {
                Toast.makeText(this, "Acerca de", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }


    }
}
