package com.emotiontracker.presentation.di

import androidx.appcompat.app.AppCompatActivity
import com.emotiontracker.data.repository.EmotionRepository
import com.emotiontracker.domain.EmotionInteractor
import com.emotiontracker.domain.RepositoryInterface
import com.emotiontracker.presentation.activity.MainActivity
import com.emotiontracker.presentation.calendar.CalendarViewModel
import com.emotiontracker.presentation.choice.ChoiceViewModel
import com.emotiontracker.presentation.navigation.FragmentNavigator
import com.emotiontracker.presentation.note.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val interactorModule = module {

    single <RepositoryInterface> { EmotionRepository.get() }

    factory {
        EmotionInteractor(repositoryInterface = get())
    }
}

val presentationModule = module {

    factory {
        FragmentNavigator(activity = MainActivity() as AppCompatActivity)
    }

    viewModel {
        ChoiceViewModel(fragmentNavigator = get())
    }

    viewModel {
        NoteViewModel(
            emotionInteractor = get(),
            fragmentNavigator = get()
        )
    }

    viewModel {
        CalendarViewModel(
            emotionInteractor = get(),
            fragmentNavigator = get()
        )
    }
}
