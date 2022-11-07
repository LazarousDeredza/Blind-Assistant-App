/*
 * Copyright 2022 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tensorflow.lite.examples.objectdetection


//here
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.tensorflow.lite.examples.objectdetection.databinding.ActivityMainBinding
import org.tensorflow.lite.task.vision.detector.Detection
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

//here

/**
 * Main entry point into our app. This app follows the single-activity pattern, and all
 * functionality is implemented in the form of fragments.
 */
const val TTS_ENGINE_RESULT_CODE = 26

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {



    //
    private var textToSpeech: TextToSpeech? = null
    // private var textToSpeech1: TextToSpeech? = null

    private lateinit var activityMainBinding: ActivityMainBinding


    companion object{
        var item :String="empty"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        //--
        val checkTTSEngineIntent = Intent() // Create an Intent
        checkTTSEngineIntent.action = TextToSpeech.Engine.ACTION_CHECK_TTS_DATA // Set the intent action to check the TTS engine data.
        startActivityForResult(checkTTSEngineIntent, TTS_ENGINE_RESULT_CODE) // Start the Intent expecting a result, sending a custom status code.
        //--


        //fragment_container.setOnClickListener(this)

    }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TTS_ENGINE_RESULT_CODE) { // Check if the result code is the same as the one sent by the TTS check intent.
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) { // If the device has all the necessary TTS data.
                textToSpeech = TextToSpeech(this, TextToSpeech.OnInitListener {
                    // Initialize the TTS engine.
                    textToSpeech?.language = Locale.getDefault() // Set the language to the device's locale.

                    textToSpeech?.speak("Welcome To blind assist AI , Tap on the middle of the screen to know whats infront of you and tap at the bottom of your screen to request for help",TextToSpeech.QUEUE_FLUSH,null,null)
                })
                btnEmail.setOnClickListener {
                    /**
                     * The speak() method signature changed in API 21.
                     */
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        textToSpeech?.speak("Sending Your Email , Please wait"
                            ?: "Sending Your Email , Please wait", TextToSpeech.QUEUE_FLUSH, null, null) // Make the device read the text in the EditText.


                    } else {
                        textToSpeech?.speak(("Sending Your Email , Please wait"
                            ?: "Sending Your Email , Please wait") as String, TextToSpeech.QUEUE_FLUSH, null) // Make the device read the text in the EditText.
                    }

                    var lat: Double=-17.8285454
                    var lon: Double=31.0379037
                    var lon1: Double = 0.0
                    var lat1: Double = 0.0


                    var gpsTracker = GpsTracker(this)
                    if (gpsTracker.canGetLocation()) {
                        val latitude: Double = gpsTracker.getLatitude()
                        val longitude: Double = gpsTracker.getLongitude()
                        lat1 = latitude
                        lon1 = longitude
                    } else {
                        gpsTracker.showSettingsAlert()
                        val latitude: Double = gpsTracker.getLatitude()
                        val longitude: Double = gpsTracker.getLongitude()
                        lat1 = latitude
                        lon1 = longitude
                    }


                    lon = lon1
                    lat = lat1

                    Thread {
                        val username = "ninja.ld49@gmail.com"
                        val password = "uxbhldqealdgttae"
                        val messageToSend =
                            "Hie , im lost .  Can you please come and get me at \nhttp://www.google.com/maps/place/$lat,$lon/@$lat,$lon,17z/data=!3m1!1e3"
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
                                InternetAddress.parse("kudzaifb365@gmail.com")
                            )
                            message.subject = "Emergency Pickup"
                            message.setText(messageToSend)
                            Transport.send(message)
                            Log.e("Mail Status", "Send")

                            //TODO
                            //Add Sound After email send
                            //Mail sent sucessfully

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                textToSpeech?.speak(" Email , Sent sucessfully"
                                    ?: "Email , Sent sucessfully", TextToSpeech.QUEUE_ADD, null, null) // Make the device read the text in the EditText.


                            } else {
                                textToSpeech?.speak(("Email , Sent sucessfully"
                                    ?: "Email , Sent sucessfully") as String, TextToSpeech.QUEUE_ADD, null) // Make the device read the text in the EditText.
                            }




                        } catch (e: MessagingException) {


                            //Mail sent Failed

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                textToSpeech?.speak(" There Was an error in sending your email, try again later"
                                    ?: " There Was an error in sending your email, try again later", TextToSpeech.QUEUE_ADD, null, null) // Make the device read the text in the EditText.


                            } else {
                                textToSpeech?.speak((" There Was an error in sending your email, try again later"
                                    ?: " There Was an error in sending your email, try again later") as String, TextToSpeech.QUEUE_ADD, null) // Make the device read the text in the EditText.
                            }



                            // throw new RuntimeException(e);
                            // Log.e("Mail Status","Mail Not Send");
                        }
                    }.start()


                    //-----------------------------------------------

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
                                    this@MainActivity,
                                    "Mail Send failed..Check your network and retry",
                                    Toast.LENGTH_LONG
                                ).show()
                                // throw new RuntimeException(e);
                                // Log.e("Mail Status","Mail Not Send");
                            }
                        }.start()
                    }


                    //--------------------------------------------

                }

                fragment_container.setOnClickListener {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        textToSpeech?.speak(item?:item, TextToSpeech.QUEUE_ADD, null, null) // Make the device read the text in the EditText.


                    } else {
                        textToSpeech?.speak(item?:item, TextToSpeech.QUEUE_ADD, null) // Make the device read the text in the EditText.
                    }

                }




            } else { // If the data is not available.
                val installTTSDataIntent = Intent() // Create a new intent.
                installTTSDataIntent.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA // Define the intent action as one for installing this missing data, the TTS API provides this.
                startActivity(installTTSDataIntent) // Start the intent for downloading the missing data.
            }
        }
    }





    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
            // Workaround for Android Q memory leak issue in IRequestFinishCallback$Stub.
            // (https://issuetracker.google.com/issues/139738913)
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }

   /* override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val result=textToSpeech!!.setLanguage(Locale.US)
            if (result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("Error","Failed to initialize Text to spaeech module")
            }
        }

        textToSpeech?.speak("Welcome To blind assist AI , Tap on the middle of the screen to know whats infront of you and tap at the bottom of your screen to request for help",TextToSpeech.QUEUE_FLUSH,null,null)
    }*/

 /*   override fun onClick(p0: View?) {
       when (p0?.id){
           R.id.fragment_container ->{
               textToSpeech = TextToSpeech(this, TextToSpeech.OnInitListener {
                   // Initialize the TTS engine.
                   textToSpeech?.language = Locale.getDefault() // Set the language to the device's locale.
               })

               textToSpeech!!.speak(item,TextToSpeech.QUEUE_FLUSH,null,null)

           }
       }
    }*/


    fun speakOut(results:List<Detection>){
        var i =""
        for (result in results){
            i+=result.categories[0].label.toString() +" and "

        }

        var fin =i.substring(0,i.length-4)

        MainActivity.Companion.item=fin
      /*  if(fin.length>1) {
           try {



            val thread = Thread {
                var textToSpeech1: TextToSpeech? = null
                textToSpeech1 = TextToSpeech(this, TextToSpeech.OnInitListener {
                    // Initialize the TTS engine.
                    textToSpeech1?.language =
                        Locale.getDefault() // Set the language to the device's locale.

                    // textToSpeech?.speak("Welcome To blind assist AI , Tap on the middle of the screen to know whats infront of you and tap at the bottom of your screen to request for help",TextToSpeech.QUEUE_FLUSH,null,null)
                })

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeech1?.speak(
                        item ?: item,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    ) // Make the device read the text in the EditText.
                    Log.e("reading", "done")
                    Log.e("data", item)
                } else {
                    textToSpeech1?.speak(
                        item ?: item,
                        TextToSpeech.QUEUE_ADD,
                        null
                    ) // Make the device read the text in the EditText.

                    Log.e("reading", "done")
                    Log.e("data", item)
                }
            }
            thread.start()


           }catch (e: Exception) {
               e.printStackTrace()
               Log.d("erroring",e.toString())
           }

        }*/
       // fragment_container.performClick()

    }



}

