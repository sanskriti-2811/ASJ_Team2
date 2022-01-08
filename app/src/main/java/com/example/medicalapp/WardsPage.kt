package com.example.medicalapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class WardsPage : AppCompatActivity() {


    lateinit var listView: ListView
    var wards: ArrayList<String> = ArrayList()
    var arrayAdapter: ArrayAdapter<String>? = null
    lateinit var etSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wards_page)

        title = "Medical App"
        listView = findViewById(R.id.listView)
        etSearch = findViewById(R.id.etSearch)
        wards.add("COVID ICU 1")
        wards.add("COVID ICU ")
        wards.add("COVID ICU 10")
        wards.add("COVID ICU 100")
        wards.add("COVID ICU 101")
        wards.add("COVID ICU 102")
        wards.add("COVID ICU 103")
        wards.add("COVID ICU 104")
        wards.add("COVID ICU 105")
        wards.add("COVID ICU 106")
        wards.add("COVID ICU 107")
        wards.add("COVID ICU 108")
        wards.add("COVID ICU 109")
        wards.add("COVID ICU 11")



        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, wards)
        listView.adapter = arrayAdapter
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                arrayAdapter!!.filter.filter(s)
            }
            override fun afterTextChanged(s: Editable) {}
        })

        listView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                if (position == 1) {
                    val myIntent = Intent(view.context, BioMedicalWasteAudit::class.java)
                    startActivityForResult(myIntent, 0)
                }

            }
    }


}