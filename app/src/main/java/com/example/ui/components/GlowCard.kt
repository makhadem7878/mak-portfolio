package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.AccentGlow
import com.example.ui.theme.BorderDefault
import com.example.ui.theme.BorderHighlight
import com.example.ui.theme.CardBackground

@Composable
fun GlowCard(
  modifier: Modifier = Modifier,
  shape: Shape = RoundedCornerShape(16.dp),
  backgroundColor: Color = CardBackground,
  borderColor: Color = BorderDefault,
  highlightBorder: Boolean = false,
  elevation: Dp = 4.dp,
  onClick: (() -> Unit)? = null,
  content: @Composable BoxScope.() -> Unit
) {
  val borderBrush = if (highlightBorder) {
    Brush.linearGradient(
      listOf(BorderHighlight, BorderDefault, Color(0xFF1E3A6E))
    )
  } else {
    Brush.linearGradient(
      listOf(borderColor, Color(0xFF152238))
    )
  }

  Surface(
    modifier = modifier
      .shadow(elevation, shape, ambientColor = AccentGlow, spotColor = AccentGlow)
      .clip(shape)
      .then(
        if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
      )
      .border(1.dp, borderBrush, shape)
      .background(backgroundColor),
    color = Color.Transparent
  ) {
    Box(
      modifier = Modifier.padding(16.dp),
      content = content
    )
  }
}
