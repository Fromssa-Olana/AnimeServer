
   
package com.example.dao

import com.example.models.*

interface DAOFacade {
    suspend fun allHeroes(): List<Hero>
    suspend fun findHero(heroName: String?): ApiResponse
    suspend fun deleteHero(name: String): Boolean
    suspend fun addHero(hero: Hero): Hero?
}