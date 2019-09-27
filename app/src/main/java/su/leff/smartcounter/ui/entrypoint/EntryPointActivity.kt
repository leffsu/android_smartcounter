package su.leff.smartcounter.ui.entrypoint

import android.content.Intent
import android.os.Bundle
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard
import su.leff.smartcounter.ui.MainActivity
import su.leff.smartcounter.R
import su.leff.smartcounter.ui.signin.SignInActivity


class EntryPointActivity : FancyWalkthroughActivity(){

    override fun onFinishButtonPressed() {
        startActivity(Intent(this, SignInActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pages = ArrayList<FancyWalkthroughCard>()

        setColorBackground(R.color.backgroundColor)

        val fancywalkthroughCard1 = FancyWalkthroughCard("Title", "Description", R.mipmap.ic_launcher)
        fancywalkthroughCard1.setBackgroundColor(R.color.entryPointDialogBackgroundColor)
        fancywalkthroughCard1.setTitleColor(R.color.white)
        fancywalkthroughCard1.setDescriptionColor(R.color.black)

        val fancywalkthroughCard2 = FancyWalkthroughCard("Title", "Description", R.mipmap.ic_launcher)
        fancywalkthroughCard1.setBackgroundColor(R.color.entryPointDialogBackgroundColor)
        fancywalkthroughCard1.setTitleColor(R.color.white)
        fancywalkthroughCard1.setDescriptionColor(R.color.white)

        val fancywalkthroughCard3 = FancyWalkthroughCard("Title", "Description", R.mipmap.ic_launcher)
        fancywalkthroughCard1.setBackgroundColor(R.color.entryPointDialogBackgroundColor)
        fancywalkthroughCard1.setTitleColor(R.color.white)
        fancywalkthroughCard1.setDescriptionColor(R.color.white)

        pages.add(fancywalkthroughCard1)
        pages.add(fancywalkthroughCard2)
        pages.add(fancywalkthroughCard3)
        setOnboardPages(pages)
    }
}