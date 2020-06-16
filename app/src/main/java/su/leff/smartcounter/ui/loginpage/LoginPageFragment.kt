package su.leff.smartcounter.ui.loginpage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.fragment_loginpage.*
import su.leff.smartcounter.R
import su.leff.smartcounter.util.BaseFragment
import su.leff.smartcounter.colorer.ResourceManager
import su.leff.smartcounter.util.isProbablyAnEmulator


class LoginPageFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loginpage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val account = GoogleSignIn.getLastSignedInAccount(context)

        if (account != null
//            || isProbablyAnEmulator()
        ) {
            findNavController().navigate(R.id.action_loginPageFragment_to_homePageFragment)
            return
        }

        activity?.applicationContext?.let {
            login_background.setBackgroundColor(ResourceManager.getBackgroundColor(it))
            txvSmartCounter.setTextColor(ResourceManager.getUsualTextColorColor(it))
            txvHelloThere.setTextColor(ResourceManager.getUsualTextColorColor(it))

        }

        setWelcomeMessage("Привет,\r\nрады тебя видеть")
        setAppName("Smart Counter")

        sign_in_button.setSize(SignInButton.SIZE_WIDE)
        (sign_in_button.getChildAt(0) as TextView).text = "Войти с помощью Google"

        sign_in_button.setOnClickListener {
            val gso =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()

            val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
            val intent = mGoogleSignInClient.signInIntent
            startActivityForResult(intent, 1313)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1313) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            try {
                val task =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                val result = task.getResult(ApiException::class.java)
                shared.firstName = result?.givenName ?: ""
                shared.secondName = result?.familyName ?: ""
                shared.email = result?.email ?: ""
                shared.accountId = result?.id ?: ""
                findNavController().navigate(R.id.action_loginPageFragment_to_homePageFragment)
            } catch (apiException: ApiException) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }



    fun setWelcomeMessage(value: String) {
        txvHelloThere.text = value
    }

    fun setAppName(value: String) {
        txvSmartCounter.text = value
    }
}