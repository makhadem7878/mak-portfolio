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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.TrackChanges
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
import com.example.ui.theme.CardBackground
import com.example.ui.theme.PrimaryAccent
import com.example.ui.theme.SecondaryAccent
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary

@Composable
fun WhyMeSection(
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 24.dp)
      .testTag("why_me_section")
  ) {
    SectionHeader(
      badgeText = "Client Value",
      title = "Why Work With Me?",
      subtitle = "Committed to transparent communication, creative solutions, and delivering on your requirements."
    )

    Spacer(modifier = Modifier.height(20.dp))

    PortfolioConfig.whyWorkWithMe.forEach { item ->
      val icon: ImageVector = when (item.iconName) {
        "chat" -> Icons.Default.ChatBubbleOutline
        "lightbulb" -> Icons.Default.Lightbulb
        "check_circle" -> Icons.Default.CheckCircleOutline
        "target" -> Icons.Default.TrackChanges
        else -> Icons.Default.CheckCircleOutline
      }

      GlowCard(
        backgroundColor = CardBackground,
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 6.dp)
      ) {
        Row(verticalAlignment = Alignment.Top) {
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(Color(0xFF0F1E3D))
              .border(1.dp, PrimaryAccent.copy(alpha = 0.5f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = icon,
              contentDescription = item.title,
              tint = SecondaryAccent,
              modifier = Modifier.size(18.dp)
            )
          }

          Spacer(modifier = Modifier.width(14.dp))

          Column {
            Text(
              text = item.title,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                fontSize = 15.sp
              )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = item.description,
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
