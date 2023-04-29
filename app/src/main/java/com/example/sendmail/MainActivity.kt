package com.example.sendmail

import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sendmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amb.cleanBtn.setOnClickListener {
            with(amb){
                toTxt.setText("")
                ccTxt.setText("")
                bccTxt.setText("")
                subjectTxt.setText("")
                messageTxt.setText("")
            }
        }

        amb.sendBtn.setOnClickListener {
            val sendMailIntent = Intent(ACTION_SENDTO)

            with (sendMailIntent) {
                putExtra(EXTRA_EMAIL, arrayOf(amb.toTxt.text.toString()))
                putExtra(EXTRA_CC, arrayOf(amb.ccTxt.text.toString()))
                putExtra(EXTRA_BCC, arrayOf(amb.bccTxt.text.toString()))
                putExtra(EXTRA_SUBJECT, arrayOf(amb.subjectTxt.text.toString()))
                putExtra(EXTRA_TEXT, arrayOf(amb.messageTxt.text.toString()))
                type = "message/rfc822"
                data = Uri.parse("mailto:")
            }
            val chooserIntent = Intent(ACTION_SENDTO)
            chooserIntent.putExtra(EXTRA_INTENT, sendMailIntent)
            startActivity(chooserIntent)
        }
    }
}