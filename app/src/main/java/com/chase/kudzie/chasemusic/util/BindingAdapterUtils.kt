package com.chase.kudzie.chasemusic.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.chase.kudzie.chasemusic.R
import com.chase.kudzie.chasemusic.domain.model.Artist
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider

/**
 * File for handling all issues to do with binding.
 * */

@BindingAdapter("duration_update")
fun TextView.bindDurationUpdate(currentSongPos: Long) {
    this.text = makeReadableDuration(currentSongPos)
}

@BindingAdapter("duration_text")
fun TextView.bindDurationText(duration: Long) {
    this.text = makeReadableDuration(duration)
}

@BindingAdapter("play_pause_icon")
fun FloatingActionButton.bindPlayPauseIcon(isPlaying: Boolean) {
    if (isPlaying) {
        this.setImageResource(R.drawable.ic_pause_48)
    } else {
        this.setImageResource(R.drawable.ic_play_48)
    }
}

@BindingAdapter("play_pause_btn")
fun ImageButton.bindPlayPlauseIcon(isPlaying: Boolean) {
    if (isPlaying) {
        this.setImageResource(R.drawable.ic_pause_24)
    } else {
        this.setImageResource(R.drawable.ic_play_24)
    }
}

@BindingAdapter("seekbar_duration")
fun SeekBar.bindSeekBar(currentSongPos: Long) {
    val seekProgress = (currentSongPos / 1000).toInt()
    this.progress = seekProgress
}

@BindingAdapter("album_artwork")
fun ImageView.bindAlbumArt(albumId: Long) {
    Glide.with(this.context)
        .load(getAlbumArtUri(albumId))
        .placeholder(R.drawable.placeholder)
        .into(this)
}

@BindingAdapter("playlist_icon")
fun ImageView.bindPlaylistIcon(name: String) {
    Glide.with(this.context)
        .load("")
        .placeholder(R.drawable.ic_playlist)
        .into(this)
}


@SuppressLint("SetTextI18n")
@BindingAdapter("playlist_count")
fun TextView.setPlaylistCount(count: Int) {
    this.text = "$count songs"
}

@BindingAdapter("artist_artwork")
fun ImageView.bindArtistArtwork(artist: Artist) {
    Glide.with(this.context)
        .asBitmap()
        .load(artist)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                this@bindArtistArtwork.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                Glide.with(this@bindArtistArtwork.context)
                    .asBitmap()
                    .load(R.drawable.placeholder)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            this@bindArtistArtwork.setImageBitmap(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
            }
        })
}

@BindingAdapter("palette_bg")
fun View.setGradient(artist: Artist) {
    Glide.with(this.context)
        .asBitmap()
        .load(artist)
        .onlyRetrieveFromCache(true)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                setGradientOnView(this@setGradient, resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                Glide.with(this@setGradient.context)
                    .asBitmap()
                    .load(R.drawable.placeholder)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            setGradientOnView(this@setGradient, resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
            }
        })
}
