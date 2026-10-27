package com.example.data

/**
 * =============================================================================
 * CENTRAL EDITABLE CONFIGURATION FOR MUSABBER ALI KHADEM PORTFOLIO
 * =============================================================================
 * You can edit any text, service, skill, project, contact link, or social URL
 * in this file without needing advanced Android or coding knowledge!
 */
object PortfolioConfig {

  // --- 1. PERSONAL INFORMATION ---
  val name = "Musabber Ali Khadem"
  val brandShort = "MAK"
  val websiteTitle = "Musabber Ali Khadem | Digital Creative Portfolio"
  val professionalTitle = "Digital Creative Professional"
  val freelanceStatusBadge = "OPEN TO FREELANCE PROJECTS"

  val heroHeading = "Hi, I'm Musabber Ali Khadem."
  val mainHeadline = "Creative Ideas. Smart Solutions. Digital Growth."
  val introduction = "I help businesses and individuals build a stronger digital presence through creative design, modern websites, digital marketing, AI-powered workflows, and engaging visual content."
  val heroSupportingText = "I create thoughtful digital experiences through graphic design, website development, digital marketing, AI automation, and video content."

  val profilePhotoAlt = "MD. Nasim, Digital Creative Professional"

  // --- 2. ABOUT ME ---
  val aboutHeading = "About Me"
  val aboutSubtitle = "Creativity Meets Technology"
  val biography = "I'm Musabber Ali Khadem, a English Literature graduate with an interest in digital creativity and technology. I enjoy exploring practical digital tools and developing creative solutions for websites, visual content, marketing, and automation. My goal is to help individuals and businesses present their ideas professionally online."

  data class AboutHighlight(val title: String, val description: String)
  val aboutHighlights = listOf(
    AboutHighlight(
      title = "Creative Background",
      description = "English Literature foundation bringing structured storytelling, clear communication, and visual harmony."
    ),
    AboutHighlight(
      title = "Modern Digital Tooling",
      description = "Hands-on experience with web builders, graphic tools, and AI workflows for practical, scalable results."
    ),
    AboutHighlight(
      title = "Client-Centric Mindset",
      description = "Dedicated to understanding exact client requirements and delivering tailored creative assets on time."
    )
  )

  // --- 3. SERVICES ---
  data class ServiceItem(
    val id: String,
    val title: String,
    val description: String,
    val features: List<String>,
    val iconName: String
  )

  val services = listOf(
    ServiceItem(
      id = "graphic-design",
      title = "Graphic Design",
      description = "Create visual designs for branding, social media, promotional materials, and digital communication.",
      features = listOf("Brand identity & logos", "Social media banners & posts", "Flyers & promotional graphics", "Clean vector layouts"),
      iconName = "palette"
    ),
    ServiceItem(
      id = "web-dev",
      title = "Web Development",
      description = "Create responsive and user-friendly websites for individuals and businesses.",
      features = listOf("Responsive mobile-first layouts", "Clean navigation & UI structure", "Speed & performance tuning", "Modern standards"),
      iconName = "code"
    ),
    ServiceItem(
      id = "wordpress-elementor",
      title = "WordPress & Elementor",
      description = "Build and customize WordPress websites and landing pages.",
      features = listOf("Elementor page builder", "Custom theme styling", "Landing pages for lead gen", "Content updates & setup"),
      iconName = "web"
    ),
    ServiceItem(
      id = "digital-marketing",
      title = "Digital Marketing",
      description = "Support digital promotion through creative content and practical marketing solutions.",
      features = listOf("Content strategy & scheduling", "Social media creative assets", "Audience engagement focus", "Targeted promotional designs"),
      iconName = "trending_up"
    ),
    ServiceItem(
      id = "ai-automation",
      title = "AI Automation",
      description = "Explore practical AI-powered workflows to simplify repetitive tasks and improve productivity.",
      features = listOf("AI-assisted content generation", "Workflow experimentation", "Process automation concepts", "Productivity boosters"),
      iconName = "auto_awesome"
    ),
    ServiceItem(
      id = "video-editing",
      title = "Video Editing",
      description = "Create engaging promotional videos and social media content.",
      features = listOf("Promotional clips & reels", "Cut & transition syncing", "Titles, captions & lower thirds", "Visual pacing & audio polish"),
      iconName = "videocam"
    )
  )

  // --- 4. SKILLS & TOOLS ---
  data class SkillCategory(
    val categoryName: String,
    val skills: List<String>
  )

  val skillCategories = listOf(
    SkillCategory(
      categoryName = "Graphic Design",
      skills = listOf("Adobe Photoshop", "Adobe Illustrator", "Canva", "Visual Content Creation")
    ),
    SkillCategory(
      categoryName = "Website Development",
      skills = listOf("WordPress", "Elementor", "HTML & CSS (Applied)")
    ),
    SkillCategory(
      categoryName = "Productivity",
      skills = listOf("Microsoft Word", "Microsoft Excel", "Microsoft PowerPoint")
    ),
    SkillCategory(
      categoryName = "AI & Automation",
      skills = listOf("AI-assisted Content Creation", "AI Workflow Experimentation", "Digital Process Automation")
    ),
    SkillCategory(
      categoryName = "Video & Content",
      skills = listOf("Video Editing", "Promotional Content Creation")
    )
  )

