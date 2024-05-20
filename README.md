# Kindle-Library
A Book Buying App 

### Project Overview:
I created an Android application called "kindleLibrary" that replicates a book buying site, similar to Amazon's book marketplace. This app allows users to explore a wide range of books, view detailed information, check prices, and verify availability. It provides a seamless and interactive shopping experience for book enthusiasts, incorporating functionalities designed to enhance user engagement and convenience.

### Features:
- Book Browsing: Users can browse a comprehensive collection of books. The interface is intuitive and showcases book covers, detailed descriptions, prices, and availability status.
- Filtering Options: A robust filtering feature allows users to refine their search based on book types, genres, authors, price ranges, or other relevant categories, providing a tailored browsing experience.
- User Authentication: The app integrates a secure login mechanism. Users enter their credentials, which are verified and stored securely in the FirebaseFirestore database.

### Adapters:
- HomeAdapter: Manages the dataset for the home screen, where books are showcased in a user-friendly layout, allowing easy navigation and interaction.
- MyCartAdapter: Handles items added to the user’s cart, enabling functionalities like viewing, modifying, or removing items within the cart.
- ViewAllAdapter: Provides a comprehensive view of all available books, supporting effortless browsing through the entire collection.
- UserModelAdapter: Facilitates the connection between user model data and the relevant UI components, ensuring accurate representation and management of user-related information.

### Models:
- HomeCategory: Represents the different categories or genres of books displayed on the home screen, including properties like category names, associated books, and relevant metadata.
- MyCartModel: Defines the structure for the shopping cart, including details such as book IDs, quantities, prices, and other relevant cart information.
- UserModel: Stores and manages user-specific information, such as login credentials, preferences, and historical data, enhancing the user experience and providing personalized content.

### UI Components:
- HomeUI: The main interface of the app, showcasing a diverse range of books in an organized and visually appealing manner. Users can explore new releases, bestsellers, and recommended reads.
- LoginUI: A secure and user-friendly login screen prompts users to enter their credentials, ensuring secure access to their profiles and personalized settings.
- MyCartFragment: A dedicated fragment displays the user's selected books, enabling them to review their choices, make adjustments, and proceed to checkout.

### Install and Run Instructions:
- Clone the repo
- Install and open project in Android Studio
- Connect an Android device via USB or set up an Android emulator (I used an emulator i.e Google Pixel 7 Pro)
- Run the code

### Interesting Parts during the Build Process:

- Designing the User Interface (UI)
Creating a user-friendly and visually appealing interface was one of the most exciting parts of building the app. I spent a lot of time designing the screens to make sure users can easily navigate through the app. For example, I used RecyclerView to display lists of books efficiently. Each book listing includes a cover image, title, author, price, and availability status, making it easy for users to find the information they need quickly.

- Implementing Filtering Options
Adding the filtering feature was particularly interesting. This allows users to search for books based on different criteria like genre, author, or price range. To make this work smoothly, I had to learn about different filtering techniques and how to apply them. The goal was to make the filtering fast and responsive, so users can see the results immediately as they apply different filters.

- Integrating Firebase
Using Firebase for user authentication and data storage was a key part of the project. Firebase simplifies a lot of backend processes, which made it easier to focus on the app’s features. Setting up Firebase Authentication allowed users to securely log in and store their data in Firestore. It was fascinating to see how quickly Firebase could handle tasks like user login and data synchronization.

### Difficulties Faced and Solutions:

- Managing User Input
Handling user inputs, especially for search and filtering, was a bit tricky. Users can type in search queries, select filters, and interact with the app in many ways. To ensure the app responded correctly, I had to test different input scenarios thoroughly. One solution was to use TextWatchers and ClickListeners to detect changes in real-time and update the UI accordingly. This made the app more interactive and responsive to user actions.

- Ensuring Data Security
Keeping user data secure was a major concern. With users logging in and storing personal information, it was important to protect their data. I used Firebase’s built-in security features, such as Authentication and Firestore security rules, to safeguard user data. Additionally, implementing validation checks on the client side helped prevent unauthorized access and ensured that data being entered was correct.

- Debugging UI Issues
Making sure the app looked good on all devices was challenging. Different screen sizes and resolutions can affect how the UI components are displayed. I used Android Studio's layout inspector and tested the app on multiple devices to identify and fix alignment issues. By using constraint layouts and responsive design techniques, I was able to ensure the app looked consistent and attractive across various devices.

## Screenshots:
### HomePage
<img src="https://github.com/shubhhh19/Kindle-Library/assets/126296317/379badf8-279d-4861-871f-7f1e5952bae2" width="200" height="400">

### Register
<img src="https://github.com/shubhhh19/Kindle-Library/assets/126296317/f7b7a051-6e4f-4c18-b5f1-cbcbd9cd870d" width="200" height="400">

### My Cart
<img src="https://github.com/shubhhh19/Kindle-Library/assets/126296317/657a2891-c377-4663-910e-baf1f8e4d3fb" width="200" height="400">
