package harish.mvvmexample.ui.splash

import android.widget.RelativeLayout
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import harish.mvvmexample.R
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MySplashActivityTest {
    @get:Rule
    val testRule = ActivityTestRule(SplashActivity::class.java)
    lateinit var mActivity: SplashActivity
    @Before
    fun setUp() {
        mActivity = testRule.activity
    }

    @Test
    fun testLaunch() {
        val view = mActivity.findViewById<RelativeLayout>(R.id.rlColor)
        assertNotNull(view)
    }

    @After
    fun tearDown() {

    }
}