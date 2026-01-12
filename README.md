# TMDB Movie App (Android)

## Setup Instructions

1. **Clone the repository**

   ```bash
   git clone <your-repo-url>
   ```

2. **Open the project** in Android Studio

3. **Get TMDB API Key**

   * Create an account at [https://www.themoviedb.org](https://www.themoviedb.org)
   * Generate an API key

4. **Add API key**
   Add the following line to `local.properties`:

   ```
   TMDB_API_KEY=your_api_key_here
   ```

5. **Sync Gradle** and run the app on an emulator or physical device

---

## Architecture Overview

The app follows **Clean Architecture with MVVM** for better scalability and maintainability.

### Layers

**Data Layer**

* Handles API calls and data sources
* Contains:

  * Retrofit API services
  * DTOs (network models)
  * PagingSource
  * Repository implementation
  * Mappers (DTO → Domain)

**Domain Layer**

* Contains business logic models
* Repository interfaces
* Independent of Android framework

**UI Layer**

* Jetpack Compose UI
* ViewModels handle UI logic
* Observes state using Kotlin Flow

### Flow

```
UI (Compose)
  ↓
ViewModel
  ↓
Repository
  ↓
PagingSource / API
```

---

## Libraries Used

* **Kotlin** – Programming language
* **Jetpack Compose** – UI toolkit
* **ViewModel** – UI state management
* **Kotlin Coroutines & Flow** – Asynchronous operations
* **Retrofit** – REST API communication
* **OkHttp** – Network logging & interceptors
* **Paging 3** – Pagination support
* **Coil** – Image loading
* **Material 3** – UI components

