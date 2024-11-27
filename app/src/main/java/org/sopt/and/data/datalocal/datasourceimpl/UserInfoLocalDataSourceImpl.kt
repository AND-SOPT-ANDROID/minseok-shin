import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource

class UserInfoLocalDataSourceImpl(context: Context) : UserInfoLocalDataSource {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override var accessToken: String
        get() = sharedPreferences.getString(TOKEN, INITIAL_VALUE).toString()
        set(value) = sharedPreferences.edit { putString(TOKEN, value) }

    override var nickname: String
        get() = sharedPreferences.getString(NICKNAME, INITIAL_VALUE).toString()
        set(value) = sharedPreferences.edit { putString(NICKNAME, value) }

    override fun clear() = sharedPreferences.edit { clear() }

    companion object {
        const val PREFERENCES_NAME = "user_preferences"
        const val TOKEN = "token"
        const val NICKNAME = "nickname"
        const val INITIAL_VALUE = ""
    }
}