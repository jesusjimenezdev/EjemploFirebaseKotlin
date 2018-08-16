package com.solucionesjyd.chatfirebase

class userDatos {
    var name: String = ""
    var mensaje: String = ""
    var fecha: String = ""
    var avatar: String = ""

    constructor(name: String, mensaje: String, fecha: String, avatar: String) {
        this.name = name
        this.mensaje = mensaje
        this.fecha = fecha
        this.avatar = avatar
    }
}