package com.indocyber.pokeapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.indocyber.pokeapp.data.db.entity.RemoteKeyEntity

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(key: RemoteKeyEntity)

    @Query("SELECT * FROM remote_keys ORDER BY id ASC")
    suspend fun getAllRemoteKeys(): List<RemoteKeyEntity>

    @Query("DELETE FROM remote_keys")
    suspend fun clearAllKeys()
}