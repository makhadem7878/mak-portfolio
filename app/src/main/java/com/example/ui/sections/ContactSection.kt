package com.example.ui.sections

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioConfig
import com.example.ui.components.GlowCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.BorderDefault
import com.example.ui.theme.BorderHighlight
import com.example.ui.theme.CardBackground
import com.example.ui.theme.CardBackgroundElevated
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.PrimaryAccent
import com.example.ui.theme.SecondaryAccent
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.WarningAmber

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ContactSection(
  selectedServicePreset: String = "",
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current

  var fullName by remember { mutableStateOf("") }
  var emailAddress by remember { mutableStateOf("") }
  var selectedService by remember(selectedServicePreset) {
    mutableStateOf(if (selectedServicePreset.isNotBlank()) selectedServicePreset else PortfolioConfig.services.first().title)
  }
  var projectDetails by remember { mutableStateOf("") }

  var serviceDropdownExpanded by remember { mutableStateOf(false) }

  // Validation states
  var nameError by remember { mutableStateOf<String?>(null) }
  var emailError by remember { mutableStateOf<String?>(null) }
  var detailsError by remember { mutableStateOf<String?>(null) }
  var submissionNotice by remember { mutableStateOf<String?>(null) }

  fun validateEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
  }

  fun handleSubmit() {
    var hasError = false

    if (fullName.trim().isEmpty()) {
      nameError = "Full Name is required"
      hasError = true
    } else {
      nameError = null
    }

    if (emailAddress.trim().isEmpty()) {
      emailError = "Email Address is required"
      hasError = true
    } else if (!validateEmail(emailAddress)) {
      emailError = "Please enter a valid email format"
      hasError = true
    } else {
      emailError = null
    }

    if (projectDetails.trim().isEmpty()) {
      detailsError = "Please share details about your project"
      hasError = true
    } else {
      detailsError = null
    }

    if (hasError) return

    val recipient = PortfolioConfig.contactEmail
    val subject = "Project Inquiry from $fullName: $selectedService"
    val body = """
      Client Name: $fullName
      Client Email: $emailAddress
      Service Needed: $selectedService
      
      Project Details:
      $projectDetails
    """.trimIndent()

    // 1. Copy inquiry to clipboard as immediate backup
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Project Inquiry", body)
    clipboard.setPrimaryClip(clip)

    // 2. Launch real email client via mailto Intent
    try {
      val mailtoUri = Uri.parse("mailto:$recipient?subject=${Uri.encode(subject)}&body=${Uri.encode(body)}")
      val intent = Intent(Intent.ACTION_SENDTO, mailtoUri).apply {
        putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
      }
      context.startActivity(Intent.createChooser(intent, "Send Project Inquiry"))
      submissionNotice = "Email client opened with prefilled inquiry. Text is also copied to clipboard!"
    } catch (e: Exception) {
      submissionNotice = "Could not open mail app automatically. Inquiry text copied to clipboard! You can email $recipient directly."
    }
  }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 24.dp)
      .testTag("contact_section")
  ) {
    SectionHeader(
      badgeText = "Get In Touch",
      title = PortfolioConfig.contactHeading,
      subtitle = PortfolioConfig.contactHeadline
    )

    Spacer(modifier = Modifier.height(10.dp))

    Text(
      text = PortfolioConfig.contactDescription,
      style = MaterialTheme.typography.bodyLarge.copy(
        color = TextMuted,
        lineHeight = 22.sp
      )
    )

    Spacer(modifier = Modifier.height(20.dp))

    // Contact Form Card
    GlowCard(
      highlightBorder = true,
      backgroundColor = CardBackgroundElevated,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("contact_form_card")
    ) {
      Column(modifier = Modifier.fillMaxWidth()) {
        Text(
          text = "Send a Project Inquiry",
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Full Name Field
        OutlinedTextField(
          value = fullName,
          onValueChange = {
            fullName = it
            if (nameError != null) nameError = null
          },
          label = { Text("Full Name *") },
          isError = nameError != null,
          supportingText = {
            nameError?.let { Text(it, color = ErrorRed, style = MaterialTheme.typography.bodySmall) }
          },
          singleLine = true,
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryAccent,
            unfocusedBorderColor = BorderDefault,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            errorBorderColor = ErrorRed
          ),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("contact_input_fullname")
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email Field
        OutlinedTextField(
          value = emailAddress,
          onValueChange = {
            emailAddress = it
            if (emailError != null) emailError = null
          },
          label = { Text("Email Address *") },
          isError = emailError != null,
          supportingText = {
            emailError?.let { Text(it, color = ErrorRed, style = MaterialTheme.typography.bodySmall) }
          },
          singleLine = true,
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryAccent,
            unfocusedBorderColor = BorderDefault,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            errorBorderColor = ErrorRed
          ),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("contact_input_email")
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Service Needed Dropdown
        ExposedDropdownMenuBox(
          expanded = serviceDropdownExpanded,
          onExpandedChange = { serviceDropdownExpanded = !serviceDropdownExpanded },
          modifier = Modifier.fillMaxWidth()
        ) {
          OutlinedTextField(
            value = selectedService,
            onValueChange = {},
            readOnly = true,
            label = { Text("Service Needed") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = serviceDropdownExpanded) },
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = PrimaryAccent,
              unfocusedBorderColor = BorderDefault,
              focusedTextColor = TextPrimary,
              unfocusedTextColor = TextPrimary
            ),
            modifier = Modifier
              .menuAnchor()
              .fillMaxWidth()
              .testTag("contact_input_service")
          )

          ExposedDropdownMenu(
            expanded = serviceDropdownExpanded,
            onDismissRequest = { serviceDropdownExpanded = false },
            modifier = Modifier.background(CardBackground)
          ) {
            PortfolioConfig.services.forEach { service ->
              DropdownMenuItem(
                text = { Text(service.title, color = TextPrimary) },
                onClick = {
                  selectedService = service.title
                  serviceDropdownExpanded = false
                }
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Project Details Field
        OutlinedTextField(
          value = projectDetails,
          onValueChange = {
            projectDetails = it
            if (detailsError != null) detailsError = null
          },
          label = { Text("Project Details *") },
          placeholder = { Text("Tell me about your project, timeline, and goals...", color = TextMuted) },
          isError = detailsError != null,
          supportingText = {
            detailsError?.let { Text(it, color = ErrorRed, style = MaterialTheme.typography.bodySmall) }
          },
          minLines = 4,
          maxLines = 8,
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryAccent,
            unfocusedBorderColor = BorderDefault,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            errorBorderColor = ErrorRed
          ),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("contact_input_details")
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Honest explanation regarding form delivery
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF0F1B30))
            .border(1.dp, BorderDefault, RoundedCornerShape(8.dp))
            .padding(10.dp),
          verticalAlignment = Alignment.Top
        ) {
          Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = SecondaryAccent,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Direct Transmission: Submitting opens your email application with the inquiry addressed to ${PortfolioConfig.contactEmail} and copies a copy to your clipboard.",
            style = MaterialTheme.typography.bodyMedium.copy(
              color = TextMuted,
              fontSize = 11.sp,
              lineHeight = 16.sp
            )
          )
        }

        if (submissionNotice != null) {
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = submissionNotice!!,
            style = MaterialTheme.typography.bodyMedium.copy(
              color = SuccessGreen,
              fontWeight = FontWeight.Medium,
              fontSize = 12.sp
            )
          )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Submit Button
        Button(
          onClick = { handleSubmit() },
          colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryAccent,
            contentColor = Color.White
          ),
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(1.dp, SecondaryAccent.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .testTag("contact_submit_button")
        ) {
          Icon(
            imageVector = Icons.Default.Send,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Send Inquiry via Email",
            style = MaterialTheme.typography.labelLarge.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 14.sp
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Direct Contact Channels
    Text(
      text = "Direct Channels & Profiles",
      style = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Bold,
        color = TextPrimary
      )
    )

    Spacer(modifier = Modifier.height(12.dp))

    // Email card
    GlowCard(
      backgroundColor = CardBackground,
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(PrimaryAccent.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Email,
              contentDescription = null,
              tint = SecondaryAccent,
              modifier = Modifier.size(18.dp)
            )
          }
          Spacer(modifier = Modifier.width(12.dp))
          Column {
            Text("Email", style = MaterialTheme.typography.labelSmall.copy(color = TextMuted))
            Text(
              PortfolioConfig.contactEmail,
              style = MaterialTheme.typography.bodyMedium.copy(
                color = TextPrimary,
                fontWeight = FontWeight.SemiBold
              )
            )
          }
        }

        IconButton(
          onClick = {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("Email", PortfolioConfig.contactEmail))
            Toast.makeText(context, "Email copied to clipboard!", Toast.LENGTH_SHORT).show()
          }
        ) {
          Icon(
            imageVector = Icons.Default.ContentCopy,
            contentDescription = "Copy email address",
            tint = SecondaryAccent,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }

    // Configurable channels: Only displayed if non-empty!
    if (PortfolioConfig.contactWhatsapp.isNotBlank()) {
      ContactChannelRow("WhatsApp", PortfolioConfig.contactWhatsapp, Icons.Default.Chat)
    }
    if (PortfolioConfig.fiverrProfileUrl.isNotBlank()) {
      ContactChannelRow("Fiverr Profile", PortfolioConfig.fiverrProfileUrl, Icons.Default.OpenInNew)
    }
    if (PortfolioConfig.upworkProfileUrl.isNotBlank()) {
      ContactChannelRow("Upwork Profile", PortfolioConfig.upworkProfileUrl, Icons.Default.OpenInNew)
    }
    if (PortfolioConfig.facebookProfileUrl.isNotBlank()) {
      ContactChannelRow("Facebook", PortfolioConfig.facebookProfileUrl, Icons.Default.OpenInNew)
    }
    if (PortfolioConfig.linkedinProfileUrl.isNotBlank()) {
      ContactChannelRow("LinkedIn", PortfolioConfig.linkedinProfileUrl, Icons.Default.OpenInNew)
    }
  }
}

