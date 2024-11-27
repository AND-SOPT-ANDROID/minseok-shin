package org.sopt.and.data.datalocal.datasource

interface UserInfoLocalDataSource {
    var accessToken: String
    var nickname: String
    fun clear()
}