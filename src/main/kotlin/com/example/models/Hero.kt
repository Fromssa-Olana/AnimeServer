package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class Hero(
    val id: Int,
    val name: String,
    val image: String,
    val about:String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
)
object Heroes : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val image= varchar("image", 1024)
    val about= varchar("about", 2024)
    val rating = double("rating")
    val power = integer("power")
    val month= varchar("month", 128)
    val day= varchar("day", 128)
    override val primaryKey = PrimaryKey(id)
}