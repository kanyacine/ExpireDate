package com.example.expiredate

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private fun isGTINValid(GTIN : String) : Boolean
    {
        return GTIN.length in 8..14
    }

    private fun standardizeGTIN(GTIN : String) : String
    {
        var GTIN14 = GTIN
        while(GTIN14.length < 14)
        {
            GTIN14 = "0$GTIN14"
        }
        return GTIN14
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list : MutableList<GTINModel> = ArrayList()
        val adapter = GTINListAdapter(list)

        val mainListRecyclerView : RecyclerView = findViewById(R.id.main_recycler)
        mainListRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        mainListRecyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))
        mainListRecyclerView.adapter = adapter

        val gtinEditText : EditText = findViewById(R.id.main_editText_gtin)
        val searchButton : ImageButton = findViewById(R.id.main_btn_search)
        val addButton : ImageButton = findViewById(R.id.main_btn_add)

        addButton.setOnClickListener {
            mainListRecyclerView.adapter = adapter

            if(isGTINValid(gtinEditText.text.toString())) {
                val calendar: Calendar = Calendar.getInstance()
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH)
                val year = calendar.get(Calendar.YEAR)
                var date: String
                val datePickerDialog =
                    DatePickerDialog(this@MainActivity, { view, expYear, expMonth, expDay ->
                        date = "" + expDay + "/" + (expMonth +1) + "/" + expYear
                        val find : GTINModel? = list.find {standardizeGTIN(gtinEditText.text.toString()) == it.GTIN}
                        if(find != null) {
                            find.Date = date
                        }
                        else
                            list.add(GTINModel(standardizeGTIN(gtinEditText.text.toString()),date))
                        adapter.notifyDataSetChanged()
                    }, year, month, day)
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                datePickerDialog.show()
            }
            else
            {
                Toast.makeText(this@MainActivity, "GTIN not valid", Toast.LENGTH_SHORT).show()
            }

        }
        searchButton.setOnClickListener {
            if(isGTINValid(gtinEditText.text.toString()))
            {
                val find : GTINModel? = list.find {standardizeGTIN(gtinEditText.text.toString())  == it.GTIN}
                if(find != null) {
                    val searchItem = ArrayList<GTINModel>()
                    searchItem.add(find)
                    mainListRecyclerView.adapter = (GTINListAdapter(searchItem))
                }
                else
                {
                    mainListRecyclerView.adapter = adapter
                    Toast.makeText(this@MainActivity, "GTIN not found", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                mainListRecyclerView.adapter = adapter
                Toast.makeText(this@MainActivity, "GTIN not valid", Toast.LENGTH_SHORT).show()
            }
        }

        gtinEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchButton.performClick()
                true
            } else {
                false
            }
        }
    }
}