package com.example.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.ui.components.GlowCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.BorderDefault
import com.example.ui.theme.CardBackground
import com.example.ui.theme.CardBackgroundElevated
import com.example.ui.theme.PrimaryAccent
import com.example.ui.theme.SecondaryAccent
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.WarningAmber

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectsSection(
  modifier: Modifier = Modifier
) {
  var selectedCategory by remember { mutableStateOf("All") }
  var selectedProjectForDetail by remember { mutableStateOf<PortfolioConfig.ProjectItem?>(null) }

  val filteredProjects = remember(selectedCategory) {
    if (selectedCategory == "All") {
      PortfolioConfig.projects
    } else {
      PortfolioConfig.projects.filter { it.category == selectedCategory }
    }
  }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 24.dp)
      .testTag("projects_section")
  ) {
    SectionHeader(
      badgeText = "Portfolio Showcase",
      title = "Featured Projects",
      subtitle = "Explore selected creative work and digital concepts."
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Category Filter Chips
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState())
        .padding(vertical = 4.dp)
    ) {
      PortfolioConfig.projectCategories.forEach { category ->
        val isSelected = category == selectedCategory
        Box(
          modifier = Modifier
            .padding(end = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
              if (isSelected) PrimaryAccent else Color(0xFF0C1629)
            )
            .border(
              1.dp,
              if (isSelected) SecondaryAccent else BorderDefault,
              RoundedCornerShape(20.dp)
            )
            .clickable { selectedCategory = category }
            .padding(horizontal = 14.dp, vertical = 8.dp)
            .testTag("filter_chip_${category.lowercase().replace(" ", "_")}")
        ) {
          Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium.copy(
              color = if (isSelected) Color.White else TextMuted,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
              fontSize = 12.sp
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(18.dp))

    // Project Cards
    filteredProjects.forEach { project ->
      GlowCard(
        backgroundColor = CardBackground,
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 8.dp)
          .testTag("project_card_${project.id}")
      ) {
        Column(modifier = Modifier.fillMaxWidth()) {
          // Banner Illustration Header
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(100.dp)
              .clip(RoundedCornerShape(12.dp))
              .background(
                Brush.linearGradient(
                  listOf(Color(0xFF0F1E3D), Color(0xFF0B1424), Color(0xFF142446))
                )
              )
              .border(1.dp, BorderDefault, RoundedCornerShape(12.dp))
              .padding(12.dp),
            contentAlignment = Alignment.Center
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.Top
            ) {
              // Category tag
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(6.dp))
                  .background(Color(0xFF070B14).copy(alpha = 0.8f))
                  .border(1.dp, PrimaryAccent.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(
                  text = project.category,
                  style = MaterialTheme.typography.labelSmall.copy(
                    color = SecondaryAccent,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                  )
                )
              }

              // Concept / Demo Badge
              if (project.isConcept) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF261D10))
                    .border(1.dp, WarningAmber.copy(alpha = 0.7f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = WarningAmber,
                    modifier = Modifier.size(10.dp)
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = "CONCEPT / DEMO",
                    style = MaterialTheme.typography.labelSmall.copy(
                      color = WarningAmber,
                      fontSize = 9.sp,
                      fontWeight = FontWeight.Bold
                    )
                  )
                }
              }
            }

            // Center Title in banner
            Text(
              text = project.title,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              ),
              modifier = Modifier.padding(top = 16.dp)
            )
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Project Title
          Text(
            text = project.title,
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              color = TextPrimary,
              fontSize = 16.sp
            )
          )

          Spacer(modifier = Modifier.height(6.dp))

          // Description
          Text(
            text = project.description,
            style = MaterialTheme.typography.bodyMedium.copy(
              color = TextMuted,
              lineHeight = 20.sp
            )
          )

          Spacer(modifier = Modifier.height(12.dp))

          // Tools used chips
          FlowRow(modifier = Modifier.fillMaxWidth()) {
            project.tools.forEach { tool ->
              Box(
                modifier = Modifier
                  .padding(end = 6.dp, bottom = 6.dp)
                  .clip(RoundedCornerShape(6.dp))
                  .background(CardBackgroundElevated)
                  .border(1.dp, BorderDefault, RoundedCornerShape(6.dp))
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(
                  text = tool,
                  style = MaterialTheme.typography.bodySmall.copy(
                    color = TextPrimary,
                    fontSize = 11.sp
                  )
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Action buttons: "View Concept Details" and optional demo button
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            OutlinedButton(
              onClick = { selectedProjectForDetail = project },
              colors = ButtonDefaults.outlinedButtonColors(
                contentColor = SecondaryAccent
              ),
              border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryAccent.copy(alpha = 0.6f)),
              shape = RoundedCornerShape(8.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              Icon(
                imageVector = Icons.Default.Visibility,
                contentDescription = null,
                modifier = Modifier.size(14.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "View Concept Details",
                style = MaterialTheme.typography.labelLarge.copy(
                  fontSize = 12.sp,
                  fontWeight = FontWeight.SemiBold
                )
              )
            }
          }
        }
      }
    }
  }

  // Detail dialog if open
  selectedProjectForDetail?.let { project ->
    ProjectDetailDialog(
      project = project,
      onDismiss = { selectedProjectForDetail = null }
    )
  }
}
