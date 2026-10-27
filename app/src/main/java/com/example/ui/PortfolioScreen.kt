package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui.components.PortfolioSection
import com.example.ui.components.PortfolioTopBar
import com.example.ui.sections.AboutSection
import com.example.ui.sections.ContactSection
import com.example.ui.sections.FooterSection
import com.example.ui.sections.HeroSection
import com.example.ui.sections.ProcessSection
import com.example.ui.sections.ProjectsSection
import com.example.ui.sections.ServicesSection
import com.example.ui.sections.SkillsSection
import com.example.ui.sections.WhyMeSection
import com.example.ui.theme.MainBackground
import com.example.ui.theme.PrimaryAccent
import com.example.ui.theme.SecondaryAccent
import kotlinx.coroutines.launch

@Composable
fun PortfolioScreen(
  modifier: Modifier = Modifier
) {
  val listState = rememberLazyListState()
  val coroutineScope = rememberCoroutineScope()

  var selectedServicePreset by remember { mutableStateOf("") }

  // Indices for sections in LazyColumn:
  // 0: Hero
  // 1: About
  // 2: Services
  // 3: Skills
  // 4: Projects
  // 5: Process
  // 6: Why Me
  // 7: Contact
  // 8: Footer

  val currentSection by remember {
    derivedStateOf {
      when (listState.firstVisibleItemIndex) {
        0 -> PortfolioSection.HOME
        1 -> PortfolioSection.ABOUT
        2 -> PortfolioSection.SERVICES
        3 -> PortfolioSection.SKILLS
        4 -> PortfolioSection.PROJECTS
        5 -> PortfolioSection.PROCESS
        6 -> PortfolioSection.WHY_ME
        else -> PortfolioSection.CONTACT
      }
    }
  }

  val showScrollToTop by remember {
    derivedStateOf { listState.firstVisibleItemIndex > 1 }
  }

  fun scrollToSection(section: PortfolioSection) {
    val targetIndex = when (section) {
      PortfolioSection.HOME -> 0
      PortfolioSection.ABOUT -> 1
      PortfolioSection.SERVICES -> 2
      PortfolioSection.SKILLS -> 3
      PortfolioSection.PROJECTS -> 4
      PortfolioSection.PROCESS -> 5
      PortfolioSection.WHY_ME -> 6
      PortfolioSection.CONTACT -> 7
    }
    coroutineScope.launch {
      listState.animateScrollToItem(targetIndex)
    }
  }

  Scaffold(
    modifier = modifier.fillMaxSize(),
    containerColor = MainBackground,
    topBar = {
      PortfolioTopBar(
        currentSection = currentSection,
        onNavigateToSection = { section -> scrollToSection(section) },
        onLetsTalkClick = { scrollToSection(PortfolioSection.CONTACT) }
      )
    },
    floatingActionButton = {
      AnimatedVisibility(
        visible = showScrollToTop,
        enter = fadeIn(),
        exit = fadeOut()
      ) {
        FloatingActionButton(
          onClick = {
            coroutineScope.launch {
              listState.animateScrollToItem(0)
            }
          },
          containerColor = Color(0xFF0F1E3D),
          contentColor = SecondaryAccent,
          shape = CircleShape,
          modifier = Modifier
            .size(44.dp)
            .border(1.dp, PrimaryAccent.copy(alpha = 0.6f), CircleShape)
            .testTag("scroll_to_top_button")
        ) {
          Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "Scroll to top",
            modifier = Modifier.size(24.dp)
          )
        }
      }
    }
  ) { innerPadding ->
    BoxWithConstraints(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(MainBackground),
      contentAlignment = Alignment.TopCenter
    ) {
      LazyColumn(
        state = listState,
        modifier = Modifier
          .fillMaxSize()
          .widthIn(max = 840.dp)
          .testTag("portfolio_scroll_container")
      ) {
        // 0. Hero Section
        item(key = "hero") {
          HeroSection(
            onExploreWorkClick = { scrollToSection(PortfolioSection.PROJECTS) },
            onContactClick = { scrollToSection(PortfolioSection.CONTACT) }
          )
        }

        // 1. About Me Section
        item(key = "about") {
          AboutSection()
        }

        // 2. Services Section
        item(key = "services") {
          ServicesSection(
            onDiscussService = { serviceTitle ->
              selectedServicePreset = serviceTitle
              scrollToSection(PortfolioSection.CONTACT)
            }
          )
        }

        // 3. Skills Section
        item(key = "skills") {
          SkillsSection()
        }

        // 4. Featured Projects Section
        item(key = "projects") {
          ProjectsSection()
        }

        // 5. Work Process Section
        item(key = "process") {
          ProcessSection()
        }

        // 6. Why Work With Me Section
        item(key = "whyme") {
          WhyMeSection()
        }

        // 7. Contact Section
        item(key = "contact") {
          ContactSection(
            selectedServicePreset = selectedServicePreset
          )
        }

        // 8. Footer Section
        item(key = "footer") {
          FooterSection(
            onNavigateToSection = { section -> scrollToSection(section) }
          )
        }

        item(key = "bottom_spacer") {
          Spacer(modifier = Modifier.height(16.dp))
        }
      }
    }
  }
}
