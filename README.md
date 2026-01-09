# 🧪 J-Pairwise
> **Smart Test Case Generator for Java** | *Combinatorial Testing made easy.*

![Java](https://img.shields.io/badge/Java-21-orange?logo=java)
![Build](https://img.shields.io/badge/Build-Gradle-green?logo=gradle)
![Status](https://img.shields.io/badge/Status-Active_Development-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

## 🧐 The Problem: Combinatorial Explosion
In software testing, checking every possible configuration is often impossible.
Imagine testing a web app with these variables:
* **OS:** Windows, Linux, macOS, Android, iOS (5)
* **Browser:** Chrome, Firefox, Safari, Edge (4)
* **Database:** PostgreSQL, MySQL, Oracle (3)
* **User Role:** Admin, Editor, Viewer (3)

**Total combinations:** `5 * 4 * 3 * 3` = **180 tests**.
Writing and maintaining 180 tests for a single feature is inefficient.

## 💡 The Solution: J-Pairwise
**J-Pairwise** is a CLI tool (currently in development) that implements the **IPOG (In-Parameter-Order-General)** algorithm.

Instead of testing *all* combinations, it generates a minimized set of test cases that covers **every possible pair** of parameters.
* **Result:** Reduces ~180 tests to **~15-20 highly effective tests**.
* **The Killer Feature:** It doesn't just give you a CSV. It automatically generates a ready-to-run **JUnit 5 `.java` file** populated with `@ParameterizedTest`.

## 🚧 Project Status & Roadmap
I am currently an Engineering student building this tool to bridge the gap between algorithmic theory and real-world software engineering.

**Current Stage:** 🏗️ *Building Core Data Models & Algorithm Logic*

### 1. Foundation (✅ Done)
- [x] Project Setup (Java 21, Gradle Kotlin DSL).
- [x] Repository Architecture (Engine, IO, Model separation).
- [x] Dependency Management (Jackson, JUnit 5).

### 2. Core Logic (🔄 In Progress)
- [ ] Implement `Parameter` and `TestCase` records.
- [ ] Implement `Pair` logic (Interaction tracking).
- [ ] **Challenge:** Implement the **IPOG Strategy** (Horizontal & Vertical growth).
- [ ] Unit Testing the algorithm with mock data.

### 3. I/O & Application (🔜 Upcoming)
- [ ] JSON Parser (Read `input.json`).
- [ ] **Java Code Generator:** Use Java 21 Text Blocks to write `.java` files dynamically.
- [ ] CLI Entry Point (`main` args processing).

## 🛠️ Tech Stack
This project uses modern Java features to keep the code clean and efficient:
* **Language:** Java 21 (Records, Pattern Matching, Text Blocks).
* **Build System:** Gradle (Kotlin DSL).
* **Testing:** JUnit 5.
* **Data:** Jackson (JSON processing).

## 🤝 Contributing & Testing
**Why get involved?**
This project is an open implementation of a complex algorithm. If you are interested in **Combinatorial Optimization**, **Compiler/Generator logic**, or just want to see how **Java 21** looks in practice, you are welcome here!

### How to run (For Developers)
Since the CLI is not ready yet, you can test the project by running the unit tests:

1.  Clone the repo:
    ```bash
    git clone https://github.com/mattiabandini1/J-Pairwise.git
    ```
2.  Open in **IntelliJ IDEA**.
3.  Run the tests via Gradle:
    ```bash
    ./gradlew test
    ```

*Feedback, Issue reports, and Pull Requests are highly appreciated!*

## 📄 License
Distributed under the **MIT License**. See `LICENSE` for more information.

---
*Created with ❤️ by [Mattia Bandini](https://github.com/mattiabandini1)*
