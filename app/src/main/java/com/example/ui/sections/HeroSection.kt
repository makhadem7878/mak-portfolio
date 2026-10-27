package com.example.ui.sections

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.PortfolioConfig
import com.example.ui.theme.AccentGlow
import com.example.ui.theme.BorderDefault
import com.example.ui.theme.CardBackground
import com.example.ui.theme.MainBackground
import com.example.ui.theme.PrimaryAccent
import com.example.ui.theme.SecondaryAccent
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary

@Composable
fun HeroSection(
  onExploreWorkClick: () -> Unit,
  onContactClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  // Pulsing animation for the freelance availability badge
  val infiniteTransition = rememberInfiniteTransition(label = "pulse")
  val pulseAlpha by infiniteTransition.animateFloat(
    initialValue = 0.4f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulseAlpha"
  )

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 24.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    // 1. OPEN TO FREELANCE PROJECTS Badge
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .clip(RoundedCornerShape(30.dp))
        .background(Color(0xFF0C192E))
        .border(1.dp, Color(0xFF1E3A6E), RoundedCornerShape(30.dp))
        .padding(horizontal = 14.dp, vertical = 8.dp)
        .testTag("freelance_status_badge")
    ) {
      Box(
        modifier = Modifier
          .size(8.dp)
          .clip(CircleShape)
          .background(SuccessGreen.copy(alpha = pulseAlpha))
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = PortfolioConfig.freelanceStatusBadge,
        style = MaterialTheme.typography.labelSmall.copy(
          color = SecondaryAccent,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.1.sp
        )
      )
    }

    Spacer(modifier = Modifier.height(20.dp))

    // 2. Profile Photo with Electric-Blue Glow and Refined Frame
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier
        .padding(vertical = 8.dp)
        .widthIn(max = 280.dp)
        .fillMaxWidth(0.78f)
    ) {
      // Glow background ring
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(1f)
          .clip(CircleShape)
          .background(
            Brush.radialGradient(
              listOf(
                PrimaryAccent.copy(alpha = 0.35f),
                SecondaryAccent.copy(alpha = 0.15f),
                Color.Transparent
              )
            )
          )
      )

      // Outer border frame
      Box(
        modifier = Modifier
          .fillMaxWidth(0.92f)
          .aspectRatio(1f)
          .shadow(16.dp, RoundedCornerShape(28.dp), spotColor = PrimaryAccent, ambientColor = AccentGlow)
          .clip(RoundedCornerShape(28.dp))
          .border(
            2.dp,
            Brush.linearGradient(
              listOf(
                PrimaryAccent,
                SecondaryAccent,
                Color(0xFF1E3A6E),
                PrimaryAccent
              )
            ),
            RoundedCornerShape(28.dp)
          )
          .background(
            Brush.verticalGradient(
              listOf(Color(0xFF0F1E38), Color(0xFF070B14))
            )
          ),
        contentAlignment = Alignment.BottomCenter
      ) {
        // Musabber Ali Khadem's actual uploaded profile photograph (Full preservation of head, ears, beard, and shoulders)
        Image(
          painter = painterResource(id = R.drawable.profile),
          contentDescription = PortfolioConfig.profilePhotoAlt,
          contentScale = ContentScale.Fit,
          alignment = Alignment.BottomCenter,
          modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .testTag("hero_profile_image")
        )
      }

      // Verified Professional Badge on bottom-right of photo
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .align(Alignment.BottomEnd)
          .clip(RoundedCornerShape(20.dp))
          .background(Color(0xFF0F1B33))
          .border(1.dp, SecondaryAccent.copy(alpha = 0.7f), RoundedCornerShape(20.dp))
          .padding(horizontal = 10.dp, vertical = 6.dp)
          .shadow(6.dp, RoundedCornerShape(20.dp))
      ) {
        Icon(
          imageVector = Icons.Default.CheckCircle,
          contentDescription = "Verified Identity",
          tint = SecondaryAccent,
          modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Creative Pro",
          style = MaterialTheme.typography.labelSmall.copy(
            color = TextPrimary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp
          )
        )
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // 3. Heading: "Hi, I'm Musabber Ali Khadem."
    Text(
      text = PortfolioConfig.heroHeading,
      style = MaterialTheme.typography.displayMedium.copy(
        fontWeight = FontWeight.Bold,
        color = TextPrimary
      ),
      modifier = Modifier.testTag("hero_heading")
    )

    Spacer(modifier = Modifier.height(8.dp))

    // 4. Main Statement: "Creative Ideas. Smart Solutions. Digital Growth."
    Text(
      text = PortfolioConfig.mainHeadline,
      style = MaterialTheme.typography.titleLarge.copy(
        fontWeight = FontWeight.SemiBold,
        color = SecondaryAccent,
        lineHeight = 26.sp
      ),
      modifier = Modifier.testTag("hero_main_headline")
    )

    Spacer(modifier = Modifier.height(14.dp))

    // 5. Supporting Text
    Text(
      text = PortfolioConfig.heroSupportingText,
      style = MaterialTheme.typography.bodyLarge.copy(
        color = TextMuted,
        lineHeight = 24.sp
      ),
      modifier = Modifier
        .widthIn(max = 560.dp)
        .testTag("hero_supporting_text")
    )

    Spacer(modifier = Modifier.height(24.dp))

    // 6. Action Buttons
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.Center
    ) {
      // Primary: Explore My Work
      Button(
        onClick = onExploreWorkClick,
        colors = ButtonDefaults.buttonColors(
          containerColor = PrimaryAccent,
          contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
          .height(48.dp)
          .border(1.dp, SecondaryAccent.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
          .testTag("hero_explore_work_button")
      ) {
        Text(
          text = "Explore My Work",
          style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Icon(
          imageVector = Icons.Default.ArrowForward,
          contentDescription = null,
          modifier = Modifier.size(16.dp)
        )
      }

      Spacer(modifier = Modifier.width(12.dp))

      // Secondary: Contact Me
      OutlinedButton(
        onClick = onContactClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
          contentColor = TextPrimary
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderDefault),
        modifier = Modifier
          .height(48.dp)
          .background(CardBackground.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
          .testTag("hero_contact_me_button")
      ) {
        Icon(
          imageVector = Icons.Default.Email,
          contentDescription = null,
          tint = SecondaryAccent,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Contact Me",
          style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
        )
      }
    }
  }
}
