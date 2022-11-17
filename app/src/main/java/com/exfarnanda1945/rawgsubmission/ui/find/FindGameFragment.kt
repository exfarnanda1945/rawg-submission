package com.exfarnanda1945.rawgsubmission.ui.find

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exfarnanda1945.rawgsubmission.R
import com.exfarnanda1945.rawgsubmission.databinding.FragmentFindGameBinding
import com.exfarnanda1945.rawgsubmission.model.game_response.GameResponseResultsItem
import com.exfarnanda1945.rawgsubmission.ui.ListGameAdapter
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

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)

                val search = menu.findItem(R.id.search_menu)
                val searchView = search.actionView as SearchView

                searchView.apply {
                    imeOptions = EditorInfo.IME_ACTION_SEARCH
                    isSubmitButtonEnabled =true
                    queryHint = "Search Here"
                    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        @SuppressLint("SetTextI18n")
                        override fun onQueryTextSubmit(query: String): Boolean {
                            searchGame(query)
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            return false
                        }
                    })
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }


        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

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

                        @SuppressLint("SetTextI18n")
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