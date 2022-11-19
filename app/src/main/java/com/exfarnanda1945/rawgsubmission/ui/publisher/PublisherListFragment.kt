package com.exfarnanda1945.rawgsubmission.ui.publisher

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.exfarnanda1945.rawgsubmission.databinding.FragmentPublisherListBinding
import com.exfarnanda1945.rawgsubmission.utils.BuilderSearchView
import com.exfarnanda1945.rawgsubmission.utils.HandlerApiClient
import kotlinx.coroutines.launch

class PublisherListFragment : Fragment() {

    private val mAdapter: PublisherListAdapter by lazy { PublisherListAdapter() }
    private var _binding: FragmentPublisherListBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: PublisherListViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPublisherListBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        BuilderSearchView.build(
            requireActivity(),
            viewLifecycleOwner,
            Lifecycle.State.RESUMED,
            object : BuilderSearchView.OnSubmitCallback {
                override fun onQuerySubmit(query: String) {
                    searchPub(query)
                }
            })

        lifecycleScope.launch {
            mViewModel.querySearch.observe(viewLifecycleOwner) {
                binding.keyResultPubGame.text = "Result from $it:"
            }
            mViewModel.listResult.collect {
                HandlerApiClient.handle(
                    it,
                    requireContext(),
                    object : HandlerApiClient.HandlerApiClientCallBack {
                        override fun onApiResponseLoading() {
                            handleUi(rv = false, progressBar = true, errorTv = false)
                        }

                        override fun onApiResponseFailed() {
                            handleUi(rv = false, progressBar = false, errorTv = true)

                        }

                        @SuppressLint("SetTextI18n")
                        override fun onGetDataSuccess() {
                            it.body.results.let { res ->
                                if (res?.size != 0) {
                                    binding.apply {
                                        rvFindPub.apply {
                                            adapter = mAdapter
                                            setHasFixedSize(true)
                                            layoutManager = GridLayoutManager(
                                                requireContext(),
                                                2,
                                                GridLayoutManager.VERTICAL, false
                                            )
                                            mAdapter.setData(res)
                                        }
                                        handleUi(rv = true, progressBar = false, errorTv = false)
                                    }
                                } else {
                                    binding.errorTvFind.text = "Data not found!"
                                    handleUi(rv = false, progressBar = false, errorTv = true)
                                }

                            }

                        }

                        override fun onGetDataFailed() {
                            handleUi(rv = false, progressBar = false, errorTv = true)
                        }
                    })
            }
        }

        return view
    }

    private fun searchPub(query: String) {
        mViewModel.setSearchPublisher(query)
    }

    private fun handleUi(rv: Boolean, progressBar: Boolean, errorTv: Boolean) {
        binding.apply {
            rvFindPub.isVisible = rv
            findProgressbar.isVisible = progressBar
            errorTvFind.isVisible = errorTv
        }
    }

}