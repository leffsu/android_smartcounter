package su.leff.smartcounter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import su.leff.smartcounter.R
import android.R.attr.typeface
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.main_activity.*
import su.leff.smartcounter.basemodule.BaseActivity
import su.leff.smartcounter.colorer.ResourceManager
import java.util.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        nav_host_fragment.setBackgroundResource(ResourceManager.getBackgroundColor())
    }
}
