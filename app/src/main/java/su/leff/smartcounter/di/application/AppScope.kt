package su.leff.smartcounter.di.application

@AppScope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope {}


//fun 'sign in works'() {
//    beforeTest {
//        activityTestRule.launchActivity(null)
//    }.afterTest {
//        TestOrchestrator.signedIn = true
//    }.run {
//        step("main screen opened"){
//            signInButton.isVisible()
//        }
//        step("sign in"){
//            signInButton.click()
//            authWindow.clickFirst()
//            sleep(500)
//        }
//        step("on home screen"){
//            graph.isVisible()
//            fabAdd.isVisible()
//        }
//    }
//}