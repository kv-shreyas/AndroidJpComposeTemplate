package com.example.jetpackComposeTemplate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import kotlin.collections.toList
import kotlin.jvm.java


data class InstallationModel(
    val entries: List<InstallationEntry>,
    val page: Int,
    val totalCount: Int,
    val pageSize: Int
)

data class InstallationEntry(
    val authorizerDepartment: String,
    val authorizerName: String,
    val bleMacAddress: String,
    val bleName: String,
    val blePassword: String,
    val boatName: String,
    val boatNumber: String,
    val createdAt: String,
    val harbourName: String,
    val hardwareSerialNumber: String,
    val id: String,
    val imagePaths: List<String>,
    val installationCompletedTimestamp: String,
    val installationStartedTimestamp: String,
    val isDeleted: Boolean,
    val locationLatLng: String,
    val name: String,
    val phoneNumber: String,
    val terminalId: String,
    val assignedTID: String,
    val regionId: Int,
    val witnessName: String,
    val witnessRelation: String,
    val remarks: String
)

@Entity(tableName = "installationsList")
data class InstallationEntryForCaching(
    val authorizerDepartment: String,
    val authorizerName: String,
    val bleMacAddress: String,
    val bleName: String,
    val blePassword: String,
    val boatName: String,
    val boatNumber: String,
    val createdAt: String,
    val harbourName: String,
    val hardwareSerialNumber: String,
    @PrimaryKey val id: String,
    val imagePaths: String,
    val installationCompletedTimestamp: String,
    val installationStartedTimestamp: String,
    val isDeleted: Boolean,
    val locationLatLng: String,
    val name: String,
    val phoneNumber: String,
    val terminalId: String,
    val assignedTID: String,
    val regionId: Int,
    val witnessName: String,
    val witnessRelation: String,
    val remarks: String
)

fun InstallationEntry.toCachingModel(): InstallationEntryForCaching {
    return InstallationEntryForCaching(
        id = this.id,
        authorizerDepartment = this.authorizerDepartment,
        authorizerName = this.authorizerName,
        bleMacAddress = this.bleMacAddress,
        bleName = this.bleName,
        blePassword = this.blePassword,
        boatName = this.boatName,
        boatNumber = this.boatNumber,
        createdAt = this.createdAt,
        harbourName = this.harbourName,
        hardwareSerialNumber = this.hardwareSerialNumber,
        imagePaths = Gson().toJson(this.imagePaths), // Convert List<String> to JSON string
        installationCompletedTimestamp = this.installationCompletedTimestamp,
        installationStartedTimestamp = this.installationStartedTimestamp,
        isDeleted = this.isDeleted,
        locationLatLng = this.locationLatLng,
        name = this.name,
        phoneNumber = this.phoneNumber,
        terminalId = this.terminalId,
        assignedTID = this.assignedTID,
        regionId = this.regionId,
        witnessName = this.witnessName,
        witnessRelation = this.witnessRelation,
        remarks = this.remarks
    )
}

fun InstallationEntryForCaching.toDomainModel(): InstallationEntry {
    return InstallationEntry(
        id = this.id,
        authorizerDepartment = this.authorizerDepartment,
        authorizerName = this.authorizerName,
        bleMacAddress = this.bleMacAddress,
        bleName = this.bleName,
        blePassword = this.blePassword,
        boatName = this.boatName,
        boatNumber = this.boatNumber,
        createdAt = this.createdAt,
        harbourName = this.harbourName,
        hardwareSerialNumber = this.hardwareSerialNumber,
        imagePaths = Gson().fromJson(this.imagePaths, Array<String>::class.java).toList(), // Convert JSON string to List<String>
        installationCompletedTimestamp = this.installationCompletedTimestamp,
        installationStartedTimestamp = this.installationStartedTimestamp,
        isDeleted = this.isDeleted,
        locationLatLng = this.locationLatLng,
        name = this.name,
        phoneNumber = this.phoneNumber,
        terminalId = this.terminalId,
        assignedTID = this.assignedTID,
        regionId = this.regionId,
        witnessName = this.witnessName,
        witnessRelation = this.witnessRelation,
        remarks = this.remarks
    )
}