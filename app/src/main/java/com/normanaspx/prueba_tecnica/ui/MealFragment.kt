package com.normanaspx.prueba_tecnica.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.normanaspx.prueba_tecnica.R
import com.normanaspx.prueba_tecnica.databinding.FragmentMealBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MealFragment : Fragment() {

    private val viewModel by activityViewModels<MealViewModel>()
    private var _binding: FragmentMealBinding?=null
    private val binding get() = _binding!!
    private lateinit var customAdapter : CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMealBinding.bind(view)

        viewModel.meals.observe(
            viewLifecycleOwner
        ){
            customAdapter = CustomAdapter(it, context)

            binding.apply {
                rvMeals.setHasFixedSize(true)
                rvMeals.adapter = customAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}