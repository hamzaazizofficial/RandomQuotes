package com.example.randomquotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class ApiViewModel(application: Application) : AndroidViewModel(application) {
    fun loadQuote(onSuccess: (JSONObject) -> Unit){
        val queue = Volley.newRequestQueue(getApplication())
        val url = "https://api.quotable.io/random"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                response ->
                onSuccess(response)
            },
            {

            }
        )
        //Add the request to the request que
        queue.add(jsonObjectRequest)
    }
}