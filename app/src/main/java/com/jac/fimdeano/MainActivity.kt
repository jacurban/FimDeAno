package com.jac.fimdeano

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jac.fimdeano.constants.FimdeAnoConstants
import com.jac.fimdeano.util.Security
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

   // private val aux: String by lazy {"kk"}

    private lateinit var security: Security
    @SuppressLint("SimpleDateFormat")
    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.mipmap.ic_launcher_foreground)

            // Setar o confirmation button
        confirmationBTN.setOnClickListener {
            val presence = security.getString(FimdeAnoConstants.PRESENCE) // cria uma constante que pega o valor salvo no security
            val intent = Intent(this, DetailsActivity::class.java) // intençao de abrir a DetailsActivity
            intent.putExtra(FimdeAnoConstants.PRESENCE, presence) // colocar na intent o valor 'presence' que a gente buscou na security
            startActivity(intent) //startar a activity intencionada acima
        }

        security = Security(this)

            // Setar a data de hoje no todayTXT
        todayTXT.text = simpleDateFormat.format(Calendar.getInstance().time)

        diasTXT.text = "${daysLeft()} dias"
    }


    override fun onResume() {
        super.onResume()
        verifyPresence()
    }

        // Função pra calcular quantos dias faltam pro fim do ano
        private fun daysLeft (): String{
            val today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)  // pega o dia de hoje
            val december31 = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR)  // pega o ultimo dia do ano
            return (december31 - today).toString()  // faz a diferença
    }

        // Setar o texto do botão de acordo com o clique do checkbox da outra activity
    private fun verifyPresence(){
        val presence = security.getString(FimdeAnoConstants.PRESENCE)
        when {
            presence.isEmpty() -> confirmationBTN.setText(R.string.nao_confirmado)
            presence == FimdeAnoConstants.CONFIRM_WILL_GO -> confirmationBTN.setText(R.string.sim)
            else -> confirmationBTN.setText(R.string.nao)
        }
    }
}
