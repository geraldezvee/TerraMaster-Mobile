package com.gereso.terramaster

data class LoginResponse (val token: String,val emailAddress: String)
data class LoginRequest (val emailAddress: String,val password: String)
data class FetchAll(val fullname: String,val introduction: String,val location: String,val pricerange: String,val proposalsdone: Int,val specialty: String)
data class ExpertModel(val fullname: String,val introduction: String,val location: String,val pricerange: String,val proposalsdone: Int,val specialty: String)
data class UserModel(val firstName: String,val lastName: String,val nickname: String,val emailAddress: String,val password: String)