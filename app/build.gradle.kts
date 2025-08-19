import javax.xml.parsers.DocumentBuilderFactory
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.dokka")  // ✅ add this

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
    implementation("org.jetbrains.dokka:gfm-plugin:1.9.20")
    implementation("org.jetbrains.dokka:javadoc-plugin:1.9.20")
    implementation("org.jetbrains.dokka:jekyll-plugin:1.9.20")
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

//layout.buildDirectory --This Build Folder ..That’s expected because Dokka writes them into your build/ folder, and by default build/ is ignored by Git (via .gitignore).
// outputDirectory.set(projectDir  Use Separate Directory
// ---------------------------------------------------------------
// Centralized Dokka defaults
fun DokkaTask.configureDokkaDefaults() {
    val appName = getAppName() // your custom util (fallback to applicationId if needed)
    moduleName.set("$appName v${android.defaultConfig.versionName}")
    dokkaSourceSets.configureEach {
        reportUndocumented.set(true)       // warn if missing docs
        skipEmptyPackages.set(true)        // skip empty packages
        suppressInheritedMembers.set(false) // show inherited members
        skipDeprecated.set(true)           // ✅ skip deprecated members here
    }
}
tasks.dokkaGfm.configure {
    val appName = getAppName() // your custom util (fallback to applicationId if needed)
    moduleName.set("$appName v${android.defaultConfig.versionName}")
    dokkaSourceSets.configureEach {
        reportUndocumented.set(true)       // warn if missing docs
        skipEmptyPackages.set(true)        // skip empty packages
        suppressInheritedMembers.set(false) // show inherited members
        skipDeprecated.set(true)           // ✅ skip deprecated members here
    }
    pluginsMapConfiguration.set(
            mapOf(
                    "org.jetbrains.dokka.base.DokkaBase" to
                            """{ "separateInheritedMembers": true }"""
                 )
                               )
}
tasks.dokkaHtml.configure {
    val appName = getAppName() // your custom util (fallback to applicationId if needed)
    moduleName.set("$appName v${android.defaultConfig.versionName}")
    dokkaSourceSets.configureEach {
        reportUndocumented.set(true)       // warn if missing docs
        skipEmptyPackages.set(true)        // skip empty packages
        suppressInheritedMembers.set(false) // show inherited members
        skipDeprecated.set(true)           // ✅ skip deprecated members here
    }
    pluginsMapConfiguration.set(
            mapOf(
                    "org.jetbrains.dokka.base.DokkaBase" to
                            """{ "separateInheritedMembers": true }"""
                 )
                               )
}





// --------------------
// Different Outputs
// --------------------

// HTML docs → project-root/dokka/html
tasks.dokkaHtml.configure {
    outputDirectory.set(projectDir.resolve("dokka/html"))
}

// Javadoc style HTML → project-root/dokka/javadoc
tasks.dokkaJavadoc.configure {
    outputDirectory.set(projectDir.resolve("dokka/javadoc"))
}

// GitHub Flavored Markdown → project-root/dokka/gfm
tasks.dokkaGfm.configure {
    outputDirectory.set(projectDir.resolve("dokka/gfm"))
}

// Jekyll Markdown → project-root/dokka/jekyll
tasks.dokkaGfm.configure {
    outputDirectory.set(projectDir.resolve("dokka/jekyll"))
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

/**
 * ./gradlew dokkaHtml        # → HTML
 * ./gradlew dokkaMarkdown    # → Plain Markdown
 * ./gradlew dokkaGfm         # → GitHub Flavored Markdown (.md)
 * ./gradlew dokkaJavadoc     # → Javadoc-style HTML
 * ./gradlew dokkaJekyll      # → Jekyll Markdown
 *
 */

