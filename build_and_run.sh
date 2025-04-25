#!/bin/bash

# Check if Android SDK is set
if [ -z "$ANDROID_HOME" ]; then
    echo "ANDROID_HOME is not set. Setting it to default location..."
    export ANDROID_HOME=$HOME/Android/Sdk
    export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
fi

# Check if device is connected
if ! adb devices | grep -q "device$"; then
    echo "No Android device connected. Please connect a device and enable USB debugging."
    exit 1
fi

# Build the app
echo "Building the app..."
./gradlew assembleDebug

# Install on device
echo "Installing on device..."
./gradlew installDebug

# Launch the app
echo "Launching the app..."
adb shell am start -n com.example.notebooklm/.MainActivity

echo "Done!" 