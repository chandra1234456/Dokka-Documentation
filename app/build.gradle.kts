import javax.xml.parsers.DocumentBuilderFactory

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.dokka") version "1.9.20"  // ✅ add this
}
subprojects {
    apply(plugin = "org.jetbrains.dokka")
}
android {
    namespace = "com.chandra.practice.dokkadocs"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.chandra.practice.dokkadocs"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0-SNAPSHOT"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt") ,
                    "proguard-rules.pro"
                         )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}


/*
 * 📝 Dokka Setup   This Gradle configuration enables **Dokka** to generate project documentation  in multiple formats (HTML & Markdown).
 * ./gradlew dokkaHtml → HTML docs    ./gradlew dokkaMarkdowntest → Markdown docs
 *  📌 Usage:   Run the following Gradle tasks: ./gradlew dokkaHtml   → Generates HTML docs ---- ./gradlew dokkaMarkdowntest  → Generates Markdown docs
 *  📂 Output Locations:   HTML     → app/build/dokka/htmltest/index.html   --Markdown → app/build/dokka/markdowntest/*.md
 *  - Open `index.html` in a browser for a rich, navigable UI. - Use Markdown output for GitHub, wikis, or PDF export.*/

//--------------------------------------
// ---------------------------------------------------------------
// 🔹 Task: dokkaHtml (Built-in)
//
// • Format: Generates HTML documentation (with CSS, search, navigation UI).
// • Output: app/build/dokka/htmltest
// • Best for: Developers browsing docs in a browser.
//
// ⚙️ Config:
//   - suppressInheritedMembers → hides inherited methods (e.g., AppCompatActivity.onCreate())
//   - skipEmptyPackages → removes empty packages
//   - reportUndocumented → warns about undocumented code
// ---------------------------------------------------------------
 */

// ---------------------------------------------------------------
// 🔹 Task: dokkaMarkdowntest (Custom)
//
// • Format: Generates Markdown documentation (.md files).
// • Output: app/build/dokka/markdowntest
// • Best for: Documentation on GitHub, wikis, or converting to PDF.
//
// ⚙️ Config:
//   - suppressInheritedMembers → hides inherited methods
//   - reportUndocumented → warns about undocumented code
//   - (skipEmptyPackages not enabled, so empty packages may appear)
//
// ℹ️ About Markdown:
//   Markdown is a lightweight text formatting language.
//   It’s easy to read in plain text and can be converted into HTML, PDF, DOCX, etc.
// ---------------------------------------------------------------
fun org.jetbrains.dokka.gradle.DokkaTask.configureDokkaDefaults() {
    val appName = getAppName()
    moduleName.set("$appName v${android.defaultConfig.versionName}") //APP Name & Version

    dokkaSourceSets.configureEach {
        //includeNonPublic.set(false)        // keep it public only
        reportUndocumented.set(true)       // warn if you forgot docs
        //skipEmptyPackages.set(true)     // cleaner output
        suppressInheritedMembers.set(true) // hide inherited Android/Java methods
    }
}

tasks.register<org.jetbrains.dokka.gradle.DokkaTask>("dokkaHtmlTest") {
    outputDirectory.set(layout.buildDirectory.dir("dokka/htmlTest"))
    configureDokkaDefaults()
}

tasks.register<org.jetbrains.dokka.gradle.DokkaTask>("dokkaMarkdownTest") {
    outputDirectory.set(layout.buildDirectory.dir("dokka/markdownTest"))
    configureDokkaDefaults()
}

tasks.register<org.jetbrains.dokka.gradle.DokkaTask>("dokkaMyJavadoc") {
    outputDirectory.set(layout.buildDirectory.dir("dokka/myJavadoc"))
    configureDokkaDefaults()
}

tasks.register<org.jetbrains.dokka.gradle.DokkaTask>("dokkaMyGfmDocs") {
    outputDirectory.set(layout.buildDirectory.dir("dokka/gfmDocs"))
    configureDokkaDefaults()
}

tasks.named<org.jetbrains.dokka.gradle.DokkaTask>("dokkaJekyll") {
    outputDirectory.set(layout.buildDirectory.dir("dokka/jekyll"))
    configureDokkaDefaults()
}

/**
 * ✅ Summary: Recommendation:
 *   - dokkaHtml → Rich, browser-friendly docs with navigation.
 *   - dokkaMarkdowntest → Text-based docs for version control & sharing.
 *   For PDF
 *   -dokkaHtml -> If you want a developer-friendly PDF → go with Markdown + Pandoc.
 *   - dokkaMarkdowntest →If you want a pretty UI-styled PDF → go with HTML(Open That HTML IN Chrome) → (In Chrome/Edge → press Ctrl + P → Save as PDF) Print to PDF.
 */


fun getAppName(): String {
    val stringsFile = file("src/main/res/values/strings.xml")
    if (!stringsFile.exists()) return project.name

    val factory = DocumentBuilderFactory.newInstance()
    val builder = factory.newDocumentBuilder()
    val doc = builder.parse(stringsFile)
    val nodeList = doc.getElementsByTagName("string")

    for (i in 0 until nodeList.length) {
        val node = nodeList.item(i)
        if (node.attributes?.getNamedItem("name")?.nodeValue == "app_name") {
            return node.textContent
        }
    }
    return project.name
}

