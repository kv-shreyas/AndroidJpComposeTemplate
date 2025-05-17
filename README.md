# 🚀 Jetpack Compose Template Android Development Guide

### 📁 Project Structure Overview

Project follows a clean MVVM architecture with SOLID principles, structured as:

---

### ✅ Responsibilities by Layer
📄 [📘 Android Development Guide (Pdf)](docs/Project_Architecture_Guide.pdf)

| Layer        | Responsibility                                             |
|--------------|------------------------------------------------------------|
| UI           | Pure Composables, no business logic                        |
| ViewModel    | Manages UI state, calls UseCases                           |
| UseCase      | Business logic only, interacts with Repositories           |
| Repository   | Abstracts remote/local data source logic                   |
| Data Sources | Retrofit API, Room DB, SharedPreferences                   |

---

###  🔁 Data Flow Summary
- UI → ViewModel → UseCase → Repository → Remote/Local

---

## ✅ Rules & Best Practices

- 📦 Single Responsibility Principle in all layers
- ❌ No logic inside Composables
- 🧪 UseCases must be unit-testable
- 🔒 Immutable UI state (StateFlow)
- 💉 Use Dependency Injection (Hilt)
- 🔄 Map DTOs to Domain Models in Repositories

---

## 🧭 Coding Guidelines
- Details on UI, ViewModel, UseCase, Repository layers

### 1. UI Layer (Jetpack Compose)
- ✅ Contains only UI logic
- ❌ No direct calls to UseCases or Repositories
- 🎯 Observe ViewModel via `StateFlow` or `collectAsState`

### 2.ViewModel Layer
- 🧠 One ViewModel per screen
- 👀 Depends only on UseCases (not repositories directly)
- 📊 Owns UI state and exposes it to the UI

### 3.UseCases
- 💼 Perform business logic
- ✅ Only depend on repository interfaces
- ❌ Should not depend on Android SDK/Jetpack


### 4.Repository
- 📁 Implementation resides in data/repository/
- ⚙️ Separates remote and local operations

### 5.Nav-graph Usage
- Ex: NavGraphUtil.gotoScreen(navController, Constants.NavDestinationScreens.REGISTER_SCREEN, true)

---