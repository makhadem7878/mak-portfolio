package com.example.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioConfig
import com.example.ui.components.GlowCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.CardBackgroundElevated
import com.example.ui.theme.PrimaryAccent
import com.example.ui.theme.SecondaryAccent
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary

@Composable
fun AboutSection(
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 24.dp)
      .testTag("about_section")
  ) {
    SectionHeader(
      badgeText = "Background & Vision",
      title = PortfolioConfig.aboutHeading,
      subtitle = PortfolioConfig.aboutSubtitle
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Main Biography Card
    GlowCard(
      highlightBorder = true,
      backgroundColor = CardBackgroundElevated,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("biography_card")
    ) {
      Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(PrimaryAccent.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.MenuBook,
              contentDescription = null,
              tint = SecondaryAccent,
              modifier = Modifier.size(20.dp)
            )
          }

          Spacer(modifier = Modifier.width(12.dp))

          Column {
            Text(
              text = "Musabber Ali Khadem",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              )
            )
            Text(
              text = "English Literature Graduate & Digital Creator",
              style = MaterialTheme.typography.labelSmall.copy(
                color = SecondaryAccent
              )
            )
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
          text = PortfolioConfig.biography,
          style = MaterialTheme.typography.bodyLarge.copy(
            color = TextMuted,
            lineHeight = 24.sp
          )
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 3 Highlights: Creative Background, Modern Tooling, Client-Centric Mindset
    PortfolioConfig.aboutHighlights.forEachIndexed { index, highlight ->
      val icon: ImageVector = when (index) {
        0 -> Icons.Default.MenuBook
        1 -> Icons.Default.AutoAwesome
        else -> Icons.Default.Verified
      }

      GlowCard(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 6.dp)
      ) {
        Row(verticalAlignment = Alignment.Top) {
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(RoundedCornerShape(8.dp))
              .background(Color(0xFF0C192E))
              .border(1.dp, PrimaryAccent.copy(alpha = 0.4f), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = icon,
              contentDescription = null,
              tint = SecondaryAccent,
              modifier = Modifier.size(16.dp)
            )
          }

          Spacer(modifier = Modifier.width(14.dp))

          Column {
            Text(
              text = highlight.title,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
              )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = highlight.description,
              style = MaterialTheme.typography.bodyMedium.copy(
                color = TextMuted,
                lineHeight = 20.sp
              )
            )
          }
        }
      }
    }
  }
}
