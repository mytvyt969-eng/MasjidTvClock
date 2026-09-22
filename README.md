# Masjid TV Clock

An Android TV app for Jama Masjid KODWATAND, Lalpania: shows all 5 daily
prayers (adhan + iqamah), Jummah time, Fajr start/end, sunrise/sunset, an
analog clock and a live countdown to the next prayer - with the whole screen
automatically changing mood through the day (pre-dawn violet, bright morning
blue, golden afternoon, sunset orange, night navy), matching the five
reference mockups.

## What's inside

```
app/src/main/java/com/masjid/tvclock/
  data/     MosqueConfig.kt        <- edit mosque name, location, iqamah times here
            PrayerSchedule.kt      <- data models
  logic/    PrayerTimeCalculator.kt<- calculates adhan times on-device (Adhan library)
            HijriDateProvider.kt   <- Hijri date, on-device
            ThemeEngine.kt         <- decides current phase + countdown
  ui/       MainScreen.kt          <- full screen layout
            SkyBackground.kt       <- gradient sky, stars, sun/moon, mosque silhouette
            ClockFace.kt            <- analog clock
            PrayerCard.kt / Icons.kt / PhaseVisuals.kt
  MainActivity.kt                  <- fullscreen, keeps TV screen on
  BootReceiver.kt                  <- auto-launches after the TV boots
```

Everything runs offline: prayer times are calculated on the device from
latitude/longitude using the open-source Adhan library, so there's no server
and no internet dependency once installed.

## Build on GitHub

The repository includes a GitHub Actions workflow that builds a debug APK automatically.
