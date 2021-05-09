package com.jaemin.features.presentation.mypage.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jaemin.features.presentation.gallery.view.GalleryFragment
import com.jaemin.features.presentation.mypage.ui.UserLikedPostsFragment
import com.jaemin.features.presentation.mypage.ui.UserWroteCommentPostsFragment
import com.jaemin.features.presentation.mypage.ui.UserWrotePostsFragment

class UserLogAdapter(fragment : Fragment, private val username : String) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                UserWrotePostsFragment().apply {
                    arguments = Bundle().apply {
                        putString("username", username)
                    }
                }
            }
            1 -> {
                UserWroteCommentPostsFragment().apply {
                    arguments = Bundle().apply {
                        putString("username", username)
                    }
                }
            }
            else -> {
                UserLikedPostsFragment().apply {
                    arguments = Bundle().apply {
                        putString("username", username)
                    }
                }
            }

        }    }
}