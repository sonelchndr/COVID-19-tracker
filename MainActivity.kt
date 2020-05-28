package com.sonelchndr.covid_19tracker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity


import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.android.volley.Request

import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


   lateinit var tvCases: TextView
    lateinit var tvRecovered: TextView
    lateinit var tvCritical: TextView
    lateinit var tvActive: TextView
    lateinit var tvTodayCases: TextView
    lateinit var tvTotalDeaths: TextView
    lateinit var tvTodayDeaths: TextView
    lateinit var tvAffectedCountries: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tvCases = findViewById(R.id.tvCases)
        tvRecovered = findViewById(R.id.tvRecovered)
        tvCritical = findViewById(R.id.tvCritical)
        tvActive = findViewById(R.id.tvActive)
        tvTodayCases = findViewById(R.id.tvTodayCases)
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths)
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths)
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries)


        fetchdata()

    }

    private fun fetchdata() {



       val url = "https:// corona.lmao.ninja/v2/all"

        val request = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { response ->

                try {


                    val jsonObject = JSONObject(
                        response.toString()
                    )


                    tvCases.text = jsonObject.getString(
                        "cases"
                    )
                    tvRecovered.text = jsonObject.getString(
                        "recovered"
                    )
                    tvCritical.text = jsonObject.getString(
                        "critical"
                    )
                    tvActive.text = jsonObject.getString(
                        "active"
                    )
                    tvTodayCases.text = jsonObject.getString(
                        "todayCases"
                    )
                    tvTotalDeaths.text = jsonObject.getString(
                        "deaths"
                    )
                    tvTodayDeaths.text = jsonObject.getString(
                        "todayDeaths"
                    )
                    tvAffectedCountries.text = jsonObject.getString(
                        "affectedCountries"
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(
                    this@MainActivity,
                    error.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
            })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }
}