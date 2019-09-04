package com.phong.loaddatahttp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import androidx.recyclerview.widget.DefaultItemAnimator

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.widget.Toast
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ImageView
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    var listData: List<ItemData>? = null
    var adapter: AdapterImager? = null
    var animator : DefaultItemAnimator = DefaultItemAnimator()
    var thread : Thread? = null
    var buffer : StringBuffer = StringBuffer()
    var loadData : LoadDataFromSever? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        adapter = AdapterImager(listData, applicationContext);
        recyclder_View_Data.adapter = adapter
        recyclder_View_Data.setHasFixedSize(true)
        recyclder_View_Data.animation = animator as Animation

        thread = Thread(Runnable {
            var url : URL = URL("https://api.github.com/users?since=135")
            var httpcon : HttpURLConnection = url.openConnection() as HttpURLConnection
            httpcon.requestMethod = "GET"
            var inputstream: InputStream = httpcon.inputStream
            var reader : BufferedReader = BufferedReader(inputstream.bufferedReader(Charsets.UTF_8))
            var line : String? = null
            do {
                line = reader.readLine()
                if(line != null)
                    buffer.append(line)
            }while (line != null)
            buffer.removeRange(0,1)
            buffer.removeRange(buffer.length-1, buffer.length)
            var jsonArray : JSONArray = JSONArray(buffer)
            for(i in 0 .. jsonArray.length()){
                var jsonObject : JSONObject = jsonArray.getJSONObject(i)
                var urlimage: URL = URL(jsonObject.getString("avatar_url"))
                var text : String = jsonObject.getString("login")
                var img : Bitmap = BitmapFactory.decodeStream(url.openConnection().inputStream)
                (listData as? ArrayList)?.add(ItemData(img, text))
            }
        })
        thread?.start()
    }

    private fun checkInternetConnection(): Boolean {

        val connManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connManager.activeNetworkInfo

        if (networkInfo == null) {
            Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show()
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
        Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
