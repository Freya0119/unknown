package com.example.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.mobile.databinding.ActivityLogRoomBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_log_room)
        binding = ActivityLogRoomBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        pay(100)
    }

    //TODO: not work
    private fun pay(money: Int) {
        val total = money
        changeMoneyText(total.toString())
    }

    private fun changeMoneyText(moneyString: String) {
        binding.nowMoneyTextView.text = moneyString
    }
}