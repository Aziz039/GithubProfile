package com.example.retrofit_github

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


/*
Profiles tested:
Aziz039
Apple
Kotlin
Android
 */

class MainActivity : AppCompatActivity() {
    private lateinit var clMain: ConstraintLayout
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var bt_search: Button
    private lateinit var it_userID: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clMain = findViewById(R.id.clMain)
        bt_search = findViewById(R.id.bt_search)
        it_userID = findViewById(R.id.it_userID)
        sharedPreferences = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)



        bt_search.setOnClickListener {

            if (it_userID.text.isNullOrBlank()) {
                Toast.makeText(clMain.context, "Empty field..", Toast.LENGTH_SHORT).show()
            } else {
                val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
                val call: Call<resultModel?>? = apiInterface!!.doGetListResources(it_userID.text.toString())

                call?.enqueue(object : Callback<resultModel?> {
                    override fun onResponse(
                        call: Call<resultModel?>?,
                        response: Response<resultModel?>
                    ) {
                        if (response.code() == 200) {
                            try {
                                //                    intent.putExtra("username", "$username")
                                Log.d("MainActivityAPI", "Fetched Successfully!")
                                val jsonObject = JSONObject(Gson().toJson(response.body()))
                                // save Username
                                with(sharedPreferences.edit()) {
                                    if(!jsonObject.getString("login").isNullOrBlank()) {
                                        putString("username", jsonObject.getString("login"))
                                    }
                                    if(!jsonObject.getString("avatar_url").isNullOrBlank()) {
                                        putString("avatar_url", jsonObject.getString("avatar_url"))
                                    }
                                    if(!jsonObject.getString("html_url").isNullOrBlank()) {
                                        putString("html_url", jsonObject.getString("html_url"))
                                    }
                                    if(!jsonObject.getString("name").isNullOrBlank()) {
                                        putString("name", jsonObject.getString("name"))
                                    }
                                    if(!jsonObject.getString("public_repos").isNullOrBlank()) {
                                        putString("public_repos", jsonObject.getString("public_repos"))
                                    }
                                    if(!jsonObject.getString("followers").isNullOrBlank()) {
                                        putString("followers", jsonObject.getString("followers"))
                                    }
                                    if(!jsonObject.getString("following").isNullOrBlank()) {
                                        putString("following", jsonObject.getString("following"))
                                    }
                                    if(!jsonObject.getString("created_at").isNullOrBlank()) {
                                        putString("created_at", jsonObject.getString("created_at"))
                                    }
                                    apply()
                                }
                                val intent = Intent(clMain.context, ProfileActivity::class.java)
                                startActivity(intent)
                            } catch (e: Exception ) {
                                Log.d("MainActivityAPI", "Error $e")
                                Toast.makeText(clMain.context, "Server error..", Toast.LENGTH_SHORT).show()
                            }
                            val intent = Intent(clMain.context, ProfileActivity::class.java)
                            startActivity(intent)
                        } else {
                            Log.d("MainActivityAPI", "ERROR")
                            Toast.makeText(clMain.context, "Invalid Username..", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<resultModel?>, t: Throwable) {
                        Toast.makeText(clMain.context, "Error..", Toast.LENGTH_SHORT).show()
                        call.cancel()
                    }
                })
            }
        }

    }
}