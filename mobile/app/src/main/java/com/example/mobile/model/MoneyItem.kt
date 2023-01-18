package com.example.mobile.model

class MoneyItem(
    val money: String,
    val date: String = "日期:",
    val address: String = "地點:",
    val memo: String = "備註:"
) {
    constructor() : this("", "", "", "")

    fun getMoneyStr(): String {
        return "金額: $money"
    }
}