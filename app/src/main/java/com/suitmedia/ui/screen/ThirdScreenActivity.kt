package com.suitmedia.ui.screen

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.databinding.ActivityThirdScreenBinding
import com.suitmedia.service.ViewModelFactory
import com.suitmedia.ui.MainViewModel
import com.suitmedia.ui.adapter.UserAdapter

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        initAction()
    }

    private fun initAction() {
        binding.apply {
            ivBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            rvUser.layoutManager = LinearLayoutManager(this@ThirdScreenActivity)

            val adapter = UserAdapter()
            rvUser.adapter = adapter
            viewModel.users.observe(this@ThirdScreenActivity) {
                if (it != null) {
                    adapter.submitData(lifecycle, it)
                } else {
                    rvUser.visibility = View.GONE
                    tvEmptyUser.visibility = View.VISIBLE
                }
            }

            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = false
                adapter.refresh()
                adapter.notifyDataSetChanged()
            }
        }
    }
}
