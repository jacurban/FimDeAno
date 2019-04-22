package com.jac.fimdeano

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jac.fimdeano.constants.FimdeAnoConstants
import com.jac.fimdeano.util.Security
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var security: Security

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.mipmap.ic_launcher_foreground)

        security = Security(this)

            // Armazena a informação no Security de acordo com o click do checkbox
        participateCHK.setOnClickListener{
            if (participateCHK.isChecked){
                security.storeString(FimdeAnoConstants.PRESENCE, FimdeAnoConstants.CONFIRM_WILL_GO)
            }else{
                security.storeString(FimdeAnoConstants.PRESENCE, FimdeAnoConstants.CONFIRM_WONT_GO)
            }
        }

        loadDataFromActivity()
    }

    private fun loadDataFromActivity(){
        val extras = intent.extras
        if (extras != null){
            val presence = security.getString(FimdeAnoConstants.PRESENCE)
            participateCHK.isChecked = (presence == FimdeAnoConstants.CONFIRM_WILL_GO)
        }
    }

   // companion object {}
}

