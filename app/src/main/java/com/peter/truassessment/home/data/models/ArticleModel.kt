package com.peter.truassessment.home.data.models

data class ArticleModel(
    val title: String,
    val imageUrl: String? = null,
    val body: String? = null
){
    companion object {
        val textMock = ArticleModel(
            title = "Lorem ipsum",
            body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit Pellentesque nec viverra nulla. Nunc volutpat cursus ornare. Maecenas sed sagittis sem. Maecenas malesuada lacinia dui id elementum. Pellentesque condimentum quis ex."
        )

        val imageMock = ArticleModel(
            title = "Lorem ipsum 2",
            imageUrl = "https://gratisography.com/wp-content/uploads/2025/01/gratisography-dog-vacation-800x525.jpg"
        )

        val imageAndTextMock = ArticleModel(
            title = "Lorem ipsum",
            body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit Pellentesque nec viverra nulla. Nunc volutpat cursus ornare. Maecenas sed sagittis sem. Maecenas malesuada lacinia dui id elementum. Pellentesque condimentum quis ex.",
            imageUrl = "https://gratisography.com/wp-content/uploads/2025/01/gratisography-dog-vacation-800x525.jpg"
        )
    }
}
