package com.example.di

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.repository.HeroRepository
import com.example.repository.HeroRepositoryImpl
import org.koin.dsl.module

// Singleton object for dependency injection
val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
    single<DAOFacade> {
        DAOFacadeImpl()
    }
}