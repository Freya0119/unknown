package com.example.mobile

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobile.databinding.AddItemFragmentBinding
import com.example.mobile.model.MoneyItem
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class AddItemFragment : Fragment() {
    private var _binding: AddItemFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        _binding = AddItemFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            addItemDateEditText.setOnClickListener { dateEditorClick() }
            addItemApplyButton.setOnClickListener { addItem() }
        }
    }

    private fun addItem() {
        val repayReference = FirebaseDatabase.getInstance().getReference("repay_log").push()
        repayReference.setValue(newMoneyItem()).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(
                    this.requireContext(),
                    "添加成功",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this.requireContext(),
                    "添加失敗, exception: ${it.exception}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun newMoneyItem(): MoneyItem? {
        return if (isCanAdded()) {
            val money = binding.addItemMoneyEditText.text.toString()
            val date = binding.addItemDateEditText.text.toString()
            val address = binding.addItemAddressEditText.text.toString()
            val memo = binding.addItemMemoEditText.text.toString()
            MoneyItem(money, "日期: $date", "地點: $address", "備註: $memo")
        } else null
    }

    private fun isCanAdded(): Boolean {
        return !(binding.addItemMoneyEditText.text.isNullOrEmpty())
    }

    private fun dateEditorClick() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this.requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                calendar.set(year, month, day)
                binding.addItemDateEditText.setText(
                    "${
                        SimpleDateFormat("yyyy/MM/dd").format(
                            calendar.time
                        )
                    }"
                )
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}