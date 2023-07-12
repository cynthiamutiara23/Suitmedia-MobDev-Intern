package com.suitmedia.ui.screen

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.suitmedia.R
import com.suitmedia.databinding.ActivitySecondScreenBinding

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var name: String
    private lateinit var user: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val statusBarColor = ContextCompat.getColor(this, R.color.blue)
            window.statusBarColor = statusBarColor
        }

        initAction()
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            user = data?.getStringExtra(EXTRA_USER).toString()
            if (user.isNotEmpty()) {
                binding.tvSelectedUser.text = user
            } else {
                binding.tvSelectedUser.text = getString(R.string.empty_user)
            }
        }
    }

    private fun initAction() {
        name = intent.getStringExtra(EXTRA_NAME).toString()

        binding.apply {
            ivBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            tvUsername.text = name

            buttonChoose.setOnClickListener {
                val intent = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
                resultLauncher.launch(intent)
            }
        }
    }

    companion object {
        const val EXTRA_NAME = "Name"
        const val EXTRA_USER = "User"
    }
}