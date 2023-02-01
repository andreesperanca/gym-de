package com.andreesperanca.gymde.di.modules

import com.andreesperanca.gymde.firebase.FirebaseDbService
import com.andreesperanca.gymde.firebase.FirebaseLoginAndRegisterService
import com.andreesperanca.gymde.repositories.*
import com.andreesperanca.gymde.ui.login_and_register.viewmodels.LoginAndRegisterViewModel
import com.andreesperanca.gymde.ui.main.viewmodels.CreateWorkoutViewModel
import com.andreesperanca.gymde.ui.main.viewmodels.MyWorkoutsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {


    single<FirebaseAuth> { FirebaseAuth.getInstance() }

    single<FirebaseFirestore> { FirebaseFirestore.getInstance() }

    single<FirebaseLoginAndRegisterService> {
        FirebaseLoginAndRegisterService(
            auth = get(),
            firebaseDb = get()
        )
    }

    single<LoginAndRegisterRepository> { LoginAndRegisterRepositoryImpl(firebaseService = get()) }

    single<FirebaseDbService> { FirebaseDbService(get(), get()) }

    single<MyWorkoutRepository> { MyWorkoutRepositoryImpl(get()) }

    single<CreateWorkoutRepository> { CreateWorkoutRepositoryImpl(get()) }

    viewModel<LoginAndRegisterViewModel>() {
        LoginAndRegisterViewModel(repository = get())
    }

    viewModel<CreateWorkoutViewModel>() {
        CreateWorkoutViewModel(repository = get())
    }

    viewModel<MyWorkoutsViewModel>() { MyWorkoutsViewModel(get()) }

}