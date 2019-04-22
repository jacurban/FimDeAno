package com.jac.fimdeano.util

import android.content.Context

        // sharedPreferences é usado para armazenar dados nao volateis e fazer acesso rapido a dados
        // nao é indicado para gerenciamento de dados  --> pra isso tem banco de dados

class Security (val context: Context){

    private val sharedpreferences = context.getSharedPreferences("FimdeAno", Context.MODE_PRIVATE)
        // o SharedPreferences é um arquivo criado dentro da aplicação. O nome pra pasta desse arquivo que eu
        // estou criando é "FimdeAno".
        // MODE_PRIVATE significa que não dá pra compartinhar esse SharedPreferences com outra aplicação


        // Método para salvar a resposta:
    fun storeString(key: String, value: String){
        sharedpreferences.edit().putString(key, value).apply()
    }

        // Método para buscar resposta:
    fun getString(key: String): String {
        return sharedpreferences.getString(key, "") ?: ""
    }
}