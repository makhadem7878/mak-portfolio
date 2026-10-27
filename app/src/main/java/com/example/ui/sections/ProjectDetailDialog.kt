package com.example.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.window.Dialog
import com.example.data.PortfolioConfig
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
fun ProjectDetailDialog(
  project: PortfolioConfig.ProjectItem,
  onDismiss: () -> Unit
) {
  Dialog(onDismissRequest = onDismiss) {
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = CardBackground,
      modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, BorderDefault, RoundedCornerShape(20.dp))
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp)
          .verticalScroll(rememberScrollState())
      ) {
        // Header with Close Button
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Concept badge
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
              .clip(RoundedCornerShape(12.dp))
              .background(Color(0xFF261D10))
              .border(1.dp, WarningAmber.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
              .padding(horizontal = 8.dp, vertical = 4.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Info,
              contentDescription = null,
              tint = WarningAmber,
              modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "DEMO CONCEPT",
              style = MaterialTheme.typography.labelSmall.copy(
                color = WarningAmber,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
              )
            )
          }

          IconButton(
            onClick = onDismiss,
            modifier = Modifier.size(32.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close",
              tint = TextMuted
            )
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Visual Concept Banner
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(
              Brush.linearGradient(
                listOf(Color(0xFF132342), Color(0xFF0C1629), Color(0xFF16294C))
              )
            )
            .border(1.dp, PrimaryAccent.copy(alpha = 0.4f), RoundedCornerShape(14.dp)),
          contentAlignment = Alignment.Center
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
              text = project.category.uppercase(),
              style = MaterialTheme.typography.labelSmall.copy(
                color = SecondaryAccent,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
              )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = project.title,
              style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              )
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = project.title,
          style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
        )

        Text(
          text = "Category: ${project.category}",
          style = MaterialTheme.typography.bodyMedium.copy(
            color = SecondaryAccent
          )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Description
        Text(
          text = project.description,
          style = MaterialTheme.typography.bodyLarge.copy(
            color = TextMuted,
            lineHeight = 22.sp
          )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = "Tools & Technologies Applied",
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
          )
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(modifier = Modifier.fillMaxWidth()) {
          project.tools.forEach { tool ->
            Box(
              modifier = Modifier
                .padding(end = 6.dp, bottom = 6.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(CardBackgroundElevated)
                .border(1.dp, BorderDefault, RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
              Text(
                text = tool,
                style = MaterialTheme.typography.bodyMedium.copy(
                  color = TextPrimary,
                  fontSize = 12.sp
                )
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Notice
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F1A30))
            .border(1.dp, BorderDefault, RoundedCornerShape(10.dp))
            .padding(12.dp)
        ) {
          Text(
            text = "Note: This is an editable design concept showcase demonstrating digital creative skills. Verified client work can replace this entry in PortfolioConfig.kt.",
            style = MaterialTheme.typography.bodyMedium.copy(
              color = TextMuted,
              fontSize = 11.sp,
              lineHeight = 16.sp
            )
          )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
          onClick = onDismiss,
          colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryAccent,
            contentColor = Color.White
          ),
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Text("Close Details")
        }
      }
    }
  }
}
