package com.rejangtoindo.kamusbahasarejang.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.rejangtoindo.kamusbahasarejang.model.kamus
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable
import org.jetbrains.anko.db.dropIndex

class HelperKamus(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "kamus.db") {
    companion object {
        private var instance: HelperKamus? = null

        @Synchronized
        fun getInstance(ctx: Context): HelperKamus {
            if (instance == null) {
                instance = HelperKamus(ctx.applicationContext)
            }
            return instance as HelperKamus
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            kamus.TABLE, true,
            kamus.indo to TEXT,
            kamus.rejang to TEXT,
                    kamus.kelas_kata to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropIndex(kamus.TABLE, true)
    }
}

val Context.database: HelperKamus
    get() = HelperKamus.getInstance(
        applicationContext
    )