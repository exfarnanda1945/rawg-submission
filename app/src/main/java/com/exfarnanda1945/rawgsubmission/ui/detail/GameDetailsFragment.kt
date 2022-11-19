package com.exfarnanda1945.rawgsubmission.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.exfarnanda1945.rawgsubmission.databinding.FragmentGameDetailBinding
import com.exfarnanda1945.rawgsubmission.model.game_detail_response.GameDetailDevelopersItem
import com.exfarnanda1945.rawgsubmission.model.game_detail_response.GameDetailGenresItem
import com.exfarnanda1945.rawgsubmission.model.game_detail_response.GameDetailParentPlatformsItem
import com.exfarnanda1945.rawgsubmission.model.game_detail_response.GameDetailPublishersItem
import com.exfarnanda1945.rawgsubmission.model.game_screenshots.GameScreenshotsResultsItem
import com.exfarnanda1945.rawgsubmission.utils.HandlerApiClient
import com.exfarnanda1945.rawgsubmission.utils.loadImage

class GameDetailsFragment : Fragment() {

    private var _binding: FragmentGameDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<GameDetailsFragmentArgs>()
    private val mViewModel by viewModels<GameDetailsViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameDetailBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val data = args.gameDetails

        handleDataDetailGame(data.id)
        handleDataScreenshotsGame(data.id)
        return view
    }

    private fun handleDataScreenshotsGame(id: Int?) {
        mViewModel.screenshotsGame.observe(viewLifecycleOwner){
            HandlerApiClient.handle(it,requireContext(),object :HandlerApiClient.HandlerApiClientCallBack{
                override fun onApiResponseLoading() {
                    binding.apply {
                        gameDetailProgressbar.isVisible = true
                        gameDetailWrapper.isVisible = false
                        errorGameDetailWrapper.isVisible = false
                    }
                }

                override fun onApiResponseFailed() {
                    binding.apply {
                        gameDetailProgressbar.isVisible = false
                        gameDetailWrapper.isVisible = false
                        errorGameDetailWrapper.isVisible = true
                    }
                }

                override fun onGetDataSuccess() {
                    it.body.let { res ->
                        setupRvScreenshots(res.results)
                        binding.apply {
                            gameDetailProgressbar.isVisible = false
                            gameDetailWrapper.isVisible = true
                            errorGameDetailWrapper.isVisible = false
                        }
                    }
                }

                override fun onGetDataFailed() {
                    binding.apply {
                        gameDetailProgressbar.isVisible = false
                        gameDetailWrapper.isVisible = false
                        errorGameDetailWrapper.isVisible = true
                    }
                }
            })
        }
        if (id != null) {
            mViewModel.getScreenshots(id)
        }
    }

    private fun setupRvScreenshots(results: List<GameScreenshotsResultsItem?>?) {
        val screenshotsAdapter = ScreenshotGameAdapter()
        binding.rvScreenshotsGame.apply {
            adapter = screenshotsAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false)
            screenshotsAdapter.setData(results)
        }
    }

    private fun handleDataDetailGame(id: Int?) {
        mViewModel.detailGameResult.observe(viewLifecycleOwner) {
            HandlerApiClient.handle(
                it,
                requireContext(),
                object : HandlerApiClient.HandlerApiClientCallBack {
                    override fun onApiResponseLoading() {
                        binding.apply {
                            gameDetailProgressbar.isVisible = true
                            gameDetailWrapper.isVisible = false
                            errorGameDetailWrapper.isVisible = false
                        }
                    }

                    override fun onApiResponseFailed() {
                        binding.apply {
                            gameDetailProgressbar.isVisible = false
                            gameDetailWrapper.isVisible = false
                            errorGameDetailWrapper.isVisible = true
                        }
                    }

                    override fun onGetDataSuccess() {
                        it.body.let { res ->
                            binding.apply {
                                loadImage(requireContext(),res.backgroundImage,imgDetailGame)
                                gameDetailName.text = res.name
                                gameRatingDetail.text = res.rating.toString()
                                setupRvPlatform(res.parentPlatforms)
                                setupRvGenres(res.genres)
                                releaseDetailGame.text = res.released ?: "-"
                                aboutGameDescription.text = res.descriptionRaw
                                setupRvDeveloper(res.developers)
                                setupPublisher(res.publishers)
                                gameDetailProgressbar.isVisible = false
                                gameDetailWrapper.isVisible = true
                                errorGameDetailWrapper.isVisible = false
                            }
                        }
                    }

                    override fun onGetDataFailed() {
                        binding.apply {
                            gameDetailProgressbar.isVisible = false
                            gameDetailWrapper.isVisible = false
                            errorGameDetailWrapper.isVisible = true
                        }
                    }
                })
        }
        if (id != null) {
            mViewModel.getDetailResult(id)
        }
    }

    private fun setupPublisher(publishers: List<GameDetailPublishersItem?>?) {
        val publishersAdapter = PublisherGameDetailAdapter()
        binding.rvPublisher.apply {
            adapter = publishersAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false)
            publishersAdapter.setData(publishers)
        }

    }

    private fun setupRvDeveloper(developers: List<GameDetailDevelopersItem?>?) {
        val developerAdapter = DeveloperGameDetailAdapter()
        binding.rvDevelopers.apply {
            adapter = developerAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false)
            developerAdapter.setData(developers)
        }

    }

    private fun setupRvGenres(genres: List<GameDetailGenresItem?>?) {
        val genreAdapter = GenresGameDetailAdapter()
        binding.rvDetailGenre.apply {
            adapter = genreAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        genreAdapter.setData(genres)
        }

    }

    private fun setupRvPlatform(parentPlatforms: List<GameDetailParentPlatformsItem?>?) {
        val platformAdapter = PlatformGameAdapter()
        binding.rvPlatformDetailGame.apply {
            adapter = platformAdapter
            setHasFixedSize(true)
            layoutManager =
                GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
            platformAdapter.setData(parentPlatforms)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}