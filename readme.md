# TruAssessment App

Welcome to the TruAssessment App repository! This project is designed to showcase a modern Android application architecture using the Model-View-Intent (MVI) pattern. Below, you'll find detailed information about the architecture, design patterns, and third-party libraries used in this project. At the bottom you'll find why I chose flows over RxJava

## Architecture Overview

The TruAssessment App is built using the MVI (Model-View-Intent) pattern. MVI is designed to provide a unidirectional data flow, making it easier to manage state and handle user interactions in a predictable manner.

The folder structure follows a feature-based organization. Each feature is contained within its own package and includes the appropriate architectural layers: ui, data, and domain.

Features are restricted to accessing only their own internal packages or the shared common package. This design enforces loose coupling and promotes modularity across the codebase.

### Layers and Abstraction
- **Data Layer**: Provides datasources and repo implmentations, dtos, mappers, api definition, and proto serializers

- **Domain Layer**: Contains business logic in the form of usecases and data models, also provides interfaces for datasources and repos for the data layer to implement

- **UI Layer**: Contains the presentation logic, providing screens, viewmodels, states, intents, and home specific components

## Third-Party Libraries

The project utilizes several third-party libraries to enhance functionality and streamline development:

| Library             | Purpose                                                |
|---------------------|--------------------------------------------------------|
| Dagger Hilt         | Dependency injection framework for managing components |
| Kotlin Coroutines   | Asynchronous programming with lightweight threads      |
| Retrofit            | Type-safe HTTP client for network operations          |
| Coil                | Lightweight image loading library                      |
| Jetpack Compose     | Declarative UI toolkit for building native UIs         |
| AndroidX Libraries  | Core support libraries (e.g., lifecycle, ViewModel)    |
| Protobuf            | Efficient serialization using Protocol Buffers         |
| Datastore           | Data storage solution for key-value or typed objects   |

## Note on Choosing Flows instead of RxJava
I prefer Flows because they're simpler to work with, and I have more experience using them. They're lightweight, coroutine-first, and integrate naturally with Jetpack Compose, which makes them a better fit for modern Android development.

They also help reduce boilerplate code. Something that I really appreciate, because excessive boilerplate can be frustrating. On top of that, Flows offer cleaner, more straightforward syntax, making the code easier to read and maintain.
