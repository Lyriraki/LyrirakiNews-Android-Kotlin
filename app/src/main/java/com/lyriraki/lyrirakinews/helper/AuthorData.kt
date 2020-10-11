package com.lyriraki.lyrirakinews.helper

import com.lyriraki.lyrirakinews.R
import com.lyriraki.lyrirakinews.model.Author

object AuthorData{
    private val authorName = arrayOf(
        "All Author",
        "Alfian Yusuf Abdullah",
        "Zidni Sika Azkia",
        "Dimas Catur Wibowo",
        "Mutiara Arumsari",
        "Dicoding Intern"
    )


    private val newsAuthorPhoto = intArrayOf(
        R.drawable.dicoding_intern,
        R.drawable.alfian_yusuf_abdullah,
        R.drawable.dicoding_intern,
        R.drawable.dimas_catur_wibowo,
        R.drawable.mutiara_arumsari,
        R.drawable.dicoding_intern
    )

    val listDataAuthor: ArrayList<Author>
        get() {
            val listAuthor = arrayListOf<Author>()
            for (position in authorName.indices) {
                val author = Author()
                author.authorName = authorName[position]
                author.authorPhoto = newsAuthorPhoto[position]
                listAuthor.add(author)
            }
            return listAuthor
        }
}