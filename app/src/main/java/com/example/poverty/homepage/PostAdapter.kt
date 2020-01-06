package com.example.poverty.homepage

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.FutureTarget
import com.bumptech.glide.request.RequestOptions
import com.example.poverty.Database.RecycleViewPost
import com.example.poverty.MainActivity
import com.example.poverty.R
import com.squareup.picasso.Picasso
import java.io.File
import kotlin.coroutines.coroutineContext
import android.util.Log
import android.widget.*
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.ViewPager
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.layout_post_list_item.view.*


class PostAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var posts = emptyList<RecycleViewPost>() // Cached copy of words
    //private lateinit var mListerner :OnItemClickListener
    var onDonateButtonClick: ((RecycleViewPost) -> Unit)? = null
    var onLikeButtonClick: ((RecycleViewPost) -> Unit)? = null
    var onUnLikeButtonClick :((RecycleViewPost) -> Unit)? = null

    interface OnItemClickListener{
        fun onUpdateClick(position: Int)
    }

    /*fun setOnItemClickListener(listener:OnItemClickListener){
        mListerner = listener

    }*/

    inner class PostViewHolder(itemView: View,var posted:RecycleViewPost?=null) : RecyclerView.ViewHolder(itemView) {


        val buttonShares: Button = itemView.findViewById(R.id.buttonShare)
        val buttonDonate: Button = itemView.findViewById(R.id.buttonDonate)
        val likeButton: LikeButton = itemView.findViewById(R.id.thumb_button)

        //val buttonToDeatails:Button =itemView.findViewById(R.id.)

        init{
            itemView.setOnClickListener {
                print("Testing123")
                val intent = Intent(itemView.context,PostDetails::class.java)
                //val options : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity)
                intent.putExtra(POST_TITLE_KEY,posted?.posttitle)
                intent.putExtra(POST_IMAGE_KEY,posted?.postImg)
                intent.putExtra(POST_DESC_KEY,posted?.postdesc)
                intent.putExtra(POST_DATE_KEY,posted?.postDate)
                itemView.context.startActivity(intent)
                Animatoo.animateZoom(it.context)
            }
            buttonShares.setOnClickListener {
                onClick(it)
            }

            buttonDonate.setOnClickListener {
                onDonateButtonClick?.invoke(posts.get(position))
                posts.get(position).postLikes
            }


            likeButton.setOnLikeListener(object: OnLikeListener {
                override fun liked(likeButton: LikeButton?) {
                    Toast.makeText(itemView.context, "Liked!!!", Toast.LENGTH_SHORT).show()
                    onLikeButtonClick?.invoke(posts.get(position))
                    //var position:Int = adapterPosition
                    //listener.onUpdateClick(position)


                }

                override fun unLiked(likeButton: LikeButton?) {
                    Toast.makeText(itemView.context, "Unliked!!!", Toast.LENGTH_SHORT).show()
                    onUnLikeButtonClick?.invoke(posts.get(position))
                }

            })

        }

        fun onClick(view:View){

            Log.d("TAG", "Button is clicked")
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,posted?.postsubtitle)
            intent.type="text/plain"

            view.context.startActivity(Intent.createChooser(intent,"Share to : "))
        }
        val postTitle: TextView = itemView.findViewById(R.id.post_title)
        val postSubtitle: TextView = itemView.findViewById(R.id.post_desc)
        val postImage: ImageView = itemView.findViewById(R.id.post_image)
        val postLikes :TextView = itemView.findViewById(R.id.textViewLikeNumber)



        /*fun bind(post: RecycleViewPost){
            postTitle.setText(post.posttitle)
            postDesc.setText(post.postdesc)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            GlideApp.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(post.postImg)
                .into(postImage)
        }*/
    }

    fun PostAdapter(exampleList:ArrayList<RecycleViewPost> ){posts = exampleList}

    //telling recycle view to create different view holder in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        /*return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_post_list_item,parent,false)
        )*/
        val itemView = inflater.inflate(R.layout.layout_post_list_item, parent, false)
        //return PostViewHolder(itemView,null,mListerner)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        //create a variable for one item at a given position in the data.
        val current = posts.get(position)
        val title = current.postID.toString()
        Log.d("TAG", title)
        holder.postTitle.text = current.posttitle
        holder.postSubtitle.text = current.postsubtitle
        holder.postLikes.text = current.postLikes.toString()

        val thumbnailImage = holder?.itemView?.post_image
        Picasso.with(holder.itemView.context).load(current.postImg).into(thumbnailImage)
        /*when(holder){
            is PostViewHolder->{
                holder.bind(posts.get(position))
            }
        }*/

        holder?.posted = current

    }

    internal fun setPosts(posts: List<RecycleViewPost>) {
        this.posts = posts
        // When notifyDataSetChanged() is called, the RecyclerView redraws the whole list, not just the changed items
        notifyDataSetChanged()
    }

    //how many items inside your list
    override fun getItemCount() = posts.size

    companion object{
        const val POST_TITLE_KEY="com.example.poverty.homepage.POSTTITLE"
        const val POST_IMAGE_KEY="com.example.poverty.homepage.POSTIMAGE"
        const val POST_DESC_KEY="com.example.poverty.homepage.POSTDESC"
        const val POST_DATE_KEY= "com.example.poverty.homepage.POSTDATE"
    }

}



