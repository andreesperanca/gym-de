package com.andreesperanca.gymde.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module


val appModules = module {


    single <FirebaseAuth> { FirebaseAuth.getInstance() }

    single <FirebaseFirestore> { FirebaseFirestore.getInstance() }


}