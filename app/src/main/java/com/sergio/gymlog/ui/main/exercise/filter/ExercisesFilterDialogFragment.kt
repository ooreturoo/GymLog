package com.sergio.gymlog.ui.main.exercise.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sergio.gymlog.databinding.FragmentExercisesFilterDialogBinding


class ExercisesFilterDialogFragment : DialogFragment() {

    lateinit var binding : FragmentExercisesFilterDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExercisesFilterDialogBinding.inflate(inflater, container, false)

        return binding.root
    }




}