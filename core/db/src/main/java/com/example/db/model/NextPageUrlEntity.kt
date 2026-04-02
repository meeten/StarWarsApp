package com.example.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pageUrl")
data class NextPageUrlEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val url: String?
)
