package org.tensorflow.lite.examples.objectdetection

import androidx.appcompat.app.AppCompatActivity
import android.app.ProgressDialog
import android.util.Log
import org.tensorflow.lite.examples.objectdetection.GpsTracker
import org.tensorflow.lite.examples.objectdetection.speek
import javax.mail.internet.MimeMessage
import javax.mail.internet.InternetAddress
import android.widget.Toast
import java.util.*
import javax.mail.*

class sendMail : AppCompatActivity() {


    fun sendEmailFunction(lat1: Double, lon1: Double) {
        Thread {
            val username = "ninja.ld49@gmail.com"
            val password = "uxbhldqealdgttae"
            val messageToSend =
                "Hie , im lost .  Can you please come and get me at \nhttp://www.google.com/maps/place/$lat1,$lon1/@$lat1,$lon1,17z/data=!3m1!1e3"
            val props = Properties()
            props["mail.smtp.auth"] = "true"
            props["mail.smtp.socketFactory.port"] = "465"
            props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
            props["mail.smtp.starttls.enable"] = "true"
            props["mail.smtp.host"] = "smtp.gmail.com"
            props["mail.smtp.port"] = "465"
            val session = Session.getInstance(props,
                object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(username, password)
                    }
                })
            try {
                val message: Message = MimeMessage(session)
                message.setFrom(InternetAddress(username))
                message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("ninja.ld50@gmail.com")
                )
                message.subject = "Emergency Pickup"
                message.setText(messageToSend)
                Transport.send(message)
                Log.e("Mail Status", "Send")


                //TODO
                //Add Sound After email send
            } catch (e: MessagingException) {
                Toast.makeText(
                    this@sendMail,
                    "Mail Send failed..Check your network and retry",
                    Toast.LENGTH_LONG
                ).show()
                // throw new RuntimeException(e);
                // Log.e("Mail Status","Mail Not Send");
            }
        }.start()
    }
}