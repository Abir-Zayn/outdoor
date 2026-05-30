# Architecture Decision — Clean Architecture for Outdoor

## The Product

Outdoor is a hotel booking and reservation app for Android, built with Jetpack Compose. It serves travelers who want to find, compare, and book rooms across different hotels — from browsing the home feed to viewing hotel details, selecting rooms, and completing a booking. The app also includes authentication, a chat system, profile management, and a bookings history.

The product is designed to grow. It currently has eight feature areas: onboarding, authentication, home browsing, hotel details, room selection, room details, bookings, and profile. New features such as payments, reviews, notifications, and loyalty programs are expected to arrive in future cycles.

## Why This Decision Matters

Choosing an architecture is not a technical exercise — it is a product decision. The architecture determines how fast the team can ship new features, how safely they can change existing ones, and how confidently they can scale the app as user demand grows. The wrong choice creates friction: slow onboarding for new developers, fragile code that breaks when touched, and painful refactors every time the product pivots.

## Why Clean Architecture Over the Alternatives

### MVC (Model-View-Controller)

MVC ties the controller directly to the view and to the model. In a mobile context this means:

- **The screen owns the logic.** Every time the product adds a new screen, the controller for that screen grows with it. The hotel detail screen in Outdoor already handles image carousels, amenity lists, policies, reviews, and a room-selection CTA. Under MVC, all of that lives in one controller. When the product asks for a "write a review" feature on that same screen, the controller expands further.

- **Controllers are not reusable across surfaces.** Outdoor already shows hotel cards on the home feed and inside the detailed view. MVC makes it difficult to share controller logic between these two surfaces without duplicating code or creating awkward inheritance chains.

- **Testing is screen-bound.** MVC controllers are tightly coupled to the view lifecycle. Writing a test for "show the correct price when a room is selected" requires simulating the entire screen, not just the selection logic.

For a product that expects to reuse business logic across multiple screens and eventually across different entry points (widgets, deep links, notifications), MVC creates a tangle that gets worse with every feature.

### MVVM (Model-View-ViewModel)

MVVM is closer to what Outdoor needs — it separates state from UI, and Compose naturally encourages this pattern. But MVVM has product-level limitations:

- **ViewModels become God objects.** A ViewModel under MVVM typically owns both business logic and presentation state. As the product matures, the ViewModel for "home screen" would need to know about search queries, hotel lists, filtering, sorting, pagination, and user preferences. This is already visible in the current HomeScreen — it manages destination, dates, and guest counts alongside hotel lists. MVVM provides no enforced boundary between "what the product does" and "how the product shows it."

- **No data layer isolation.** MVVM leaves the data source as an implementation detail of the ViewModel. When Outdoor adds a real backend, the team would scatter network calls, caching, and data mapping across every ViewModel. Changing the API contract or swapping the cache strategy means touching every ViewModel individually.

- **Feature boundaries blur.** MVVM organizes by screen, not by feature. Outdoor's product roadmap includes cross-feature workflows: "book a room from a notification" or "re-book from your chat conversation." MVVM makes these cross-cutting flows painful because each ViewModel only knows its own screen.

MVVM works well for simple apps with a small number of screens. Outdoor is not that app — it already has eight features with deep navigation flows and plans for more.

### TDD (Test-Driven Development)

TDD is a discipline, not an architecture. It answers "how do we write code?" not "how do we organize code?" The question Outdoor faces is architectural — how to structure the codebase so that features can be added, changed, and scaled without the system collapsing under its own weight.

TDD can be applied within any architecture. In fact, Clean Architecture makes TDD easier because the separation of concerns gives you natural test boundaries: domain logic can be tested without any Android framework, presentation logic can be tested without a real UI, and data logic can be tested without a real server.

Choosing TDD alone without an architectural foundation would mean well-tested code that is still hard to change, because the tests would be coupled to a structure that does not isolate business rules from presentation or data.

### Clean Architecture

Clean Architecture was chosen because it aligns with what the product needs at every stage of its growth:

- **Feature independence.** Each feature (home, auth, bookings, etc.) is a self-contained module with its own data, domain, and presentation layers. When the product owner says "add a loyalty points banner to the home screen," the change lives inside the home feature. No other feature is affected. This is already reflected in the package structure: every feature owns its own presentation, and the empty `data` and `domain` directories are placeholders for the layers that will carry business rules and data sources.

- **Business rules live in one place.** The domain layer — currently empty but structured to be filled — will hold use cases like "search available rooms," "calculate total booking cost," or "validate registration input." These rules are independent of whether the user sees them on a phone, a tablet, or a foldable. Outdoor already supports adaptive layouts (the login and register screens detect `maxWidth > 600dp` for tablet/landscape). Clean Architecture ensures that the same business rule powers every layout variant without duplication.

- **Data source flexibility.** The data layer will abstract where information comes from. Today, hotel data is hardcoded in sample objects. Tomorrow it will come from a remote API with local caching. The product team does not need to know or care about this transition — it is an implementation detail confined to the data layer. Swapping a mock for a real backend does not touch presentation or domain code.

- **Testability by structure, not by effort.** Because domain logic has no Android or UI dependencies, it can be tested with plain unit tests. Because presentation logic owns only state and delegates to domain, it can be tested without rendering a screen. Because data logic owns only source mapping, it can be tested without a running server. This is not extra work — it is a structural guarantee.

- **Onboarding speed.** A new developer joining the team can understand one feature at a time. They look at the `auth` feature and see its screens, its business rules, and its data sources all in one place. They do not need to understand the entire app to contribute to one feature.

## Improvements Needed

The current codebase shows the right structure at the package level but has gaps that must be closed before the product scales further:

1. **Fill the domain layer.** Every feature that currently holds business logic in presentation (validation in RegisterScreen, search state in HomeScreen) needs that logic extracted into domain use cases. This is the single most important investment — it prevents the presentation layer from becoming the dump for all product rules.

2. **Fill the data layer.** Replace hardcoded sample data with repository implementations that abstract the source. The product will ship with real data, and the data layer is where that transition happens without disrupting the rest of the app.

3. **Replace screen-index navigation.** The current `MainActivity` navigates by integer index (`currentScreen = 0, 1, 2...`). This is fragile — adding or reordering screens breaks every index reference. A navigation layer (type-safe routes) should replace this so that the product team can reorder or insert screens without engineering risk.

4. **State management centralization.** Features currently hold state with `mutableStateOf` inside composables. As state grows more complex (pagination, offline sync, conflict resolution), this approach does not scale. A ViewModel or reducer per feature should own state, leaving composables as pure renderers.

5. **Cross-feature communication.** When bookings need to know about auth state, or chat needs to reference room details, features need a defined contract — not direct imports of each other's internals. The domain layer's use cases serve as this contract: feature A depends on feature B's domain interface, not its presentation or data.

6. **Shared UI through core, not duplication.** The `core/ui` package already centralizes `AppButton`, `AppText`, `AppTextField`, and typography. This discipline must continue — every new visual pattern (loading indicators, empty states, error banners) should be added to core before being used in features. This keeps the product visually consistent and changes fast when the design system updates.

## Summary

Clean Architecture was chosen because it is the only pattern among the candidates that guarantees feature independence, business rule isolation, and data source flexibility — the three product qualities that determine whether Outdoor can ship fast, change safely, and scale confidently. MVC, MVVM, and TDD each solve part of the problem; Clean Architecture solves the whole problem by making the structure of the code match the structure of the product.
