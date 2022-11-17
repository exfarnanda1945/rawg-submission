package com.exfarnanda1945.rawgsubmission.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exfarnanda1945.rawgsubmission.R
import com.exfarnanda1945.rawgsubmission.databinding.FragmentHomeBinding
import com.exfarnanda1945.rawgsubmission.model.game_response.GameResponseResultsItem
import com.exfarnanda1945.rawgsubmission.ui.ListGameAdapter
import com.exfarnanda1945.rawgsubmission.utils.HandlerApiClient
import kotlinx.coroutines.launch
import me.relex.circleindicator.CircleIndicator


class HomeFragment : Fragment() {
    private lateinit var indicator: CircleIndicator

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.apply {
            findGameBtn.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_findGameFragment)
            }
        }

        lifecycleScope.launch {
            // Slider
            launch {
                mViewModel.gameListBanner.collect {
                    HandlerApiClient.handle(
                        it,
                        requireContext(),
                        object : HandlerApiClient.HandlerApiClientCallBack {
                            override fun onApiResponseLoading() {
                                handleUi(
                                    PartUi.SLIDER_BANNER,
                                    rv = false,
                                    progressBar = true,
                                    errorTv = false
                                )
                            }

                            override fun onApiResponseFailed() {
                                handleUi(
                                    PartUi.SLIDER_BANNER,
                                    rv = false,
                                    progressBar = false,
                                    errorTv = true
                                )
                            }

                            override fun onGetDataSuccess() {
                                it.body.results.let { res ->
                                    val viewPagerAdapter = ImageSlideBannerAdapter(
                                        requireContext(),
                                        res
                                    )
                                    binding.viewpagerSliderBanner.adapter = viewPagerAdapter
                                    indicator = binding.ciIndicatorSlider
                                    indicator.setViewPager(binding.viewpagerSliderBanner)
                                }
                                handleUi(
                                    PartUi.SLIDER_BANNER,
                                    rv = true,
                                    progressBar = false,
                                    errorTv = false
                                )
                            }

                            override fun onGetDataFailed() {
                                handleUi(
                                    PartUi.SLIDER_BANNER,
                                    rv = false,
                                    progressBar = false,
                                    errorTv = true
                                )
                            }

                        })
                }
            }
            // Goty
            launch {
                mViewModel.gameBestOfTheYear.collect {
                    HandlerApiClient.handle(
                        it,
                        requireContext(),
                        object : HandlerApiClient.HandlerApiClientCallBack {
                            override fun onApiResponseLoading() {
                                handleUi(
                                    PartUi.GOTY,
                                    rv = false,
                                    progressBar = true,
                                    errorTv = false
                                )
                            }

                            override fun onApiResponseFailed() {
                                handleUi(
                                    PartUi.GOTY,
                                    rv = false,
                                    progressBar = false,
                                    errorTv = true
                                )
                            }

                            override fun onGetDataSuccess() {
                                it.body.results.let { res ->
                                    val gotyAdapter = GotyAdapter()
                                    gotyAdapter.setOnItemClickCallback(object :
                                        GotyAdapter.IOnItemCallBack {
                                        override fun onItemClickCallback(data: GameResponseResultsItem) {
                                            findNavController().navigate(
                                                HomeFragmentDirections.actionHomeFragmentToGameDetail(
                                                    data
                                                )
                                            )
                                        }
                                    })
                                    binding.rvGoty.apply {
                                        adapter = gotyAdapter
                                        setHasFixedSize(true)
                                        layoutManager = LinearLayoutManager(
                                            view.context,
                                            LinearLayoutManager.HORIZONTAL,
                                            false
                                        )
                                        gotyAdapter.setData(res)
                                    }
                                }
                                handleUi(
                                    PartUi.GOTY,
                                    rv = true,
                                    progressBar = false,
                                    errorTv = false
                                )
                            }

                            override fun onGetDataFailed() {
                                handleUi(
                                    PartUi.GOTY,
                                    rv = false,
                                    progressBar = false,
                                    errorTv = true
                                )
                            }
                        })
                }
            }
            // Latest
            launch {
                mViewModel.gameLatestRelease.collect {
                    HandlerApiClient.handle(
                        it,
                        requireContext(),
                        object : HandlerApiClient.HandlerApiClientCallBack {
                            override fun onApiResponseLoading() {
                                handleUi(
                                    PartUi.LATEST_RELEASE,
                                    rv = false,
                                    progressBar = true,
                                    errorTv = false
                                )
                            }

                            override fun onApiResponseFailed() {
                                handleUi(
                                    PartUi.LATEST_RELEASE,
                                    rv = false,
                                    progressBar = false,
                                    errorTv = true
                                )
                            }

                            override fun onGetDataSuccess() {
                                it.body.results.let { res ->
                                    val latestGameAdapter = LatestReleaseAdapter()

                                    latestGameAdapter.setOnItemClickCallback(object :
                                        ListGameAdapter.IOnItemCallBack {
                                        override fun onItemClickCallback(data: GameResponseResultsItem) {
                                            findNavController().navigate(
                                                HomeFragmentDirections.actionHomeFragmentToGameDetail(
                                                    data
                                                )
                                            )
                                        }

                                    })

                                    binding.rvLatestRelease.apply {
                                        adapter = latestGameAdapter
                                        setHasFixedSize(true)
                                        layoutManager = LinearLayoutManager(
                                            view.context,
                                            LinearLayoutManager.VERTICAL,
                                            false
                                        )
                                        latestGameAdapter.setData(res)
                                    }
                                }

                                handleUi(
                                    PartUi.LATEST_RELEASE,
                                    rv = true,
                                    progressBar = false,
                                    errorTv = false
                                )
                            }

                            override fun onGetDataFailed() {
                                handleUi(
                                    PartUi.LATEST_RELEASE,
                                    rv = false,
                                    progressBar = false,
                                    errorTv = true
                                )
                            }
                        })

                }
            }


        }

        return view
    }

    private fun handleUi(key: PartUi, rv: Boolean, progressBar: Boolean, errorTv: Boolean) {
        when (key) {
            PartUi.SLIDER_BANNER -> binding.apply {
                imageSlider.isVisible = rv
                sliderProgressBar.isVisible = progressBar
                errorTvSlider.isVisible = errorTv
            }
            PartUi.GOTY -> binding.apply {
                rvGoty.isVisible = rv
                gotyProgressBar.isVisible = progressBar
                errorTvGoty.isVisible = errorTv
            }
            PartUi.LATEST_RELEASE -> binding.apply {
                rvLatestRelease.isVisible = rv
                latestGameProgressbar.isVisible = progressBar
                errorTvLatest.isVisible = errorTv
            }
        }
    }


    enum class PartUi {
        SLIDER_BANNER, GOTY, LATEST_RELEASE
    }
}