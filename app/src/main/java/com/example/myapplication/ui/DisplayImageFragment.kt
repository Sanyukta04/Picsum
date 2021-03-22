package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.api.Result
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentDisplayImageBinding
import com.example.myapplication.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayImageFragment : Fragment() {

    private lateinit var binding: FragmentDisplayImageBinding
    private val imageViewModel: ImageViewModel by viewModels()
    private lateinit var mImageAdapter:ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentDisplayImageBinding.inflate(inflater, container, false)
        setAdapter()
        getImageData()
        return binding.root
    }

    private fun setAdapter() {
        mImageAdapter=ImageAdapter()
        binding.rvImage.adapter=mImageAdapter
    }


    private fun getImageData() {
        imageViewModel.getImageData().observe(viewLifecycleOwner, { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    mImageAdapter.submitList(result.data)
                }
                Result.Status.ERROR -> {
                }
                Result.Status.LOADING -> {
                    result.message?.let { Log.i("Loading", it) }
                }
            }
        })
    }
}