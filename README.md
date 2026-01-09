# 🧪 J-Pairwise
> **Smart Test Case Generator for Java** | *Combinatorial Testing made easy.*

![Java](https://img.shields.io/badge/Java-21-orange?logo=java)
![Build](https://img.shields.io/badge/Build-Gradle-green?logo=gradle)
![Status](https://img.shields.io/badge/Status-v1.0_Stable-success)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

## 🧐 The Problem: Combinatorial Explosion
In software testing, checking every possible configuration is often impossible.
Imagine testing a web app with these variables:
* **OS:** Windows, Linux, macOS, Android, iOS (5)
* **Browser:** Chrome, Firefox, Safari, Edge (4)
* **Database:** PostgreSQL, MySQL, Oracle (3)
* **User Role:** Admin, Editor, Viewer (3)

**Total combinations:** `5 * 4 * 3 * 3` = **180 tests**.
Writing and maintaining 180 tests manually is inefficient and error-prone.

## 💡 The Solution: J-Pairwise
**J-Pairwise** is a Java tool that implements the **IPOG (In-Parameter-Order-General)** algorithm to solve this problem.

Instead of testing *all* combinations, it generates a minimized set of test cases that covers **every possible pair** of parameters.
* **Result:** Reduces ~180 tests to **~15-20 highly effective tests**.
* **The Killer Feature:** It acts as a **Metaprogramming Tool**. It doesn't just output data; it automatically generates a ready-to-compile **JUnit 5 class** implementing the **Smart Oracle Pattern**.

## 🚀 Key Features (v1.0)
This project bridges the gap between algorithmic theory and real-world software engineering.

* ✅ **IPOG Implementation:** Custom implementation of horizontal and vertical growth strategies for pairwise coverage.
* ✅ **Code Generation:** Uses Java Text Blocks to dynamically write `.java` source files.
* ✅ **Smart Oracle Pattern:** The generated tests separate **Data** (handled by the tool) from **Logic** (handled by the developer). You write the verification logic *once*, and it applies to all generated cases.
* ✅ **JSON Configuration:** Fully data-driven input via `demo_config.json`.

## 🛠️ Tech Stack
This project uses modern Java features to keep the code clean and efficient:
* **Language:** Java 21 (Records, Pattern Matching, Text Blocks).
* **Build System:** Gradle (Kotlin DSL).
* **Testing:** JUnit 5 (Jupiter & Parameterized Tests).
* **Data:** Jackson (JSON processing).

## ⚡ How to Run

### Option A: Quick Start (Recommended for Users)
No coding required. Just download and run.

1.  **Download:** Go to the **Releases** page (right sidebar) and download the latest assets:
    * `J-Pairwise-1.0-SNAPSHOT-all.jar`
    * `demo_config.json`
2.  **Run:** Open your terminal in the download folder and execute:
    ```bash
    java -jar J-Pairwise-1.0-SNAPSHOT-all.jar
    ```
    *(Note: You can also specify a custom config file: `java -jar J-Pairwise-1.0-SNAPSHOT-all.jar my_config.json`)*

### Option B: Build from Source (For Developers)
If you want to modify the code or build it yourself:

1.  **Clone the repo:**
    ```bash
    git clone [https://github.com/mattiabandini1/J-Pairwise.git](https://github.com/mattiabandini1/J-Pairwise.git)
    cd J-Pairwise
    ```
2.  **Build the JAR:**
    ```bash
    ./gradlew shadowJar
    ```
3.  **Run:**
    ```bash
    java -jar build/libs/J-Pairwise-1.0-SNAPSHOT-all.jar
    ```

## 📂 Project Structure

* `src/main/java/.../engine` -> **The Brain:** Implementation of the IPOG algorithm.
* `src/main/java/.../io` -> **The Builder:** JSON parsing and Java Source Code generation.
* `src/main/java/.../model` -> **The Data:** Records representing Parameters and Test Cases.
* `demo_config.json` -> Configuration file for the demo run.

## 🤝 Contributing
**Why get involved?**
This project is an open implementation of a complex algorithm. If you are interested in **Combinatorial Optimization**, **Compiler/Generator logic**, or just want to see how **Java 21** looks in practice, feel free to fork and contribute!

## 📄 License
Distributed under the **MIT License**. See `LICENSE` for more information.

---
*Created with ❤️ by [Mattia Bandini](https://github.com/mattiabandini1)*
