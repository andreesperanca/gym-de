package com.andreesperanca.gymde.di.modules

import com.andreesperanca.gymde.firebase.FirebaseDbService
import com.andreesperanca.gymde.firebase.FirebaseLoginAndRegisterService
import com.andreesperanca.gymde.repositories.*
import com.andreesperanca.gymde.repositories.interfaces.*
import com.andreesperanca.gymde.ui.login_and_register.viewmodels.LoginAndRegisterViewModel
import com.andreesperanca.gymde.ui.main.viewmodels.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoriesModules = module {
    single<CreateExerciseRepository> { CreateExerciseRepositoryImpl(get()) }
    single<LoginAndRegisterRepository> { LoginAndRegisterRepositoryImpl(firebaseService = get()) }
    single<MyWorkoutRepository> { MyWorkoutRepositoryImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<MyAccountRepository> { MyAccountRepositoryImpl(get()) }
    single<CreateWorkoutRepository> { CreateWorkoutRepositoryImpl(get()) }
    single<WorkoutDetailsRepository> { WorkoutDetailsRepositoryImpl(get()) }
}

val firebaseModules = module {
    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<FirebaseStorage> { FirebaseStorage.getInstance() }
    single<FirebaseFirestore> { FirebaseFirestore.getInstance() }
    single<FirebaseLoginAndRegisterService> {
        FirebaseLoginAndRegisterService(
            auth = get(),
            firebaseDb = get()
        )
    }
    single<FirebaseDbService> { FirebaseDbService(get(), get(), get()) }
}

val viewModelModules = module {
    viewModel<LoginAndRegisterViewModel>() { LoginAndRegisterViewModel(repository = get()) }
    viewModel<CreateWorkoutViewModel>() { CreateWorkoutViewModel(repository = get()) }
    viewModel<MyWorkoutsViewModel>() { MyWorkoutsViewModel(get()) }
    viewModel<WorkoutDetailsViewModel>() { WorkoutDetailsViewModel(get()) }
    viewModel<NewExerciseViewModel>() { NewExerciseViewModel(get()) }
    viewModel<HomeViewModel>() { HomeViewModel(get()) }
    viewModel<MyAccountViewModel>() { MyAccountViewModel(get()) }
}
