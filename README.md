# TMDB Movie App (Android)

## Setup Instructions

1. **Clone the repository**

   ```bash
   git clone <your-repo-url>
   ```

2. **Open the project** in Android Studio

3. **Get TMDB Read Access Token**

Create an account at: https://www.themoviedb.org

Go to Settings → API

Generate a Read Access Token (v4 auth)

Note: This project uses the Read Access Token via request headers, not an API key as a query parameter.

4. Add Read Access Token

Add the following line to your local.properties file
(Do NOT commit this file):

   ```bash
   TMDB_READ_ACCESS_TOKEN=your_read_access_token_here
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




🔐 Security Note

TMDB Read Access Token is passed via Authorization headers

Tokens are stored in local.properties

Sensitive data is excluded from version control