@Composable
private fun ContactChannelRow(
  label: String,
  urlOrHandle: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector
) {
  val context = LocalContext.current
  GlowCard(
    backgroundColor = CardBackground,
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp),
    onClick = {
      try {
        val uri = if (urlOrHandle.startsWith("http://") || urlOrHandle.startsWith("https://")) {
          Uri.parse(urlOrHandle)
        } else {
          Uri.parse("https://$urlOrHandle")
        }
        context.startActivity(Intent(Intent.ACTION_VIEW, uri))
      } catch (e: Exception) {
        Toast.makeText(context, "Could not open link: $urlOrHandle", Toast.LENGTH_SHORT).show()
      }
    }
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(PrimaryAccent.copy(alpha = 0.2f)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = icon,
            contentDescription = null,
            tint = SecondaryAccent,
            modifier = Modifier.size(18.dp)
          )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Text(label, style = MaterialTheme.typography.labelSmall.copy(color = TextMuted))
          Text(
            urlOrHandle,
            style = MaterialTheme.typography.bodyMedium.copy(
              color = TextPrimary,
              fontWeight = FontWeight.SemiBold
            )
          )
        }
      }
      Icon(
        imageVector = Icons.Default.OpenInNew,
        contentDescription = "Open Link",
        tint = SecondaryAccent,
        modifier = Modifier.size(18.dp)
      )
    }
  }
}
