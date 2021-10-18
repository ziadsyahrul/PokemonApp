package com.indocyber.pokeapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val next: String?,
    val previous: String?
)