# 🐕 APIConnectApp - Random Dog Gallery

A modern Android application that fetches random dog images from the Dog CEO API.

---

## 📝 Reflection

### Which API did you choose and why?

I chose the Dog CEO API because it's free, doesn't require authentication, and returns simple JSON responses that are perfect for learning. Plus, who doesn't love looking at cute dogs? The API is reliable and fast, which made development smooth, and the visual nature of the content makes the app fun and engaging to use.

### How did you implement data fetching and JSON parsing?

I used Retrofit to handle the network requests and Gson to automatically parse the JSON responses into Kotlin data classes. The API calls run on coroutines using `lifecycleScope`, which keeps everything asynchronous and prevents the UI from freezing. When a user taps the button, the app makes a suspend function call to fetch the dog image URL, then Coil loads the image with a nice crossfade animation. Everything is set up as a singleton pattern so the Retrofit instance is reused efficiently throughout the app.

### What challenges did you face when handling errors or slow connections?

The main challenge was making sure the app stayed responsive during slow network connections. I added a progress bar that shows while loading and disabled the buttons to prevent multiple requests at once. Handling different types of errors was tricky at first - I had to distinguish between network failures, API errors, and parsing issues to give users helpful feedback. I also had to be careful with the activity lifecycle to prevent crashes if the user closes the app while an image is loading. Using `lifecycleScope` with a try-catch-finally block solved most of these issues, ensuring the UI always returns to a usable state and users can retry if something goes wrong.

### How would you improve your app's UI or performance in future versions?

For UI improvements, I'd add a favorites feature that actually saves images locally using Room database, implement swipe gestures to quickly browse through dogs, and add a gallery view to see all your saved favorites. I'd also love to extract and display the breed name from the image URL and maybe pull in some fun facts about each breed. On the performance side, I'd implement proper image caching to support offline viewing, add skeleton loaders for a smoother loading experience, and move to an MVVM architecture with a ViewModel and Repository pattern for better code organization and testing. Pre-fetching the next image in the background while the user views the current one would make the experience feel even faster.

---

## 🛠️ Technologies Used

- Kotlin
- Retrofit & Gson
- Kotlin Coroutines
- Coil for image loading
- Material Design 3

---

## 🚀 Getting Started

1. Clone the repository
2. Open in Android Studio
3. Run on emulator or device (API 25+)

---

**Created as a junior developer assessment project.**
