## Architecture

### Package structure
```
com.example.outdoor/
├── core/
│   └── ui/
│       ├── components/
│       │   ├── AppButton.kt     ← primary pill button (OutdoorBlue, 52dp height)
│       │   └── AppText.kt       ← typed text variants (Body, Label, LabelAccent, Badge, ButtonLabel)
│       └── typography/
│           └── AppTextStyle.kt  ← TextStyle constants (HeadlineLarge, Body, LabelNormal, LabelSemiBold, Badge, ButtonLabel)
└── feature/
    └── onboard/
        └── presentation/
            ├── OnboardScreen.kt
            └── components/
                ├── HeroImage.kt
                └── OnboardActions.kt
```

### Core UI rules
- **Never** hardcode `fontFamily = IBMPlexSerif` in feature code — use `AppTextStyle.*` or `AppText.*`
- **Never** duplicate button shape/color logic — use `AppButton`
- Add new `AppText` variants to `AppText.kt` + matching `AppTextStyle` entry before creating feature-specific text composables
- `AppButton` accepts `containerColor` override for non-primary variants

## graphify

This project has a knowledge graph at graphify-out/ with god nodes, community structure, and cross-file relationships.

Rules:
- For codebase questions, first run `graphify query "<question>"` when graphify-out/graph.json exists. Use `graphify path "<A>" "<B>"` for relationships and `graphify explain "<concept>"` for focused concepts. These return a scoped subgraph, usually much smaller than GRAPH_REPORT.md or raw grep output.
- If graphify-out/wiki/index.md exists, use it for broad navigation instead of raw source browsing.
- Read graphify-out/GRAPH_REPORT.md only for broad architecture review or when query/path/explain do not surface enough context.
- After modifying code, run `graphify update .` to keep the graph current (AST-only, no API cost).
