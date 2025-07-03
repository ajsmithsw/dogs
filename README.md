### Alex Smith | Technical Task
I took a mostly conventional approach to this exercise, making use of popular frameworks (such as Hilt for dependency injection) and architectural patterns (MVVM). I have made use of Jetpack Compose for the UI layer, separated my domain layer into a separate module, and demonstrated unit testing and instrumented testing examples. I have opted to use Glide for handling image display as it's highly efficient and easy to use. For testing I have chosen MockK to mock dependencies, and loosely taken a BDD approach to test structure. I have included examples of both unit testing and UI testing principles.

## Tech stack
- Dependency Injection: Hilt
- Networking: Retrofit / OkHttp / Gson
- Architecture: Jetpack components (ViewModel etc.)
- UI:
  - Jetpack Compose
  - Compose Navigation
  - Glide
- Mock testing framework: MockK

## Testing
  - Unit tests (JUnit)
  - UI tests (JUnit, Compose), including automated integration testing

## Architecture
- Multi-module architecture:
  - :app (main app module)
  - :data (domain layer)
- Single activity
- MVVM architecture:
  - Service -> Repository -> ViewModel -> UI
- Uni-directional data flow; State and Events pattern

## Design considerations
- Stock material design theme
- Lazy grid layout on detail screen
- Shared element transitions (Breed name -> page title)

## AI disclosure
In order to save time on boilerplate and trivial tasks, I made use of:
- Gemini in Android Studio (Code completion)
- ChatGPT (Generating DTO models from API documentation)

All decisions including architecture, testing, tech stack and design are my own.