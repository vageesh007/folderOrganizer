# 📂 Folder Organizer

> A sleek, modern desktop app built using JavaFX that organizes your files with one click. Built by [Vageesh Singh](https://github.com/vageesh-singh).

![Java Version](https://img.shields.io/badge/Java-17-orange?logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-17-blue?logo=openjfx)
![License](https://img.shields.io/badge/License-MIT-green)
![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20Linux%20%7C%20macOS-lightgrey)

# 🖼️ Preview

Coming soon: Screenshots or a demo gif here.

# 🚀 Features

## 📁 Automatically organizes files by extension

## 🧠 Smart categorization: documents, media, code, etc.

## 🧰 Simple & beautiful JavaFX GUI

## 🔐 Safe to use – no file deletion without confirmation

## ⚡ Lightweight and fast

## 🔧 Cross-platform compatible (JAR)

## 💻 Technologies Used

# Java 17

JavaFX 17.0.2

Maven

FXML & CSS (for UI design)

Launch4j (for Windows .exe wrapping)

# 🛠️ Setup & Run

## 🧱 Requirements

JDK 17+

Maven 3.8+

JavaFX SDK 17 (for manual runs)

## ▶️ Run using Maven

git clone https://github.com/your-username/folder-organizer.git
cd folder-organizer
mvn clean javafx:run

## 🧪 Build Jar

mvn clean package

Jar output:target/folderOrganizer-1.0-SNAPSHOT-shaded.jar

## 💡 Run Fat JAR with JavaFX

java --module-path /path/to/javafx-sdk/lib \
     --add-modules javafx.controls,javafx.fxml \
     -jar target/folderOrganizer-1.0-SNAPSHOT-shaded.jar

## 📆 Build .exe (Optional)

Use Launch4j to convert the JAR to an EXE:

Wrap the shaded JAR

JVM Args:

--module-path lib/javafx-sdk/lib
--add-modules javafx.controls,javafx.fxml
-Xms128m
-Xmx512m

Tip: Ensure javaw.exe is found and paths are correct.

# 📁 Project Structure

📆 folder-organizer/
🔝 src/
   👉 main/
       👉 java/com/vageesh/OrganizerApp.java
       👉 java/com/vageesh/OrganizerController.java
       👉 resources/
           👉 organizer.fxml
           👉 style.css
           👉 icon.png
👉 pom.xml
👉 README.md

# 📜 License

This project is licensed under the MIT License.

# 🤝 Contribute

Pull requests are welcome! Feel free to fork and suggest improvements, fix bugs, or enhance UI features.

# ✨ Author

# Made with ❤️ by Vageesh Singh


