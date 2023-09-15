package com.nn.tz.ui.main

import android.util.Log
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nn.tz.R
import com.nn.tz.core.base.BaseFragment
import com.nn.tz.core.network.UIState
import com.nn.tz.data.model.Product
import com.nn.tz.databinding.FragmentMainBinding
import com.nn.tz.ui.main.adapter.CategoriesAdapter
import com.nn.tz.ui.main.adapter.CategoryAdapter
import com.nn.tz.ui.main.viewModel.MainViewModel
import com.nn.tz.utils.Constants.ARGS_ASC
import com.nn.tz.utils.Constants.ARGS_DESC
import com.nn.tz.utils.Constants.ARGS_SEND_DATA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private val binding: FragmentMainBinding by viewBinding()
    private val categoriesAdapter = CategoriesAdapter(this::onClick)
    private val categoryAdapter = CategoryAdapter(this::onClickCategory)
    private val category = "jewelery"

    private fun onClick(product: String) {
        viewModel.changeCategories(product)
        viewModel.changeCategories.observe(viewLifecycleOwner) {
            viewModel.getCategory(it, ARGS_ASC)
        }
    }

    private fun onClickCategory(product: Product) {
        findNavController().navigate(R.id.detailFragment, bundleOf(ARGS_SEND_DATA to product))
    }

    override fun initialize() {
        binding.rvCategories.adapter = categoriesAdapter
        binding.rvCategory.adapter = categoryAdapter
    }

    override fun setupRequests() {
        viewModel.getCategory(category, ARGS_ASC)
        viewModel.getCategories()
    }

    override fun setupObservers() {
        viewModel.getCategories.collectUIState(
            state = { binding.progressBar.isVisible = it is UIState.Loading },
            onSuccess = {

                Log.e("OLOLO", "setupObservers: $it", )
                categoriesAdapter.submitList(it)
            }
        )

        viewModel.getCategory.collectUIState(
            state = { binding.progressBar.isVisible = it is UIState.Loading },
            onSuccess = {
                categoryAdapter.submitList(it)
            }
        )
    }

    override fun initClickListeners() {
        binding.btnSort.setOnClickListener {
            viewModel.changeCategories.observe(viewLifecycleOwner) {
                viewModel.getCategory(it, ARGS_DESC)
            }
        }


    }
}