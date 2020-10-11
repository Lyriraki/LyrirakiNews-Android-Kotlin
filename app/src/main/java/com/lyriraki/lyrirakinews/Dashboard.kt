package com.lyriraki.lyrirakinews

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.lyriraki.lyrirakinews.adapter.ListAuthorNewsAdapter
import com.lyriraki.lyrirakinews.adapter.ListNewsAdapter
import com.lyriraki.lyrirakinews.helper.AuthorData
import com.lyriraki.lyrirakinews.helper.NewsData
import com.lyriraki.lyrirakinews.model.Author
import com.lyriraki.lyrirakinews.model.News
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.util.*


class Dashboard : AppCompatActivity(){
    private lateinit var rvNews: RecyclerView
    private lateinit var rvByAuthor: RecyclerView
    private var list: ArrayList<News> = arrayListOf()
    private var listAuthor: ArrayList<Author> = arrayListOf()
    private var displayList: ArrayList<News> = arrayListOf()

    private var title: String = "LyrirakiNews"
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        rvNews = findViewById(R.id.rv_news)
        rvByAuthor = findViewById(R.id.rv_byauthor)

        rvNews.setHasFixedSize(true)
        list.addAll(NewsData.listData)
        listAuthor.addAll(AuthorData.listDataAuthor)
        displayList.addAll(list)
        showRecyclerList()
    }

//    Controller Show
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.about -> {
                val moveAboutActivity = Intent(this@Dashboard, AboutActivity::class.java)
                startActivity(moveAboutActivity)
            }
        }
        setActionBarTitle(title)
    }


//    Control Recycle
    @SuppressLint("WrongConstant")
    private fun showRecyclerList() {
        rv_news.layoutManager = LinearLayoutManager(this)
        val listNewsAdapter = ListNewsAdapter(displayList)
        rv_news.adapter = listNewsAdapter

        rvByAuthor.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
        val listAuthorNewsAdapter = ListAuthorNewsAdapter(listAuthor)
        rvByAuthor.adapter = listAuthorNewsAdapter

        listNewsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: News) {
                showSelectedNews(data)
            }
        })

        listAuthorNewsAdapter.setOnItemClickCallback(object: ListAuthorNewsAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Author) {
                showByAuthor(data)
            }
        })
    }

//    Control Click
    private fun showSelectedNews(news: News) {
        val moveToDetail = Intent(this@Dashboard, DetailNews::class.java)
        moveToDetail.putExtra(DetailNews.EXTRA_judul, news.judul)
        moveToDetail.putExtra(DetailNews.EXTRA_author, news.author)
        moveToDetail.putExtra(DetailNews.EXTRA_des, news.des)
        moveToDetail.putExtra(DetailNews.EXTRA_photo, news.photo)
        moveToDetail.putExtra(DetailNews.EXTRA_authorphoto, news.authorPhoto)
        startActivity(moveToDetail)
    }

    private fun showByAuthor(author: Author){
        displayList.clear()
        searchView.setQuery("", false)
        searchView.onActionViewCollapsed()
        searchView.clearFocus()
        searchView.setIconifiedByDefault(false)
        var nameAuthor = author.authorName
        list.forEach {
            if(nameAuthor.equals("All Author")){
                displayList.add(it)
            }
            else if(it.author.toLowerCase().contains(nameAuthor.toLowerCase())){
                displayList.add(it)
            }
        }
        rvNews.adapter!!.notifyDataSetChanged()
    }


//    Controller Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        var item : MenuItem = menu.findItem(R.id.app_bar_search)
        searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isNotEmpty()){
                    displayList.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    list.forEach {
                        if(it.judul.toLowerCase(Locale.getDefault()).contains(search)){
                            displayList.add(it)
                        }
                    }
                    rvNews.adapter!!.notifyDataSetChanged()
                }
                else{
                    displayList.clear()
                    displayList.addAll(list)
                    rvNews.adapter!!.notifyDataSetChanged()
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        item.collapseActionView()
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }

}