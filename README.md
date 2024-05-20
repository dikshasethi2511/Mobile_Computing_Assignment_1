# Navigation App

## Overview

The Navigation App is an Android application designed to provide users with a seamless journey tracking experience. The app allows users to monitor their progress, view upcoming stops, and easily switch between metric and imperial units. With a clean and intuitive user interface, users can navigate through their journey effortlessly.

## Features

### Stop Tracking

- Displays a list of stops along the journey.
- Highlights the current stop with a yellow background.
- Marks covered stops with a green background.

### Journey Details

- Shows the total distance covered.
- Displays the total distance left.
- Includes a progress bar indicating the overall completion of the journey.

### Unit Switching

- Allows users to switch between metric (kilometers) and imperial (miles) units.

### Buttons Row

- Provides intuitive buttons for various actions:
  - Mark reaching a stop.
  - Switch between metric and imperial units.
  - Restart the journey.

## Implementation Details - Composable Functions

### MainActivity

The main activity initializes the app and sets the content to the `MyApp` composable function.

### StopElement

Represents a single stop element with customizable background colors based on the stop's status (current, covered, or default).

### StopsColumn

Displays a list of stops using either a `LazyColumn` (for more than 10 stops) or a regular `Column`.

### JourneyDetails

Displays total distance covered, total distance left, and a progress bar.

### TopButtonsRow

Contains buttons for marking reaching a stop, switching units, and restarting the journey.

### MyApp

The main composable function orchestrating the UI layout and managing journey-related states.

### fetchNextStopDistance and getTotalDistance

Helper functions to retrieve the distance for the next stop and calculate the total journey distance.

### stopsData

Defines the list of stops with associated name and distance resources.

## Usage

1. Run the app on an Android device or emulator.
2. Interact with the buttons to mark reaching a stop, switch units, and restart the journey.
3. Observe the dynamic updates to the UI reflecting the journey progress.

## Preview

Check out the preview functions to get a glimpse of how different components look in the app.

### Note

- The app is currently configured with a sample set of 12 stops. Uncomment the alternative `stopsData` definition if you want to use a shorter list.

