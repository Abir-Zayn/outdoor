# Outdoor

A full-stack travel booking app — book hotels, apartments, and more.

**Stack:** Kotlin + Jetpack Compose (Android) · Django REST (backend)

---

## Android App (`/application`)

| Layer | Tech |
|-------|------|
| Language | Kotlin |
| UI | Jetpack Compose (no XML) |
| Build | AGP 9.2.1 · Gradle 9.4.1 |
| Min SDK | 24 (Android 7) |
| Theme | Material3 · IBM Plex Serif · brand colors |

### Brand Colors
| Name | Hex |
|------|-----|
| Blue (primary) | `#0759FF` |
| Gold (accent) | `#E4AF16` |
| Black | `#010101` |
| Gray | `#797979` |
| Light Gray | `#E0E0E0` |
| Near White | `#F6F6F6` |

### Screens built
- [x] Splash Screen — blue bg, "Outdoor." + logo, fade-in, 2.5s → home

---

## Backend (`/backend/django-reservation-system`)

Django REST API. See [`backend/README.md`](backend/django-reservation-system/README.md) for setup.
