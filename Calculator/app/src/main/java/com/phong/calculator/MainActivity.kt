package com.phong.calculator

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val TAG = "@@@@@@@@@@@"
    private var chuoi: String = ""
    private var pheptinh: String = ""
    private var so1: Double = 0.0
    private var so2: Double = 0.0
    override fun onClick(v: View?) {
        when (v?.getId()) {
            button0?.id -> {
                chuoi += "0"
                editText?.setText(chuoi)
            }
            button1?.id -> {
                chuoi += "1"
                editText?.setText(chuoi)
            }
            button2?.id -> {
                chuoi += "2"
                editText?.setText(chuoi)
            }
            button3?.id -> {
                chuoi += "3"
                editText?.setText(chuoi)
            }
            button4?.id -> {
                chuoi += "4"
                editText?.setText(chuoi)
            }
            button5?.id -> {
                chuoi += "5"
                editText?.setText(chuoi)
            }
            button6?.id -> {
                chuoi += "6"
                editText?.setText(chuoi)
            }
            button7?.id -> {
                chuoi += "7"
                editText?.setText(chuoi)
            }
            button8?.id -> {
                chuoi += "8"
                editText?.setText(chuoi)
            }
            button9?.id -> {
                chuoi += "9"
                editText?.setText(chuoi)
            }
            button_?.id -> {
                chuoi += "."
                editText?.setText(chuoi)
            }
            buttonAC?.id -> {
                if(chuoi != ""){
                chuoi = chuoi.substring(0, chuoi.length-1)}
                editText.setText("${chuoi}")
            }
            buttonCong?.id -> {
                pheptinh = "+"
                chuoi = ""
                try {
                    so1 = editText?.text.toString().toDouble()
                    so2 = 0.0
                } catch (e: NumberFormatException) {
                    Log.d(TAG, e.toString())
                }
                editText?.setText("")
                textViewpheptinh?.setText("${so1}${pheptinh}")
            }
            buttonTru?.id -> {
                pheptinh = "-"
                chuoi = ""
                try {
                    so1 = editText?.text.toString().toDouble()
                    so2 = 0.0
                } catch (e: NumberFormatException) {
                    Log.d(TAG, e.toString())
                }
                editText?.setText("")
                textViewpheptinh?.setText("${so1}${pheptinh}")
            }
            buttonNhan?.id -> {
                pheptinh = "x"
                chuoi = ""
                try {
                    so1 = editText?.text.toString().toDouble()
                    so2 = 0.0
                } catch (e: NumberFormatException) {
                    Log.d(TAG, e.toString())
                }
                editText?.setText("")
                textViewpheptinh?.setText("${so1}${pheptinh}")
            }
            buttonChia?.id -> {
                pheptinh = "/"
                chuoi = ""
                try {
                    so1 = editText?.text.toString().toDouble()
                    so2 = 0.0
                } catch (e: NumberFormatException) {
                    Log.d(TAG, e.toString())
                }
                editText?.setText("")
                textViewpheptinh?.setText("${so1}${pheptinh}")
            }
            buttonBang?.id -> {
                try {
                    so2 = editText?.text.toString().toDouble()
                }catch (e : NumberFormatException){
                    Toast.makeText(applicationContext, "Lỗi định dạng số", Toast.LENGTH_LONG).show()
                }
                var ketqua : String = ""
                when(pheptinh){
                    "+" -> ketqua = cong(so1, so2)
                    "-" -> ketqua = tru(so1, so2)
                    "x" -> ketqua = nhan(so1, so2)
                    "/" -> {
                        ketqua = chia(so1, so2)
                        if (ketqua == "error"){
                            Toast.makeText(applicationContext, "không thể chia cho 0", Toast.LENGTH_LONG).show()
                        }
                    }
                    "%" -> ketqua = chialaydu(so1, so2)
                }
                editText?.setText("${ketqua}")
                textViewpheptinh?.setText("${so1}${pheptinh}${so2}")
            }
            buttonLaydu?.id -> {
                pheptinh = "%"
                chuoi = ""
                try {
                    so1 = editText?.text.toString().toDouble()
                    so2 = 0.0
                } catch (e: NumberFormatException) {
                    Log.d(TAG, e.toString())

                }
                editText?.setText("")
                textViewpheptinh?.setText("${so1}${pheptinh}")
            }
            buttonCongTru?.id -> {
                if(chuoi.contains('-')){
                    chuoi = chuoi.substring(1, chuoi.length)
                }else chuoi = "-$chuoi"
                editText.setText("${chuoi}")
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun cong(a: Double, b: Double): String {
        return "${a + b}"
    }

    private fun tru(a: Double, b: Double): String {
        return "${a - b}"
    }

    private fun nhan(a: Double, b: Double): String {
        return "${a * b}"
    }

    private fun chia(a: Double, b: Double): String {
        var s: String = if (b != 0.0) "${a / b}" else "error"
        return s
    }
    private fun chialaydu(a:Double, b: Double): String{
        return "${a%b}"
    }
    private fun init() {
        button0?.setOnClickListener(this)
        button1?.setOnClickListener(this)
        button2?.setOnClickListener(this)
        button3?.setOnClickListener(this)
        button4?.setOnClickListener(this)
        button5?.setOnClickListener(this)
        button6?.setOnClickListener(this)
        button7?.setOnClickListener(this)
        button8?.setOnClickListener(this)
        button9?.setOnClickListener(this)
        button_?.setOnClickListener(this)
        buttonAC?.setOnClickListener(this)
        buttonCong?.setOnClickListener(this)
        buttonTru?.setOnClickListener(this)
        buttonChia?.setOnClickListener(this)
        buttonNhan?.setOnClickListener(this)
        buttonBang?.setOnClickListener(this)
        buttonCongTru?.setOnClickListener(this)
        buttonAC.setOnLongClickListener{
            editText?.setText("")
            textViewpheptinh?.setText("")
            so1 = 0.0
            so2 = 0.0
            chuoi = ""
            true
        }


    }
}
