package org.sopt.and.data.datalocal.datasource

interface UserLocalDataSource {
    var accessToken: String
    var nickname: String
    fun clear()
}