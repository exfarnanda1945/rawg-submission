package com.exfarnanda1945.rawgsubmission.ui.genre

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.exfarnanda1945.rawgsubmission.databinding.FragmentGenreListItemBinding
import com.exfarnanda1945.rawgsubmission.utils.HandlerApiClient
import kotlinx.coroutines.launch

class GenreListFragment : Fragment() {
    private val mAdapter: GenreListAdapter by lazy { GenreListAdapter() }
    private var _binding: FragmentGenreListItemBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: GenreListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenreListItemBinding.inflate(inflater, container, false)
        val view = binding.root

        lifecycleScope.launch {
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
                                        rvFindGame.apply {
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

    private fun handleUi(rv: Boolean, progressBar: Boolean, errorTv: Boolean) {
        binding.apply {
            rvFindGame.isVisible = rv
            findProgressbar.isVisible = progressBar
            errorTvFind.isVisible = errorTv
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}