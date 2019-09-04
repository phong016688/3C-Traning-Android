package com.phong.loaddatahttp

import android.os.AsyncTask
import java.net.HttpURLConnection
import java.net.URL

class LoadDataFromSever : AsyncTask<String, Int, String>() {
    override fun doInBackground(vararg strings: String): String? {
        var url : URL = URL(strings[0])
        var httpcon : HttpURLConnection = url.openConnection() as HttpURLConnection
        return null
    }
}
