# NotebookLM Android App

⚠️ **WARNING: This project is under active development and may contain bugs or incomplete features. Use at your own risk.**

A modern Android application that integrates with the NotebookLM API to provide an interactive document analysis and chat experience.

## Features

- Create and manage documents
- Chat with documents using AI-powered responses
- View source references for AI responses
- Modern Material Design 3 UI
- Real-time document updates

## Prerequisites

Before running the app, ensure you have the following installed:

1. **Java Development Kit (JDK) 17 or higher**:
   ```bash
   sudo apt update
   sudo apt install openjdk-17-jdk
   ```

2. **Android SDK Command-line tools**:
   ```bash
   sudo apt install android-sdk-cmdline-tools
   ```

3. **Gradle**:
   ```bash
   sudo apt install gradle
   ```

4. **Android Device or Emulator**:
   - Enable Developer Options on your Android device
   - Enable USB debugging
   - Connect device via USB
   - Accept USB debugging prompt on device

## Setup

1. **Clone the repository**:
   ```bash
   git clone [repository-url]
   cd NotebookLM
   ```

2. **Configure Android SDK path**:
   - Edit `local.properties` and set your Android SDK path:
     ```
     sdk.dir=/path/to/your/Android/Sdk
     ```

3. **Set environment variables**:
   ```bash
   export ANDROID_HOME=$HOME/Android/Sdk
   export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
   ```

4. **Add your NotebookLM API key**:
   - Open `app/src/main/java/com/example/notebooklm/config/Config.kt`
   - Replace `YOUR_API_KEY_HERE` with your actual NotebookLM API key

## Running the App

### Using the Script (Recommended)

1. Make the script executable:
   ```bash
   chmod +x build_and_run.sh
   ```

2. Run the script:
   ```bash
   ./build_and_run.sh
   ```

### Manual Build and Run

1. Build the app:
   ```bash
   ./gradlew assembleDebug
   ```

2. Install on device:
   ```bash
   ./gradlew installDebug
   ```

3. Launch the app:
   ```bash
   adb shell am start -n com.example.notebooklm/.MainActivity
   ```

## Using the App

1. **Creating a Document**:
   - Tap the "+" button in the top-right corner
   - Enter a title for your document
   - Add your document content
   - Tap "Create" to process the document

2. **Chatting with Documents**:
   - Select a document from the list on the left
   - Type your question in the chat input
   - Press the send button
   - View AI responses with source references

## Troubleshooting

1. **Build Issues**:
   - Run `./gradlew --info` for detailed error messages
   - Ensure all prerequisites are installed
   - Verify Android SDK path in `local.properties`

2. **Device Connection Issues**:
   - Run `adb devices` to verify device connection
   - Check USB debugging is enabled
   - Try reconnecting the device

3. **API Issues**:
   - Verify your API key is correct
   - Check internet connection
   - Ensure NotebookLM API is enabled in Google Cloud Console

## Project Structure

```
NotebookLM/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/notebooklm/
│   │       │   ├── config/
│   │       │   ├── data/
│   │       │   ├── ui/
│   │       │   └── MainActivity.kt
│   │       └── res/
│   └── build.gradle
├── build.gradle
├── settings.gradle
├── local.properties
└── build_and_run.sh
```

## Contributing

This project is under active development. Feel free to submit issues and enhancement requests!

## License

[Add your license information here]

## Acknowledgments

- Google NotebookLM API
- Android Jetpack Compose
- Material Design 3 