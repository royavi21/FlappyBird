# 🐦 Flappy Bird in Java

<p align="center">
  A 2D Flappy Bird clone built using **Java Swing and AWT**.
  <br>
  Developed as my **4th Semester Software Development Course Project**.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=openjdk" alt="Java">
  <img src="https://img.shields.io/badge/GUI-Swing%20%26%20AWT-blue?style=for-the-badge" alt="Swing and AWT">
  <img src="https://img.shields.io/badge/JDK-8%2B-red?style=for-the-badge&logo=openjdk" alt="JDK 8+">
  <img src="https://img.shields.io/badge/Project-4th%20Semester-success?style=for-the-badge" alt="4th Semester">
</p>

---

## 🎮 About the Game

**Flappy Bird** is a simple yet challenging 2D arcade game where you control a bird trying to fly through gaps between pipes.

Press `SPACE` to flap your wings, avoid obstacles, and try to achieve the highest possible score!

---

## ✨ Features

- 🐦 **Bird Physics:** Gravity and flap mechanics for realistic movement.
- 🟢 **Random Pipes:** Pipes with randomly generated gap positions.
- ⏱️ **Pipe Generation:** New pipe pairs appear every 1.5 seconds.
- 🏆 **Scoring System:** Earn points every time you successfully pass a pair of pipes.
- 💀 **Game Over:** Displays a Game Over screen when you hit an obstacle.
- 🔄 **Instant Restart:** Press `SPACE` to restart after Game Over.
- 🚀 **Continuous Flapping:** Hold `SPACE` to flap repeatedly at 200 ms intervals.
- 🖥️ **Fixed Resolution:** Runs in a 360 × 640 pixel window.
- ☕ **Cross-Platform:** Works on any system with Java installed.

---

## 🎯 Controls

| Key | Action |
|:---:|---|
| `SPACE` | Flap / Restart the game |
| `HOLD SPACE` | Flap repeatedly every 200 ms |

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core programming language |
| Java Swing | Game window and GUI |
| Java AWT | Graphics, rendering and keyboard input |
| Swing Timer | Game loop and pipe generation |

---

## 📋 Requirements

Before running the project, make sure you have:

- **Java Development Kit (JDK) 8 or newer**
- A Java-compatible operating system (Windows, Linux or macOS).
- Any Java IDE or a terminal/command prompt.

Check your Java installation:

```bash
java -version
javac -version
```

---

## 📁 Project Structure

```text
FlappyBird/
│
├── src/
│   ├── App.java
│   ├── FlappyBird.java
│   ├── flappybirdbg.png
│   ├── flappybird.png
│   ├── toppipe.png
│   └── bottompipe.png
│
└── README.md
```

> **Note:** All four PNG image files must be available in the same folder as the compiled `.class` files because the game loads them from the classpath.

---

## 🚀 How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/royavi21/FlappyBird
```

### 2. Navigate to the Source Directory

```bash
cd FlappyBird/src
```

### 3. Compile the Java Files

```bash
javac App.java FlappyBird.java
```

### 4. Run the Game

```bash
java App
```

That's it! Enjoy playing Flappy Bird! 🎉

---

## 📦 Optional: Build an Executable JAR

You can package the game into a single executable JAR file.

Run these commands from the `src` directory:

**Compile the project:**

```bash
javac App.java FlappyBird.java
```

**Create the JAR file:**

```bash
jar cfe FlappyBird.jar App *.class *.png
```

**Run the JAR:**

```bash
java -jar FlappyBird.jar
```

---

## ⚙️ How It Works

The game is built using Java Swing and AWT.

- **App.java:** Creates the main `JFrame` window and adds the game panel.
- **FlappyBird.java:** Extends `JPanel` and implements `ActionListener` and `KeyListener` to handle game logic, keyboard input, and rendering.
- **Game Loop:** A Swing Timer runs approximately every 16 milliseconds (around 60 FPS).
- **Bird Movement:** Gravity increases the bird's downward velocity, while pressing `SPACE` applies upward force.
- **Pipe Generation:** A separate Swing Timer generates new pairs of pipes every 1.5 seconds.
- **Collision Detection:** Rectangle overlap checks determine whether the bird hits a pipe.
- **Scoring:** The score increases whenever the bird successfully passes a pair of pipes.

### 📊 Game Configuration

| Setting | Value |
|---|---:|
| Board Size | 360 × 640 px |
| Gravity | 1 px per frame |
| Flap Speed | -9 |
| Pipe Speed | 4 px per frame |
| Pipe Gap | 160 px |
| Pipe Generation Interval | 1.5 seconds |
| Game Loop Interval | ~16 ms |

---

## 🔮 Future Improvements

Some ideas to make the game even better:

- [ ] Remove pipes automatically after they leave the screen.
- [ ] Initialize the game window using `SwingUtilities.invokeLater()`.
- [ ] Display a clear error message when an image file is missing.
- [ ] Save the highest score between game sessions.
- [ ] Add pause and resume functionality.
- [ ] Introduce sound effects and background music.
- [ ] Add multiple difficulty levels.
- [ ] Separate game logic from rendering for easier unit testing.

---

## 📚 Documentation & Project Report

A complete project report covering all phases of the **Software Development Life Cycle (SDLC)** is included in the `docs` directory.

The report covers:

1. Planning
2. Requirements Analysis
3. System Design
4. Implementation
5. Testing
6. Deployment
7. Maintenance


---

## 👨‍💻 Author

**Avijit Roy**

🎓 4th Semester – Software Development Course Project

💻 Developed with Java Swing and AWT.

---

<p align="center">
  <b>⭐ If you like this project, consider giving it a star!</b>
  <br><br>
  Made with ❤️ and Java ☕
</p>