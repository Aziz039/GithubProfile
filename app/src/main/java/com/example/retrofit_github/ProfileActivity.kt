package com.example.retrofit_github

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso

class ProfileActivity: AppCompatActivity() {
    private lateinit var clProfile: ConstraintLayout
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var bt_newSearch: Button

    private lateinit var img_icon: ImageView
    private lateinit var tv_username: TextView
    private lateinit var tv_name: TextView
    private lateinit var tv_public_repos: TextView
    private lateinit var tv_followers: TextView
    private lateinit var tv_following: TextView
    private lateinit var tv_created_at: TextView
    private lateinit var tv_html_url: TextView
    private lateinit var tv_avatar_url: TextView

    private lateinit var username: String
    private lateinit var avatar_url: String
    private lateinit var html_url: String
    private lateinit var name: String
    private lateinit var location: String
    private lateinit var bio: String
    private lateinit var public_repos: String
    private lateinit var followers: String
    private lateinit var following: String
    private lateinit var created_at: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        clProfile = findViewById(R.id.clProfile)
        sharedPreferences = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        bt_newSearch = findViewById(R.id.bt_newSearch)

        initVars()
        setUsernameInfo ()
        addUsernameInfoToUI()

        bt_newSearch.setOnClickListener {
            clearUsernameInfo ()
            val intent = Intent(clProfile.context, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initVars() {
        img_icon = findViewById(R.id.img_icon)
        tv_username = findViewById(R.id.tv_username)
         tv_name = findViewById(R.id.tv_name)
       tv_public_repos = findViewById(R.id.tv_public_repos)
        tv_followers = findViewById(R.id.tv_followers)
         tv_following = findViewById(R.id.tv_following)
       tv_created_at = findViewById(R.id.tv_created_at)
        tv_html_url = findViewById(R.id.tv_html_url)
       tv_avatar_url = findViewById(R.id.tv_avatar_url)
    }

    private fun setUsernameInfo () {
        username = sharedPreferences.getString("username", "").toString()
        avatar_url = sharedPreferences.getString("avatar_url", "").toString()
        html_url = sharedPreferences.getString("html_url", "").toString()
        name = sharedPreferences.getString("name", "").toString()
        location = sharedPreferences.getString("location", "").toString()
        bio = sharedPreferences.getString("bio", "").toString()
        public_repos = sharedPreferences.getString("public_repos", "").toString()
        followers = sharedPreferences.getString("followers", "").toString()
        following = sharedPreferences.getString("following", "").toString()
        created_at = sharedPreferences.getString("created_at", "").toString()
    }
    private fun addUsernameInfoToUI() {
        if (!avatar_url.isNullOrBlank()) {
            tv_avatar_url.text = "Avatar URL: $avatar_url"
            Picasso.get().load("$avatar_url").into(img_icon);
        }
        if (!username.isNullOrBlank()) {
            tv_username.text = "Username: $username"
        }
        if (!html_url.isNullOrBlank()) {
            tv_html_url.text = "HTML URL: $html_url"
        }
        if (!name.isNullOrBlank()) {
            tv_name.text = "Name: $name"
        }
        if (!public_repos.isNullOrBlank()) {
            tv_public_repos.text = "Public repos: $public_repos"
        }
        if (!followers.isNullOrBlank()) {
            tv_followers.text = "Followers: $followers"
        }
        if (!following.isNullOrBlank()) {
            tv_following.text = "Following: $following"
        }
        if (!created_at.isNullOrBlank()) {
            tv_created_at.text = "Created at: $created_at"
        }
    }

    private fun clearUsernameInfo () {
        // clear Username
        with(sharedPreferences.edit()) {
            putString("username", "")
            putString("avatar_url", "")
            putString("html_url", "")
            putString("name", "")
            putString("public_repos", "")
            putString("followers", "")
            putString("following", "")
            putString("created_at", "")
            apply()
        }
        tv_avatar_url.text = ""
        tv_username.text = ""
        tv_html_url.text = ""
        tv_name.text = ""
        tv_public_repos.text = ""
        tv_followers.text = ""
        tv_following.text = ""
        tv_created_at.text = ""
        img_icon.setImageResource(R.drawable.ic_github_icon)
    }
}