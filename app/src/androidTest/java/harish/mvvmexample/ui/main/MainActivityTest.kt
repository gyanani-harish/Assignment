package harish.mvvmexample.ui.main

import androidx.appcompat.widget.Toolbar
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import harish.mvvmexample.R
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val testRule = ActivityTestRule(MainActivity::class.java)
    lateinit var mActivity: MainActivity
    @Before
    fun setUp() {
        mActivity = testRule.activity
    }

    @Test
    fun testLaunch() {
        val view = mActivity.findViewById<Toolbar>(R.id.my_toolbar)
        TestCase.assertNotNull(view)
        TestCase.assertEquals(view.title,mActivity.getString(R.string.app_name))
    }

    @Test
    fun goToDetailsFragment() {
    }

    @After
    fun tearDown() {

    }
}