  // --- 5. FEATURED PROJECTS ---
  data class ProjectItem(
    val id: String,
    val title: String,
    val category: String,
    val description: String,
    val tools: List<String>,
    val isConcept: Boolean = true,
    val demoUrl: String = "",
    val detailsUrl: String = ""
  )

  val projectCategories = listOf(
    "All",
    "Graphic Design",
    "Websites",
    "Digital Marketing",
    "AI & Automation",
    "Video Editing"
  )

  val projects = listOf(
    ProjectItem(
      id = "p1",
      title = "Creative Brand Design",
      category = "Graphic Design",
      description = "Complete visual identity concept including logo variants, modern brand guidelines, social media kit, and custom typography pairings.",
      tools = listOf("Adobe Illustrator", "Photoshop", "Brand Strategy"),
      isConcept = true
    ),
    ProjectItem(
      id = "p2",
      title = "Business Website Concept",
      category = "Websites",
      description = "Modern corporate multi-page layout built for smooth user navigation, clear service presentations, and conversion-focused call-to-actions.",
      tools = listOf("WordPress", "Elementor", "Responsive UI"),
      isConcept = true
    ),
    ProjectItem(
      id = "p3",
      title = "Social Media Campaign Concept",
      category = "Digital Marketing",
      description = "Promotional creative suite featuring high-engagement feed posts, story banners, and promotional templates designed to boost brand visibility.",
      tools = listOf("Canva", "Photoshop", "Content Planning"),
      isConcept = true
    ),
    ProjectItem(
      id = "p4",
      title = "AI Workflow Concept",
      category = "AI & Automation",
      description = "Automated prompt and content pipeline concept designed to speed up drafting social captions, product summaries, and recurring digital copy.",
      tools = listOf("AI Workflows", "Prompt Design", "Automation"),
      isConcept = true
    ),
    ProjectItem(
      id = "p5",
      title = "Promotional Video Concept",
      category = "Video Editing",
      description = "Dynamic short-form video edit featuring snappy kinetic typography, rhythmic transitions, sound design accents, and engaging hooks.",
      tools = listOf("Video Editing", "Motion Graphics", "Sound Sync"),
      isConcept = true
    ),
    ProjectItem(
      id = "p6",
      title = "Responsive Landing Page",
      category = "Websites",
      description = "High-conversion landing page structure with hero value propositions, benefit breakdowns, social proof blocks, and frictionless lead forms.",
      tools = listOf("Elementor", "HTML & CSS", "Mobile UX"),
      isConcept = true
    )
  )

  // --- 6. WORK PROCESS ---
  data class ProcessStep(
    val stepNumber: String,
    val title: String,
    val description: String
  )

  val workProcess = listOf(
    ProcessStep("01", "Discover", "Understand the project requirements, target audience, and business goals."),
    ProcessStep("02", "Plan", "Define the structure, visual direction, timeline, and key milestones."),
    ProcessStep("03", "Create", "Develop the agreed digital solution with quality craftsmanship and attention to detail."),
    ProcessStep("04", "Refine & Deliver", "Review the work together, polish final assets, and prepare ready-to-use deliverables.")
  )

  // --- 7. WHY WORK WITH ME ---
  data class WhyMeItem(
    val title: String,
    val description: String,
    val iconName: String
  )

  val whyWorkWithMe = listOf(
    WhyMeItem(
      title = "Clear Communication",
      description = "Regular updates and transparent discussions throughout every step of your project.",
      iconName = "chat"
    ),
    WhyMeItem(
      title = "Creative Problem-Solving",
      description = "Finding practical and attractive solutions tailored specifically to your project's challenges.",
      iconName = "lightbulb"
    ),
    WhyMeItem(
      title = "Attention to Detail",
      description = "Clean alignment, cohesive color harmony, and well-organized file deliverables.",
      iconName = "check_circle"
    ),
    WhyMeItem(
      title = "Focus on Client Requirements",
      description = "Prioritizing your exact objectives to ensure the final output matches your vision.",
      iconName = "target"
    )
  )

  // --- 8. CONTACT & SOCIAL CHANNELS ---
  // Leave empty ("") if not yet available; unconfigured buttons will automatically be hidden!
  val contactEmail = "m.a.khadem7878@gmail.com"
  val contactWhatsapp = ""       // e.g. "https://wa.me/1234567890" or phone number
  val fiverrProfileUrl = ""      // e.g. "https://fiverr.com/your-username"
  val upworkProfileUrl = ""      // e.g. "https://upwork.com/freelancers/~your-id"
  val facebookProfileUrl = ""    // e.g. "https://facebook.com/your-page"
  val linkedinProfileUrl = ""    // e.g. "https://linkedin.com/in/your-profile"

  val contactHeading = "Have a Project in Mind?"
  val contactHeadline = "Let's Build Something Great Together."
  val contactDescription = "Tell me about your project and goals. Let's discuss the right digital solution for your needs."

  val copyrightTagline = "Digital Creative Professional • Empowering brands with design, code, and smart solutions."
}
