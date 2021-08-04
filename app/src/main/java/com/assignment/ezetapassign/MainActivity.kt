package com.assignment.ezetapassign

import DataFromServer
import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.assignment.network.FetchDataHelperClass
import com.assignment.network.ResponceCallback
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val b = FetchDataHelperClass()
        val linearLayout = findViewById<LinearLayout>(R.id.layout)
        val imageView = findViewById<ImageView>(R.id.image)
        val heading = findViewById<TextView>(R.id.heading)



         b.fetchImageData("android_assignment.json",object : ResponceCallback {
            override fun onSuccess(value: Result<JsonObject>) {
            }

            override fun onImageSuccess(value: Result<String>) {
                Picasso.get().load(value.getOrNull()).into(imageView)
            }

            override fun onFailure() {

            }

        })

        b.fetchCustomUI("android_assignment.json",object : ResponceCallback {
            override fun onSuccess(value: Result<JsonObject>) {
                val ui = Gson().fromJson(value.getOrNull(), DataFromServer::class.java)

                heading.text = ui.headingtext

                for (i:Int in 0..ui.uidata.size-1){

                    if (ui.uidata.get(i).uitype.equals("label")) {
                        val txtView = TextView(this@MainActivity)
                        txtView.text = ui.uidata.get(i).value
                        txtView.setLayoutParams(
                            LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                        )
                        txtView.id = i
                        linearLayout.addView(txtView)
                    } else if (ui.uidata.get(i).uitype.equals("edittext")) {
                        val edittext = EditText(this@MainActivity)
                        val hint = ui.uidata.get(i).hint
                        edittext.setHint(hint)
                        edittext.setLayoutParams(
                            LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                        )
                        edittext.id = i
                        linearLayout.addView(edittext)
                    } else if (ui.uidata.get(i).uitype.equals("button")) {
                        val button = Button(this@MainActivity)
                        button.setLayoutParams(
                            LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                        )
                        button.text = ui.uidata[i].value
                        button.id = i
                        button.setOnClickListener {
                            var name : String? = null
                            var number : String? = null
                            var city : String? = null
                            val arrayIds = arrayOf(1,3,5)

                            name = linearLayout.getChildAt(1).findViewById<EditText>(arrayIds[0]).text.trim().toString()
                            number = linearLayout.getChildAt(3).findViewById<EditText>(arrayIds[1]).text.trim().toString()
                            city = linearLayout.getChildAt(5).findViewById<EditText>(arrayIds[2]).text.trim().toString()


                            if(!name.isNullOrEmpty()&&!number.isNullOrEmpty()&&!city.isNullOrEmpty()) {
                                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                                intent.putExtra("name",name)
                                intent.putExtra("number",number)
                                intent.putExtra("city",city)
                                startActivity(intent)

                            }
                            else{
                                Toast.makeText(this@MainActivity, "Please fill all details",Toast.LENGTH_SHORT).show()
                            }
                        }
                        linearLayout.addView(button)
                    }
                }
            }

            override fun onImageSuccess(value: Result<String>) {

            }

            override fun onFailure() {

            }

        })





    }


}