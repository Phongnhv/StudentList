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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etMSSV = findViewById<EditText>(R.id.etMSSV)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val rvStudents = findViewById<RecyclerView>(R.id.rvStudents)

        studentAdapter = StudentAdapter(studentList)
        rvStudents.layoutManager = LinearLayoutManager(this)
        rvStudents.adapter = studentAdapter

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val mssv = etMSSV.text.toString()

            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                val newStudent = Student(name, mssv)
                studentAdapter.addStudent(newStudent)

                etName.text.clear()
                etMSSV.text.clear()
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

