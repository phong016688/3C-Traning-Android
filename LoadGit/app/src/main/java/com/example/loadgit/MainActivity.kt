package com.example.loadgit

import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.JsonReader
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    var load: LoadData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        load = LoadData()
        load?.truyenData = object : TruyenData {
            override fun truyendata(list: List<Data>) {
                val adapter = Adapter(applicationContext, list)
                recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.setHasFixedSize(true)
                recyclerView.adapter = adapter
                progressBar.visibility = ProgressBar.GONE
                recyclerView.visibility = RecyclerView.VISIBLE
            }
        }
        button.setOnClickListener {
            if (checkInternetConnection()) {
                it.visibility = Button.GONE
                progressBar.visibility = ProgressBar.VISIBLE
                load?.execute("https://api.github.com/search/users?q=phong")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkInternetConnection(): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            val connManager =
                this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connManager.activeNetworkInfo

            if (networkInfo == null) {
                Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG)
                    .show()
                return false
            }

            if (!networkInfo.isConnected) {
                Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show()
                return false
            }

            if (!networkInfo.isAvailable) {
                Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show()
                return false
            }
        }
        Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show()
        return true
    }
}

class LoadData : AsyncTask<String, Unit, List<Data>>() {
    var list: ArrayList<Data> = ArrayList()
    val buffer = StringBuffer()
    var truyenData: TruyenData? = null
    override fun doInBackground(vararg p0: String?): List<Data> {
        val url: String? = p0[0]
        val httpcon: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
        httpcon.requestMethod = "GET"
        val inputStream: InputStream = httpcon.inputStream
        val bufferread: BufferedReader = inputStream.bufferedReader(Charsets.UTF_8)
        var line: String?
        do {
            line = bufferread.readLine()
            buffer.append(line)
        } while (line != null)
        val json1 = JSONObject(buffer.toString())
        val jsonArr: JSONArray = json1.getJSONArray("items")
        for (i in 0 until jsonArr.length()) {
            val id = (jsonArr[i] as JSONObject).get("id").toString()
            val name = (jsonArr[i] as JSONObject).get("login").toString()
            val img = (jsonArr[i] as JSONObject).get("avatar_url").toString()
            list.add(Data("  id: $id", "  name: $name", img))
        }
        return list
    }

    override fun onPostExecute(result: List<Data>?) {
        super.onPostExecute(result)
        truyenData?.truyendata(list)
    }
}

interface TruyenData {
    fun truyendata(list: List<Data>)
}

class Data(id: String, name: String, imager: String) {
    var id: String = id
    var name: String = name
    var image: String = imager

}