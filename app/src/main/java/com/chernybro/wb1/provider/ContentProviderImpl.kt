package com.chernybro.wb1.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri


// Пример реализации провайдера. Если мы реализуем класс, мы можем класть наши данные
// чтобы другие приложения могли получить данные нашего приложения

// Пример - различные приложения для заметок, контактов.
class ContentProviderImpl : ContentProvider() {
    private var dbHelper: OurDatabaseHelper? = null

    companion object {
        const val AUTHORITY =
            "com.chernybro.wb1.authority"
        val CONTENT_URI = Uri.parse("content://$AUTHORITY")
    }

    override fun onCreate(): Boolean {
        dbHelper = OurDatabaseHelper(context)
        return true
    }

    override fun delete(uri: Uri, where: String?, args: Array<out String>?): Int {
        val table = getTableName(uri)
        val dataBase = dbHelper?.writableDatabase
        return dataBase?.delete(table, where, args) ?: 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, initialValues: ContentValues?): Uri? {
        val table = getTableName(uri)
        val database = dbHelper?.writableDatabase
        val value = database?.insert(table, null, initialValues)
        return Uri.withAppendedPath(CONTENT_URI, value.toString())
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?,
    ): Cursor? {
        val table = getTableName(uri)
        val database = dbHelper?.readableDatabase
        return database?.query(table, projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        whereClause: String?,
        whereArgs: Array<out String>?,
    ): Int {
        val table = getTableName(uri)
        val database = dbHelper?.writableDatabase
        return database?.update(table, values, whereClause, whereArgs) ?: 0
    }


    private fun getTableName(uri: Uri): String? {
        var path = uri.path
        path = path?.replace("/", "")
        return path
    }
}

class OurDatabaseHelper(
    context: Context?,
) : SQLiteOpenHelper(context, "name", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}