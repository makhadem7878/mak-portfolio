package com.example.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.Videocam
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
import com.example.ui.theme.BorderDefault
import com.example.ui.theme.CardBackground
import com.example.ui.theme.PrimaryAccent
import com.example.ui.theme.SecondaryAccent
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillsSection(
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 24.dp)
      .testTag("skills_section")
  ) {
    SectionHeader(
      badgeText = "Competencies",
      title = "Skills & Digital Tools",
      subtitle = "Practical tools and creative workflows utilized to craft impactful digital solutions."
    )

    Spacer(modifier = Modifier.height(20.dp))

    PortfolioConfig.skillCategories.forEach { category ->
      val icon: ImageVector = when (category.categoryName) {
        "Graphic Design" -> Icons.Default.Palette
        "Website Development" -> Icons.Default.Code
        "Productivity" -> Icons.Default.TaskAlt
        "AI & Automation" -> Icons.Default.AutoAwesome
        "Video & Content" -> Icons.Default.Videocam
        else -> Icons.Default.CheckCircle
      }

      GlowCard(
        backgroundColor = CardBackground,
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 8.dp)
          .testTag("skills_category_${category.categoryName.lowercase().replace(" ", "_")}")
      ) {
        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF0C192E))
                .border(1.dp, PrimaryAccent.copy(alpha = 0.5f), RoundedCornerShape(8.dp)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = icon,
                contentDescription = category.categoryName,
                tint = SecondaryAccent,
                modifier = Modifier.size(16.dp)
              )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
              text = category.categoryName,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              )
            )
          }

          Spacer(modifier = Modifier.height(14.dp))

          // FlowRow of Skill chips
          FlowRow(
            modifier = Modifier.fillMaxWidth()
          ) {
            category.skills.forEach { skill ->
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                  .padding(end = 8.dp, bottom = 8.dp)
                  .clip(RoundedCornerShape(20.dp))
                  .background(Color(0xFF0A1224))
                  .border(1.dp, BorderDefault, RoundedCornerShape(20.dp))
                  .padding(horizontal = 12.dp, vertical = 7.dp)
              ) {
                Box(
                  modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(SecondaryAccent)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = skill,
                  style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                  )
                )
              }
            }
          }
        }
      }
    }
  }
}
