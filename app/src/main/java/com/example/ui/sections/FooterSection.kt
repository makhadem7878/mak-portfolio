package com.example.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
import com.example.ui.components.PortfolioSection
import com.example.ui.theme.BorderDefault
import com.example.ui.theme.MainBackground
import com.example.ui.theme.PrimaryAccent
import com.example.ui.theme.SecondaryAccent
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSubtle
import java.util.Calendar

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FooterSection(
  onNavigateToSection: (PortfolioSection) -> Unit,
  modifier: Modifier = Modifier
) {
  val currentYear = Calendar.getInstance().get(Calendar.YEAR)

  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(Color(0xFF05080E))
      .padding(horizontal = 20.dp, vertical = 28.dp)
      .testTag("footer_section"),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Divider(color = BorderDefault.copy(alpha = 0.6f), thickness = 1.dp)
    Spacer(modifier = Modifier.height(20.dp))
    // Monogram & Brand Name
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .clickable { onNavigateToSection(PortfolioSection.HOME) }
        .padding(4.dp)
    ) {
      Box(
        modifier = Modifier
          .size(32.dp)
          .clip(CircleShape)
          .background(
            Brush.linearGradient(listOf(PrimaryAccent, SecondaryAccent))
          ),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = PortfolioConfig.brandShort,
          style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        )
      }
      Spacer(modifier = Modifier.width(10.dp))
      Text(
        text = PortfolioConfig.name,
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
      )
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Tagline
    Text(
      text = PortfolioConfig.copyrightTagline,
      style = MaterialTheme.typography.bodyMedium.copy(
        color = TextMuted,
        fontSize = 12.sp,
        lineHeight = 18.sp
      ),
      modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(18.dp))

    // Navigation links in footer
    FlowRow(
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier.fillMaxWidth()
    ) {
      PortfolioSection.values().forEach { section ->
        Text(
          text = section.displayName,
          style = MaterialTheme.typography.bodySmall.copy(
            color = SecondaryAccent,
            fontWeight = FontWeight.Medium
          ),
          modifier = Modifier
            .clickable { onNavigateToSection(section) }
            .padding(horizontal = 10.dp, vertical = 6.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    Divider(color = BorderDefault.copy(alpha = 0.5f), thickness = 1.dp)

    Spacer(modifier = Modifier.height(16.dp))

    // Copyright statement with dynamic year
    Text(
      text = "© $currentYear ${PortfolioConfig.name}. All rights reserved.",
      style = MaterialTheme.typography.labelSmall.copy(
        color = TextSubtle,
        fontSize = 11.sp
      )
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
      text = "Crafted with precision • Digital Creative Professional",
      style = MaterialTheme.typography.labelSmall.copy(
        color = TextSubtle.copy(alpha = 0.7f),
        fontSize = 10.sp
      )
    )
  }
}
