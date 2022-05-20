package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*

class DAOFacadeImpl : DAOFacade {
    init {
        val heroes = listOf(
            Hero(
                id = 1,
                name = "Sasuke",
                image = "/images/sasuke.jpg",
                about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan.",
                rating = 5.0,
                power = 98,
                month = "July",
                day = "23rd"
            ),
            Hero(
                id = 2,
                name = "Naruto",
                image = "/images/naruto.jpg",
                about = "Naruto Uzumaki (うずまきナルト, Uzumaki Naruto) is a shinobi of Konohagakure's Uzumaki clan.",
                rating = 5.0,
                power = 98,
                month = "Oct",
                day = "10th"
            ),
            Hero(
                id = 3,
                name = "Sakura",
                image = "/images/sakura.jpg",
                about = "Sakura Uchiha (うちはサクラ, Uchiha Sakura, née Haruno (春野)) is a kunoichi of Konohagakure.",
                rating = 4.5,
                power = 92,
                month = "Mar",
                day = "28th"
            )
        )
        runBlocking {
            if (allHeroes().isEmpty()) {
                for (hero in heroes) {
                    println("Saving: ${hero.name}")
                    addHero(hero)
                    println("done Saving${hero.name}")
                }
            }
        }
    }

    private fun resultRowToHero(row: ResultRow) = Hero(
        id = row[Heroes.id],
        name = row[Heroes.name],
        image = row[Heroes.image],
        about = row[Heroes.about],
        rating = row[Heroes.rating],
        power = row[Heroes.power],
        month = row[Heroes.month],
        day = row[Heroes.day]
    )

    override suspend fun allHeroes(): List<Hero> = dbQuery {
        Heroes.selectAll().map(::resultRowToHero)
    }

    override suspend fun findHero(heroName: String?): ApiResponse = dbQuery {
      val hero = Heroes
            .select { Heroes.name eq heroName!! }
            .map(::resultRowToHero)
            .singleOrNull()
        ApiResponse(
            success = true,
            message = "ok",
            prevPage = null,
            nextPage = null,
            heroes = listOf(hero)

        )
    }

    override suspend fun addHero(hero: Hero): Hero? = dbQuery {
        val insertStatement = Heroes.insert {
            it[name] = hero.name
            it[image] = hero.image
            it[about] = hero.about
            it[month] = hero.month
            it[power] = hero.power
            it[rating] = hero.rating
            it[day] = hero.day

        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToHero)
    }


    override suspend fun deleteHero(name: String): Boolean = dbQuery {
        Heroes.deleteWhere { Heroes.name eq name } > 0
    }

}
