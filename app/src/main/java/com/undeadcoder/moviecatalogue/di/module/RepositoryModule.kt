package com.undeadcoder.moviecatalogue.di.module

import com.undeadcoder.moviecatalogue.data.source.MovieRepository
import org.koin.dsl.module

val repoModule = module {
    single { MovieRepository(get()) }
}