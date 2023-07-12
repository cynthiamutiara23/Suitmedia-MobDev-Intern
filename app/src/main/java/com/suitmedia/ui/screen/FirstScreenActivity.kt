package com.suitmedia.ui.screen

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.suitmedia.R
import com.suitmedia.databinding.ActivityFirstScreenBinding

class FirstScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val statusBarColor = ContextCompat.getColor(this, R.color.blue)
            window.statusBarColor = statusBarColor
        }

        initAction()
    }

    private fun initAction() {
        binding.apply {
            buttonCheck.setOnClickListener {
                val palindrome = edPalindrome.text.toString()
                if (palindrome.isNotEmpty()) {
                    val isPalindrome = checkPalindrome(palindrome)
                    if (isPalindrome) {
                        Snackbar.make(
                            root,
                            getString(R.string.is_palindrome),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else {
                        Snackbar.make(
                            root,
                            getString(R.string.not_palindrome),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    edPalindrome.error = getString(R.string.fill_palindrome)
                }
            }

            buttonNext.setOnClickListener {
                val name = edName.text.toString()
                if (name.isNotEmpty()) {
                    val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                    intent.putExtra(SecondScreenActivity.EXTRA_NAME, name)
                    startActivity(intent)
                } else {
                    edName.error = getString(R.string.fill_name)
                }
            }
        }
    }

    private fun checkPalindrome(palindrome: String): Boolean{
        val cleanPalindrome = palindrome.lowercase().replace(Regex("[^a-zA-Z0-9]"), "")
        return cleanPalindrome == cleanPalindrome.reversed()
    }
}