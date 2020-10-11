package com.lyriraki.lyrirakinews.model

data class News(
    var judul: String = "",
    var author: String = "",
    var date: String = "",
    var des: String = "",
    var photo: Int = 0,
    var authorPhoto: Int = 0
)