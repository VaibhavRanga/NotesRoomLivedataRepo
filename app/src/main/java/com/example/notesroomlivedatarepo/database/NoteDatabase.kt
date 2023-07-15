package com.example.notesroomlivedatarepo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesroomlivedatarepo.models.NoteModel

@Database(entities = [NoteModel::class], exportSchema = false, version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        @Volatile
        private var DB_INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            if (DB_INSTANCE == null) {
                synchronized(this) {
                    DB_INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database"
                    )
                        .allowMainThreadQueries().build()
                }
            }
            return DB_INSTANCE!!
        }
    }
}
