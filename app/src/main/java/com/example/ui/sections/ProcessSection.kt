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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.ui.components.GlowCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.BorderDefault
import com.example.ui.theme.CardBackground
import com.example.ui.theme.PrimaryAccent
import com.example.ui.theme.SecondaryAccent
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary

@Composable
fun ProcessSection(
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 24.dp)
      .testTag("process_section")
  ) {
    SectionHeader(
      badgeText = "Methodology",
      title = "Work Process",
      subtitle = "A structured four-step methodology ensuring clarity, creative quality, and on-time delivery."
    )

    Spacer(modifier = Modifier.height(20.dp))

    PortfolioConfig.workProcess.forEachIndexed { index, step ->
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
      ) {
        // Step number indicator column with connector line
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.width(44.dp)
        ) {
          Box(
            modifier = Modifier
              .size(40.dp)
              .clip(CircleShape)
              .background(
                Brush.linearGradient(
                  listOf(Color(0xFF0F1E3D), Color(0xFF0B1424))
                )
              )
              .border(1.dp, SecondaryAccent.copy(alpha = 0.7f), CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = step.stepNumber,
              style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold,
                color = SecondaryAccent,
                fontSize = 13.sp
              )
            )
          }

          if (index < PortfolioConfig.workProcess.size - 1) {
            Box(
              modifier = Modifier
                .width(2.dp)
                .height(44.dp)
                .background(
                  Brush.verticalGradient(
                    listOf(SecondaryAccent.copy(alpha = 0.5f), BorderDefault)
                  )
                )
            )
          }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Step card
        GlowCard(
          backgroundColor = CardBackground,
          modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
        ) {
          Column {
            Text(
              text = step.title,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                fontSize = 16.sp
              )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = step.description,
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
