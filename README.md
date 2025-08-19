
# MyApp – DOKKA Configuration

A simple Android app built with **Kotlin** that allows users to create, edit, and organize notes with cloud sync and reminders.

## 🚀 Features
- ✅ Set-up For Dokka Configuration

## 🛠️ Tech Stack
- **Language:** Kotlin

## ⚙️ Setup Instructions
1. Clone the repo:
   ```bash
   https://github.com/chandra1234456/Dokka-Documentation.git

---
```
run this in terminal
./gradlew dokkahtmltest

```

### 📘 Dokka Output Formats

- **dokkaHtml** →  
  Generates a **rich, navigable HTML website** with built-in search.  
  ✅ Best for hosting on a web server or opening locally in a browser.

- **dokkaMarkdown** →  
  Produces `.md` files for each package/class.  
  ✅ Best for GitHub, GitLab, Bitbucket, or project wikis.  
  *(Can later convert Markdown → PDF with Pandoc or Typora.)*

- **dokkaJavadoc** →  
  Creates **Javadoc-style HTML**.  
  ✅ Useful when mixing **Java + Kotlin** or if you need docs in Javadoc format.

- **dokkaGfm (GitHub Flavored Markdown)** →  
  Markdown output optimized for **GitHub rendering** (tables, links, code blocks look better).  
  ✅ Best for GitHub repos (`README.md`, `docs/`, or Wikis).

- **dokkaJekyll** →  
  Generates output compatible with **Jekyll static site generators**.  
  ✅ Perfect if you want to publish docs via **GitHub Pages** or another Jekyll-based site.

  
# 📚 Dokka Output Formats

## 1. dokkaHtml
    → Rich, navigable HTML website with search & navigation.
    ✅ Best for hosting on a web server or opening locally in a browser.

## 2. dokkaMarkdown
    → Generates .md files for each package/class.
    ✅ Best for GitHub, GitLab, Bitbucket, or wikis.
   💡 You can later convert Markdown → PDF (Pandoc, Typora, or plugin).

## 3. dokkaJavadoc
    → Javadoc-style HTML output.
    ✅ Useful if your project mixes Java/Kotlin or needs Javadoc-like docs.

## 4. dokkaGfm (GitHub Flavored Markdown)
    → Markdown output tuned for GitHub rendering.
    ✅ Looks better in GitHub repos.

## 5. dokkaJekyll
   → Outputs docs in Jekyll format.
  ✅ Great if you want to integrate into static site generators.


## 6. 📚 Usage Examples
Show **how to use main functionalities** (like API calls or UI flows).

```markdown
## 📚 Usage


```
## 🔧 Troubleshooting
- **App crashes at start?** → Check if `google-services.json` is added
- **Ads not showing?** → Use Google test Ad IDs during development
- **Remote Config not updating?** → Clear cache and call `fetchAndActivate()`

## 📄 License
This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.
