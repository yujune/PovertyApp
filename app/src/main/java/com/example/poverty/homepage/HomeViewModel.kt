package com.example.poverty.homepage

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.poverty.Database.PostDatabaseDao
import com.example.poverty.Database.PostRepository
import com.example.poverty.Database.PostRoomDatabase
import com.example.poverty.Database.RecycleViewPost
import kotlinx.coroutines.*
//The Application class in Android is the base class within an Android app that contains all other components such as activities and services
class HomeViewModel(application: Application) : AndroidViewModel(application) {

    //The ViewModel maintains a reference to the repository to get data
    private val repository: PostRepository
    //LiveData gives us updated words when they change.
    val allPost: LiveData<List<RecycleViewPost>>

    var numLikes = 0

    //private var viewModelJob = Job()
    //private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //val posts = database.getAllPost().toString()

    //private var post = MutableLiveData<RecycleViewPost?>()


    init{
        //initializePost()
        //Gets reference to PostDatabaseDao from PostRoomDatabase to construct the correct PostRepository
        val postDatabaseDao = PostRoomDatabase.getInstance(application,viewModelScope).postDatabaseDao()
        repository = PostRepository(postDatabaseDao)
        //get all the post stored in the database
        allPost=repository.allPost

    }

    //ViewModel uses a coroutine to perform long running operations
    fun insertPost(post: RecycleViewPost) = viewModelScope.launch{
        repository.insert(post)
    }

    fun deleteAllPost() = viewModelScope.launch{
        repository.delete()
    }

    fun updatePost(post: RecycleViewPost) = viewModelScope.launch {
        repository.update(post)
    }

    /*fun addLikes(numLike:Int){
        numLikes = numLike + numLikes

    }*/

    /*private fun initializePost() {
        uiScope.launch {
            post.value = getPostFromDatabase()
        }
    }

    private suspend fun getPostFromDatabase(): RecycleViewPost? {
        return withContext(Dispatchers.IO) {
            var night = database.getPost()
            night
        }
    }

    /*fun insert(post:RecycleViewPost)= viewModelScope.launch{
        repository.insert(post)
    }*/

    fun onStartTracking() {
        uiScope.launch {
            val newPost = RecycleViewPost()
            insert(newPost)
            post.value = getPostFromDatabase()
        }
    }

    private suspend fun insert(posts: RecycleViewPost) {
        withContext(Dispatchers.IO) {
            database.insert(posts)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }*/
}