package com.example.mobile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile.adapter.RepayAdapter
import com.example.mobile.databinding.RepayShowListBinding
import com.example.mobile.model.MoneyItem
import com.example.mobile.viewmodel.RepayViewModel
import com.example.mobile.viewmodel.RepayViewModelFactory
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RepayRecyclerViewShowFragment : Fragment() {
    private var _binding: RepayShowListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    private val viewModel: RepayViewModel by viewModels()

    private var adapter = RepayAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)

        _binding = RepayShowListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView = repayLogRecyclerview
        }
        recyclerView.adapter = adapter

        viewModel.allItems.observe(this.viewLifecycleOwner) { adapter.submitList(it) }
        initLog()
    }

    private fun initLog() {
        val database = FirebaseDatabase.getInstance().getReference("repay_log")
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<MoneyItem>()
                snapshot.children.forEach {
                    list.add(it.getValue(MoneyItem::class.java)!!)
                }
                viewModel.updateItems(list)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}