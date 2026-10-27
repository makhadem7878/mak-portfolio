package com.example.ui.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.PrimaryAccent
import com.example.ui.theme.SecondaryAccent
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary

@Composable
fun SectionHeader(
  badgeText: String,
  title: String,
  subtitle: String? = null,
  modifier: Modifier = Modifier,
  centered: Boolean = false
) {
  Column(
    modifier = modifier.fillMaxWidth(),
    horizontalAlignment = if (centered) Alignment.CenterHorizontally else Alignment.Start
  ) {
    // Electric blue pill badge
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .clip(RoundedCornerShape(20.dp))
        .background(Color(0xFF0F1A30))
        .border(1.dp, Color(0xFF1E3A6E), RoundedCornerShape(20.dp))
        .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
      Box(
        modifier = Modifier
          .size(6.dp)
          .clip(CircleShape)
          .background(SecondaryAccent)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = badgeText.uppercase(),
        style = MaterialTheme.typography.labelSmall.copy(
          color = SecondaryAccent,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.2.sp
        )
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Main section title
    Text(
      text = title,
      style = MaterialTheme.typography.headlineLarge.copy(
        fontWeight = FontWeight.Bold,
        color = TextPrimary
      )
    )

    if (subtitle != null) {
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodyLarge.copy(
          color = TextMuted,
          lineHeight = 22.sp
        )
      )
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Subtle gradient accent line
    Box(
      modifier = Modifier
        .width(if (centered) 64.dp else 48.dp)
        .height(3.dp)
        .clip(RoundedCornerShape(2.dp))
        .background(
          Brush.horizontalGradient(
            listOf(PrimaryAccent, SecondaryAccent)
          )
        )
    )
  }
}
