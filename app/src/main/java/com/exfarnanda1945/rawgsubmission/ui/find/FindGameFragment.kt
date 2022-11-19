package com.exfarnanda1945.rawgsubmission.ui.find

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exfarnanda1945.rawgsubmission.databinding.FragmentFindGameBinding
import com.exfarnanda1945.rawgsubmission.model.game_response.GameResponseResultsItem
import com.exfarnanda1945.rawgsubmission.ui.ListGameAdapter
import com.exfarnanda1945.rawgsubmission.utils.BuilderSearchView
import com.exfarnanda1945.rawgsubmission.utils.HandlerApiClient
import kotlinx.coroutines.launch

class FindGameFragment : Fragment() {

    private val mAdapter: FindGameAdapter by lazy { FindGameAdapter() }
    private var _binding: FragmentFindGameBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: FindGameViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindGameBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        BuilderSearchView.build(
            requireActivity(),
            viewLifecycleOwner,
            Lifecycle.State.RESUMED,
            object : BuilderSearchView.OnSubmitCallback {
                override fun onQuerySubmit(query: String) {
                    searchGame(query)
                }
            })

        lifecycleScope.launch {
            mViewModel.querySearch.observe(viewLifecycleOwner){
                binding.keyResultFindGame.text = "Result from $it:"
            }
            mViewModel.searchResult.collect {
                HandlerApiClient.handle(
                    it,
                    requireContext(),
                    object : HandlerApiClient.HandlerApiClientCallBack {
                        override fun onApiResponseLoading() {
                            handleUi(rv = false,progressBar = true,errorTv = false)
                        }

                        override fun onApiResponseFailed() {
                            handleUi(rv = false,progressBar = false,errorTv = true)

                        }
                        override fun onGetDataSuccess() {
                            it.body.results.let { res ->
                                if(res?.size != 0){
                                    mAdapter.setOnItemClickCallback(object :
                                        ListGameAdapter.IOnItemCallBack {
                                        override fun onItemClickCallback(data: GameResponseResultsItem) {
                                            findNavController().navigate(
                                                FindGameFragmentDirections.actionFindGameFragmentToGameDetail(
                                                    data
                                                )
                                            )
                                        }
                                    })

                                    binding.apply {
                                        rvFindGame.apply {
                                            adapter = mAdapter
                                            setHasFixedSize(true)
                                            layoutManager = LinearLayoutManager(
                                                requireContext(),
                                                LinearLayoutManager.VERTICAL,
                                                false
                                            )
                                            mAdapter.setData(res)
                                        }
                                        handleUi(rv = true,progressBar = false,errorTv = false)
                                    }
                                }else{
                                    binding.errorTvFind.text = "Data not found!"
                                    handleUi(rv = false,progressBar = false,errorTv = true)
                                }

                            }

                        }

                        override fun onGetDataFailed() {
                            handleUi(rv = false,progressBar = false,errorTv = true)
                        }
                    })
            }
        }


        return view
    }

    private fun searchGame(query: String) {
        mViewModel.apply {
            setSearchGame(query)
        }
    }

    private fun handleUi(rv:Boolean,progressBar:Boolean,errorTv:Boolean){
        binding.apply {
            rvFindGame.isVisible = rv
            findGameProgressbar.isVisible = progressBar
            errorTvFind.isVisible  = errorTv
        }
    }


}