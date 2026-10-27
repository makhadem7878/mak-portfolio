package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.PortfolioConfig
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Musabber Portfolio", appName)
  }

  @Test
  fun `verify portfolio config integrity`() {
    assertEquals("Musabber Ali Khadem", PortfolioConfig.name)
    assertEquals(6, PortfolioConfig.services.size)
    assertEquals(5, PortfolioConfig.skillCategories.size)
    assertEquals(6, PortfolioConfig.projects.size)
    assertEquals(4, PortfolioConfig.workProcess.size)
    assertEquals(4, PortfolioConfig.whyWorkWithMe.size)
    assertFalse(PortfolioConfig.contactEmail.isBlank())
    assertTrue(PortfolioConfig.projects.all { it.isConcept })
  }
}
