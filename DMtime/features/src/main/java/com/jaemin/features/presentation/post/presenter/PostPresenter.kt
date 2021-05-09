package com.jaemin.features.presentation.post.presenter

import com.jaemin.features.domain.entity.Post
import com.jaemin.features.domain.entity.Post.Companion.REACTION_DISLIKE
import com.jaemin.features.domain.entity.Post.Companion.REACTION_LIKE
import com.jaemin.features.domain.entity.Post.Companion.REACTION_NONE
import com.jaemin.features.domain.usecase.*
import com.jaemin.features.presentation.post.contract.PostContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver

class PostPresenter(
    private val getPostUseCase: GetPostUseCase,
    private val postLikeUseCase: PostLikeUseCase,
    private val postDislikeUseCase: PostDislikeUseCase,
    private val postView: PostContract.View
) : PostContract.Presenter {
    private val postId by lazy {
        postView.getPostId()
    }
    private var likes = 0
    private var dislikes = 0
    private var myReaction = "none"

    override fun onCreate() {
        getPostUseCase.execute(postId, object : DisposableSingleObserver<Post>() {
            override fun onSuccess(post: Post) {
                postView.setPost(post)
                likes = post.numberOfLikes
                dislikes = post.numberOfDislikes
                myReaction = post.myReaction
                if (post.myReaction == REACTION_LIKE)
                    postView.disablePostLikeButton()
                if(post.myReaction == REACTION_DISLIKE)
                    postView.disablePostDislikeButton()

            }

            override fun onError(e: Throwable) {
                postView.showErrorScreen()
            }
        })

    }

    override fun onClickLikeButton() {
        postLikeUseCase.execute(Pair(postId, myReaction), object : DisposableSingleObserver<Unit>() {
            override fun onSuccess(t: Unit) {
                myReaction = when (myReaction) {
                    REACTION_LIKE -> {
                        postView.setPostLike(--likes)
                        postView.enablePostLikeButton()
                        REACTION_NONE
                    }
                    REACTION_DISLIKE -> {
                        postView.setPostDislike(--dislikes)
                        postView.enablePostDislikeButton()
                        postView.setPostLike(++likes)
                        postView.disablePostLikeButton()
                        REACTION_LIKE
                    }
                    else -> {
                        postView.disablePostLikeButton()
                        postView.setPostLike(++likes)
                        REACTION_LIKE
                    }
                }



            }
            override fun onError(e: Throwable) {
                e.printStackTrace()
                postView.showErrorScreen()
            }
        })

    }

    override fun onClickDislikeButton() {
        postDislikeUseCase.execute(Pair(postId, myReaction), object : DisposableSingleObserver<Unit>() {
            override fun onSuccess(t: Unit) {
                myReaction = when (myReaction) {
                    REACTION_LIKE -> {
                        postView.setPostLike(--likes)
                        postView.enablePostLikeButton()
                        postView.setPostDislike(++dislikes)
                        postView.disablePostDislikeButton()
                        REACTION_DISLIKE
                    }
                    REACTION_DISLIKE -> {
                        postView.setPostDislike(--dislikes)
                        postView.enablePostDislikeButton()
                        REACTION_NONE
                    }
                    else -> {
                        postView.setPostDislike(++dislikes)
                        postView.disablePostDislikeButton()
                        REACTION_DISLIKE
                    }
                }
            }
            override fun onError(e: Throwable) {
                e.printStackTrace()
                postView.showErrorScreen()
            }
        })
    }

    override fun onClickPostImage(position: Int) {
        postView.showImage(position)
    }


}