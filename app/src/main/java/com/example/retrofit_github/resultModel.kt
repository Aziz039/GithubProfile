package com.example.retrofit_github

import com.google.gson.annotations.SerializedName




class resultModel {
    @SerializedName("login")
    private var login: String? = null

    @SerializedName("avatar_url")
    private var avatar_url: String? = null

    @SerializedName("html_url")
    private var html_url: String? = null

    @SerializedName("name")
    private var name: String? = null

    @SerializedName("location")
    private var location: String? = null

    @SerializedName("bio")
    private var bio: String? = null

    @SerializedName("public_repos")
    private var public_repos: String? = null

    @SerializedName("followers")
    private var followers: String? = null

    @SerializedName("following")
    private var following: String? = null

    @SerializedName("created_at")
    private var created_at: String? = null

}