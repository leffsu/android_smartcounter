package su.leff.smartcounter.ui.loginpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_loginpage.*
import su.leff.smartcounter.R
import su.leff.smartcounter.colorer.ResourceManager

class LoginPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loginpage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activity?.applicationContext?.let {
            login_background.setBackgroundColor(ResourceManager.getBackgroundColor(it))
            txvSmartCounter.setTextColor(ResourceManager.getUsualTextColorColor(it))
            txvHelloThere.setTextColor(ResourceManager.getUsualTextColorColor(it))
            txvLoginViaGoogle.setTextColor(ResourceManager.getUsualTextColorColor(it))
            txvLogin.setTextColor(ResourceManager.getUsualTextColorColor(it))
            edtYourName.setTextColor(ResourceManager.getUsualTextColorColor(it))
            edtYourName.setHintTextColor(ResourceManager.getUsualTextColorColor(it))
        }

        setWelcomeMessage("hello there\r\nnice to see you here")
        setAppName("Smart Counter")
        setLoginViaGoogle("Login via Google")
        setLoginButtonText("Next")
        edtYourName.hint = "your name"

    }

    fun setWelcomeMessage(value: String) {
        txvHelloThere.text = value
    }

    fun setAppName(value: String) {
        txvSmartCounter.text = value
    }

    fun setLoginViaGoogle(value: String) {
        txvLoginViaGoogle.text = value
    }

    fun setLoginButtonText(value: String) {
        txvLogin.text = value
    }
}