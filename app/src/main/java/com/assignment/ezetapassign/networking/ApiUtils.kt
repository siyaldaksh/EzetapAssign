package com.example.codegamaassignment

class ApiUtils() {

    companion object{
        private val BASE_URL = "https://demo.ezetap.com/mobileapps/"

        fun getAPIService(): Api {
            return RetrofitClient.getClient(BASE_URL).create(Api::class.java)
        }
    }


}