package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioConfig
import com.example.ui.theme.BorderDefault
import com.example.ui.theme.CardBackground
import com.example.ui.theme.MainBackground
import com.example.ui.theme.PrimaryAccent
import com.example.ui.theme.SecondaryAccent
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary

enum class PortfolioSection(val displayName: String) {
  HOME("Home"),
  ABOUT("About"),
  SERVICES("Services"),
  SKILLS("Skills"),
  PROJECTS("Projects"),
  PROCESS("Process"),
  WHY_ME("Why Me"),
  CONTACT("Contact")
}

@Composable
fun PortfolioTopBar(
  currentSection: PortfolioSection,
  onNavigateToSection: (PortfolioSection) -> Unit,
  onLetsTalkClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  var menuExpanded by remember { mutableStateOf(false) }

  Surface(
    modifier = modifier.fillMaxWidth(),
    color = MainBackground.copy(alpha = 0.95f),
    tonalElevation = 6.dp
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        // Brand on Left: Logo badge + "Musabber Ali Khadem"
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onNavigateToSection(PortfolioSection.HOME) }
            .padding(4.dp)
            .testTag("brand_logo_button")
        ) {
          // Monogram pill
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(
                Brush.linearGradient(listOf(PrimaryAccent, SecondaryAccent))
              ),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = PortfolioConfig.brandShort,
              style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 12.sp
              )
            )
          }

          Spacer(modifier = Modifier.width(10.dp))

          Column {
            Text(
              text = PortfolioConfig.name,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                fontSize = 15.sp
              )
            )
            Text(
              text = PortfolioConfig.professionalTitle,
              style = MaterialTheme.typography.labelSmall.copy(
                color = SecondaryAccent,
                fontSize = 10.sp
              )
            )
          }
        }

        // Right side: "Let's Talk" CTA + Mobile Menu Hamburger
        Row(
          verticalAlignment = Alignment.CenterVertically
        ) {
          // "Let's Talk" prominent button
          Button(
            onClick = onLetsTalkClick,
            colors = ButtonDefaults.buttonColors(
              containerColor = PrimaryAccent,
              contentColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 14.dp, vertical = 8.dp),
            modifier = Modifier
              .testTag("lets_talk_button")
              .border(1.dp, SecondaryAccent.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
          ) {
            Icon(
              imageVector = Icons.Default.Send,
              contentDescription = "Let's Talk",
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Let's Talk",
              style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
              )
            )
          }

          Spacer(modifier = Modifier.width(8.dp))

          // Hamburger Menu Toggle
          IconButton(
            onClick = { menuExpanded = !menuExpanded },
            modifier = Modifier
              .size(40.dp)
              .clip(CircleShape)
              .background(CardBackground)
              .border(1.dp, BorderDefault, CircleShape)
              .testTag("menu_toggle_button")
          ) {
            Icon(
              imageVector = if (menuExpanded) Icons.Default.Close else Icons.Default.Menu,
              contentDescription = if (menuExpanded) "Close Navigation" else "Open Navigation",
              tint = if (menuExpanded) SecondaryAccent else TextPrimary,
              modifier = Modifier.size(20.dp)
            )
          }
        }
      }

      // Quick Horizontal Section Navigation Strip
      if (!menuExpanded) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 12.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          PortfolioSection.values().forEach { section ->
            val isSelected = section == currentSection
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(
                  if (isSelected) PrimaryAccent.copy(alpha = 0.25f) else Color(0xFF0C1424)
                )
                .border(
                  1.dp,
                  if (isSelected) SecondaryAccent else BorderDefault,
                  RoundedCornerShape(14.dp)
                )
                .clickable { onNavigateToSection(section) }
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .testTag("quick_tab_${section.name.lowercase()}")
            ) {
              Text(
                text = section.displayName,
                style = MaterialTheme.typography.labelSmall.copy(
                  color = if (isSelected) SecondaryAccent else TextMuted,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                  fontSize = 11.sp
                )
              )
            }
          }
        }
      }

      // Expandable Mobile / Tablet Navigation Menu
      AnimatedVisibility(
        visible = menuExpanded,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0A1020))
            .border(1.dp, BorderDefault)
            .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
          Text(
            text = "NAVIGATION",
            style = MaterialTheme.typography.labelSmall.copy(
              color = SecondaryAccent,
              letterSpacing = 1.2.sp,
              fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
          )

          PortfolioSection.values().forEach { section ->
            val isSelected = section == currentSection
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(
                  if (isSelected) PrimaryAccent.copy(alpha = 0.15f) else Color.Transparent
                )
                .clickable {
                  menuExpanded = false
                  onNavigateToSection(section)
                }
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .testTag("nav_item_${section.name.lowercase()}"),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = section.displayName,
                style = MaterialTheme.typography.titleMedium.copy(
                  color = if (isSelected) SecondaryAccent else TextPrimary,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
              )

              if (isSelected) {
                Box(
                  modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(SecondaryAccent)
                )
              }
            }
          }
        }
      }
      androidx.compose.material3.Divider(color = BorderDefault, thickness = 1.dp)
    }
  }
}
