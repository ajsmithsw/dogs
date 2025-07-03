### Alex Smith | Technical Task
I took a mostly conventional approach to this exercise, making use of popular frameworks (such as Hilt for dependency injection) and architectural patterns (MVVM). I have made use of Jetpack Compose for the UI layer, separated my domain layer into a separate module, and demonstrated unit testing and instrumented testing examples. I have opted to use Glide for handling image display as it's highly efficient and easy to use. For testing I have chosen MockK to mock dependencies, and loosely taken a BDD approach to test structure.

## Tech stack
- Dependency Injection: Hilt
- Networking: Retrofit / OkHttp / Gson
- Images: Glide
- UI:
  - Jetpack Compose
  - Compose Navigation
- Testing:
  - Unit tests (JUnit)
  - Android tests (JUnit, Compose)
  - Mocking framework: MockK

## Architecture
- Multi-module architecture:
  - :app (main app module)
  - :data (domain layer)
- Single activity
- MVVM architecture:
  - Service -> Repository -> ViewModel -> UI

## Design considerations
- Basic material design theme
- Lazy grid layout on detail screen
- Shared element transitions (Breed name -> page title)