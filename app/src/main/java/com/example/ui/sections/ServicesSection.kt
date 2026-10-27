package com.example.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.example.ui.theme.CardBackgroundElevated
import com.example.ui.theme.PrimaryAccent
import com.example.ui.theme.SecondaryAccent
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary

@Composable
fun ServicesSection(
  onDiscussService: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 24.dp)
      .testTag("services_section")
  ) {
    SectionHeader(
      badgeText = "What I Offer",
      title = "Services & Solutions",
      subtitle = "Tailored digital expertise to elevate your brand presence, engagement, and operational efficiency."
    )

    Spacer(modifier = Modifier.height(20.dp))

    PortfolioConfig.services.forEach { service ->
      val icon: ImageVector = when (service.iconName) {
        "palette" -> Icons.Default.Palette
        "code" -> Icons.Default.Code
        "web" -> Icons.Default.Language
        "trending_up" -> Icons.Default.TrendingUp
        "auto_awesome" -> Icons.Default.AutoAwesome
        "videocam" -> Icons.Default.Videocam
        else -> Icons.Default.Code
      }

      GlowCard(
        backgroundColor = CardBackground,
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 8.dp)
          .testTag("service_card_${service.id}")
      ) {
        Column(
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              // Service Icon Badge with glowing background
              Box(
                modifier = Modifier
                  .size(44.dp)
                  .clip(RoundedCornerShape(12.dp))
                  .background(
                    Brush.linearGradient(
                      listOf(Color(0xFF0F1E3D), Color(0xFF0B1424))
                    )
                  )
                  .border(1.dp, PrimaryAccent.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = icon,
                  contentDescription = service.title,
                  tint = SecondaryAccent,
                  modifier = Modifier.size(24.dp)
                )
              }

              Spacer(modifier = Modifier.width(14.dp))

              Text(
                text = service.title,
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = TextPrimary,
                  fontSize = 17.sp
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          Text(
            text = service.description,
            style = MaterialTheme.typography.bodyMedium.copy(
              color = TextMuted,
              lineHeight = 22.sp
            )
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Key deliverables
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(CardBackgroundElevated)
              .padding(10.dp)
          ) {
            service.features.forEach { feature ->
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 3.dp)
              ) {
                Box(
                  modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(SuccessGreen.copy(alpha = 0.2f)),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = SuccessGreen,
                    modifier = Modifier.size(10.dp)
                  )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = feature,
                  style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextPrimary,
                    fontSize = 12.sp
                  )
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // "Discuss This Service" button
          OutlinedButton(
            onClick = { onDiscussService(service.title) },
            colors = ButtonDefaults.outlinedButtonColors(
              containerColor = Color.Transparent,
              contentColor = SecondaryAccent
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryAccent.copy(alpha = 0.6f)),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(44.dp)
              .testTag("discuss_service_${service.id}")
          ) {
            Text(
              text = "Discuss This Service",
              style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.SemiBold,
                color = SecondaryAccent
              )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
              imageVector = Icons.Default.ArrowForward,
              contentDescription = null,
              modifier = Modifier.size(14.dp)
            )
          }
        }
      }
    }
  }
}
