package com.example.jetpackComposeTemplate.data.local
import androidx.room.Dao

@Dao
interface InstallationDao {
/*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInstallations(installations: List<InstallationEntryForCaching>)

    @Query("SELECT * FROM installationsList")
    suspend fun getCachedInstallations(): List<InstallationEntryForCaching>

    @Query("DELETE FROM installationsList")
    suspend fun clearAllInstallationsListFromCache()

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun createInstallationDraft(createTerminalInstallationForCaching: CreateTerminalInstallationForDraft):Long

    @Query("SELECT * FROM installation")
    suspend fun getDraftInstallationsList(): List<CreateTerminalInstallationForDraft>

    @Query("SELECT * FROM installation")
    fun getDraftInstallationsFlow(): Flow<List<CreateTerminalInstallationForDraft>>


    @Query("SELECT * FROM installation WHERE id = :id")
    suspend fun getDraftInstallationByID(id: Long): CreateTerminalInstallationForDraft

    @Delete
    suspend fun deleteDraftData(draft: CreateTerminalInstallationForDraft): Int

    @Query("SELECT * FROM installation WHERE LOWER(name) LIKE LOWER('%' || :searchQuery || '%')")
    suspend fun getSearchedDraftInstallations(searchQuery: String): List<CreateTerminalInstallationForDraft>

    @Query("UPDATE installation SET status = :newStatus WHERE id = :id")
    suspend fun updateDraftInstallationStatusById(id: Int, newStatus: String): Int

    @Query("SELECT * FROM installation WHERE status = :status")
    suspend fun getDraftInstallationsByStatus(status: String): List<CreateTerminalInstallationForDraft>

    @Query("UPDATE installation SET errorDetails = :errorDetails WHERE id = :id")
    suspend fun setErrorDetailsToDraftById(id: Int, errorDetails: String): Int
*/
}