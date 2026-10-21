package com.nicolas.tareas.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.nicolas.tareas.utils.DatabaseManager

class TaskDAO(val context: Context) {

    lateinit var db: SQLiteDatabase

    fun open(){
        db = DatabaseManager(context).writableDatabase
    }

    fun close(){
        db.close()
    }

    fun insert(task: Task){
        // Gets the data repository in write mode
        open()

// Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(Task.COLUMN_NAME_TITLE, task.title)
        values.put(Task.COLUMN_NAME_DONE, task.done)

        try {
            // Update the row
            val newRowId = db.insertOrThrow(Task.TABLE_NAME, null, values)
            Log.i("DATABASE", "Inserted row inserted with ID: $newRowId ")
        } catch (e: Exception) {
            Log.e("DATABASE", e.localizedMessage, e)
        } finally {
            close()
        }


    }

    fun update(task: Task){

    }

    fun delete(task: Task){
        delete(task.id)
    }

    fun delete(id: Int){
        db.delete(Task.TABLE_NAME, "${Task.COLUMN_NAME_ID} = $id", null)
    }

    fun findById(id: Int): Task?{

    }

    fun findAll(): List<Task>{

    }

}
