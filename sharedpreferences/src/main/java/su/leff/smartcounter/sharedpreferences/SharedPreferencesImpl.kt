package su.leff.smartcounter.sharedpreferences

import android.content.Context

class SharedPreferencesImpl(context: Context) : ISharedPreferencesAPI {

    val preferences = context.getSharedPreferences("COUNTER_INI", Context.MODE_PRIVATE)

    val emptyString = ""

    override var firstName: String
        get() = preferences.getString(SP_KEY.FIRST_NAME.name, emptyString) ?: emptyString
        set(value) {
            preferences.edit().putString(SP_KEY.FIRST_NAME.name, value).apply()
        }
    override var secondName: String
        get() = preferences.getString(SP_KEY.SECOND_NAME.name, emptyString) ?: emptyString
        set(value) {
            preferences.edit().putString(SP_KEY.SECOND_NAME.name, value).apply()
        }
    override var email: String
        get() = preferences.getString(SP_KEY.EMAIL.name, emptyString) ?: emptyString
        set(value) {
            preferences.edit().putString(SP_KEY.EMAIL.name, value).apply()
        }
    override var accountId: String
        get() = preferences.getString(SP_KEY.ACCOUNT_ID.name, emptyString) ?: emptyString
        set(value) {
            preferences.edit().putString(SP_KEY.ACCOUNT_ID.name, value).apply()
        }


    enum class SP_KEY {
        FIRST_NAME, SECOND_NAME, EMAIL, ACCOUNT_ID
    }
}