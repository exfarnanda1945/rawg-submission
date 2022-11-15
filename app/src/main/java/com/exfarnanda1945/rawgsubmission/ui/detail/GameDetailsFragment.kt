package com.exfarnanda1945.rawgsubmission.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.exfarnanda1945.rawgsubmission.databinding.FragmentGameDetailBinding

class GameDetailsFragment : Fragment() {

    private var _binding: FragmentGameDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<GameDetailsFragmentArgs>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameDetailBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val data = args.gameDetails
        val scGameAdapter = ScreenshootGameAdapter()

        binding.apply {
            Glide.with(requireContext()).load(data.backgroundImage).into(detailGameImg)
            detailGameName.text = data.name
            detailGameReleaseDesc.text = data.released
            detailGameMetacriticDesc.text = data.metacritic.toString()
            detailGameRatingDesc.text = "${data.rating}/5"
            detailGamePlatformDesc.text =
                " " + data.parentPlatforms?.map { it?.platform?.name }?.joinToString()
            detailGameGenreDesc.text = " " + data.genres?.map { it?.name }?.joinToString()
            detailGameStoreDesc.text = " " + data.stores?.map { it?.store?.name }?.joinToString()
            detailGameMinSpecDesc.text =
                data.platforms?.find { it?.platform?.id == 4 }?.requirementsEn?.minimum ?: "-"
            detailGameReqSpecDesc.text =
                data.platforms?.find { it?.platform?.id == 4 }?.requirementsEn?.recommended ?: "-"
            detailGameRvSc.apply {
                adapter = scGameAdapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(
                    view.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                scGameAdapter.setData(data.shortScreenshots?.map { it?.image })
            }

        }
        return view
    }


}