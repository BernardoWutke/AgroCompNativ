package com.example.agrocompnativ.models

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class DataSource {
    private val db = FirebaseFirestore.getInstance()




     suspend fun salvarSetores(setores: List<Setor>): Boolean{
        //uma variavel que retona o sucesso da operação, e não é inicializada
        var sucesso: Boolean = false
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            for (setor in setores){
                db.collection("users").document(user.uid).collection("setores").document(setor.index.toString()).set(setor)
                    .addOnCompleteListener{
                        sucesso = true
                    }.addOnFailureListener{
                        sucesso = true
                    }
            }
        }
        return sucesso
    }

    fun salvarIrrigacao(irrigacao: irrigacao){
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            db.collection("users").document(user.uid).collection("irrigacao").document("irrigacao").set(irrigacao)
                .addOnCompleteListener{

                }.addOnFailureListener{

                }
        }
    }


}