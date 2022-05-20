package com.example.routes

import com.example.dao.DAOFacade
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.searchHeroes(){
    val daoFacade: DAOFacade by inject()

    get("/anime/heroes/search") {
        val  name = call.request.queryParameters["name"]
        val apiResponse = daoFacade.findHero(heroName = name)
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}