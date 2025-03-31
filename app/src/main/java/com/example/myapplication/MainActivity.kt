package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var edtHoTen: EditText
    private lateinit var edtMSSV: EditText
    private lateinit var btnAdd: Button
    private lateinit var listView: ListView
    private lateinit var adapter: StudentAdapter
    private val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtHoTen = findViewById(R.id.edtHoTen)
        edtMSSV = findViewById(R.id.edtMSSV)
        btnAdd = findViewById(R.id.btnAdd)
        listView = findViewById(R.id.listView)

        adapter = StudentAdapter(this, studentList)
        listView.adapter = adapter

        btnAdd.setOnClickListener {
            val hoTen = edtHoTen.text.toString().trim()
            val mssv = edtMSSV.text.toString().trim()
            if (hoTen.isNotEmpty() && mssv.isNotEmpty()) {
                studentList.add(0, Student(hoTen, mssv))
                adapter.notifyDataSetChanged()
                edtHoTen.text.clear()
                edtMSSV.text.clear()
            }
        }
    }
}

data class Student(val name: String, val mssv: String)

class StudentAdapter(private val context: Context, private val students: MutableList<Student>) :
    ArrayAdapter<Student>(context, 0, students) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_student, parent, false)

        val txtNameMssv: TextView = view.findViewById(R.id.txtNameMssv)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)

        val student = students[position]
        txtNameMssv.text = "${student.name}\n${student.mssv}"

        btnDelete.setOnClickListener {
            students.removeAt(position)
            notifyDataSetChanged()
        }

        return view
    }
}
