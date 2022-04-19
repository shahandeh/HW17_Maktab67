package com.example.hw17_maktab67.ui.showvideo

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.hw17_maktab67.R
import com.example.hw17_maktab67.databinding.FragmentShowVideoBinding
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ui.StyledPlayerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowVideoFragment : Fragment(R.layout.fragment_show_video), Player.Listener {

    private lateinit var binding: FragmentShowVideoBinding
    private lateinit var player: ExoPlayer
    private lateinit var playerView: StyledPlayerView
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentShowVideoBinding.bind(view)

        progressBar = binding.progressBarFragmentShowVideo

        setupPlayer()
        addMp4Player()

        if (savedInstanceState != null) {
            savedInstanceState.getInt("mediaItem").let {
                val seekTime = savedInstanceState.getLong("SeekTime")
                player.seekTo(seekTime)
                player.play()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("SeekTime", player.currentPosition)
        outState.putInt("mediaItem", player.currentMediaItemIndex)
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        playerView = binding.exoPlayerFragmentShowVideo
        playerView.player = player
        player.addListener(this)
    }

    private fun addMp4Player() {
        val mediaItem = MediaItem.fromUri("http://51.89.107.152/one.mp4")
        player.addMediaItem(mediaItem)
        player.prepare()
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                progressBar.visibility = View.VISIBLE
            }
            Player.STATE_READY -> {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }

}