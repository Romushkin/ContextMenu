package com.example.contextmenu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var assessmentEt: EditText
    private lateinit var randomBTN: Button
    private var isButtonClick = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        assessmentEt = findViewById(R.id.assessmentET)
        registerForContextMenu(assessmentEt)

        randomBTN = findViewById(R.id.randomBTN)
        randomBTN.setOnClickListener(this)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_change_color -> {
                if (!isButtonClick) {
                    when (assessmentEt.text.toString().toInt()) {
                        1 -> assessmentEt.setBackgroundColor(getColor(R.color.text_orange))
                        2 -> assessmentEt.setBackgroundColor(getColor(R.color.text_yellow))
                        3 -> assessmentEt.setBackgroundColor(getColor(R.color.text_green))
                        4 -> assessmentEt.setBackgroundColor(getColor(R.color.text_blue))
                        5 -> assessmentEt.setBackgroundColor(getColor(R.color.text_red))
                        else -> Toast.makeText(this, "Введите оценку от 1 до 5", Toast.LENGTH_LONG).show()
                    }
                } else {
                    when (assessmentEt.text.toString().toInt()) {
                        in 1..10 -> assessmentEt.setBackgroundColor(getColor(R.color.text_red))
                        in 11..20 -> assessmentEt.setBackgroundColor(getColor(R.color.text_orange))
                        in 21..30 -> assessmentEt.setBackgroundColor(getColor(R.color.text_yellow))
                        in 31..40 -> assessmentEt.setBackgroundColor(getColor(R.color.text_green))
                        else -> assessmentEt.setBackgroundColor(getColor(R.color.text_blue))
                    }
                    isButtonClick = false
                }
            }

            R.id.menu_exit -> finish()
            else -> return super.onContextItemSelected(item)
        }
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.randomBTN -> {
                assessmentEt.setText((1..50).random().toString())
                isButtonClick = true
            }

            else -> {
                isButtonClick = false
            }
        }
    }

}