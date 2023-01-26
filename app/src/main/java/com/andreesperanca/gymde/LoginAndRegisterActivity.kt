package com.andreesperanca.gymde

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andreesperanca.gymde.databinding.ActivityLoginAndRegisterBinding

class LoginAndRegisterActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginAndRegisterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}