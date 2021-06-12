package com.tomdok.emojiclicker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import database.GameDatabase
import database.Record
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RecordActivity : AppCompatActivity() {

    private val listView by lazy {
        findViewById<ListView>(R.id.record_listView)
    }

    private val buttonBack by lazy {
        findViewById<Button>(R.id.record_buttonBack)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {

        }

        setContentView(R.layout.activity_record)

        buttonBack.setOnClickListener {

            finish()
        }

        var records = listOf<Record>()

        runBlocking {

            CoroutineScope(IO).launch {

                records = GameDatabase.getInstance(applicationContext).recordDAO.getAll()
            }.join()
        }

        listView.adapter = RecordListViewAdapter(applicationContext, records)
    }

    class RecordListViewAdapter(private val context: Context, private val records: List<Record>): BaseAdapter() {

        override fun getCount(): Int = records.size

        override fun getItem(position: Int): Any = records[position]

        override fun getItemId(position: Int): Long = records[position].id!!

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val record = records[position]
            val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.records_listview_row, parent, false)

            val textViewPosition = view.findViewById<TextView>(R.id.records_row_textViewPosition)
            textViewPosition.text = position.toString()

            val textViewName = view.findViewById<TextView>(R.id.records_row_textViewName)
            textViewName.text = record.player_name

            val textViewLevel = view.findViewById<TextView>(R.id.records_row_textViewLevel)
            textViewLevel.text = record.level.toString()

            val textViewTime = view.findViewById<TextView>(R.id.records_row_textViewTime)
            textViewTime.text = record.time.toString()

            val imageView = view.findViewById<ImageView>(R.id.records_row_imageView)
            imageView.setImageResource(R.drawable.player)

            return view
        }
    }
}