# 📝 ToDo List App

A modern **To-Do List Android app** built with **Jetpack Compose**, **MVVM architecture**, and **Room Database**.  
It lets you add, edit, and manage tasks efficiently — with smooth navigation and a clean UI.

---

## 🚀 Features

- ✅ Add new tasks  
- ✏️ Edit existing tasks  
- 🗑️ Delete tasks  
- 📱 Built with **Jetpack Compose UI**  
- 💾 Offline storage using **Room Database**  
- ⚙️ **MVVM architecture** with **Repository pattern**  
- 🔄 State restoration using **SavedStateHandle**  
- 🧭 Navigation handled via **Compose Navigation**  
- 💡 Clean, scalable codebase for learning or extension  

---

## 🧰 Tech Stack

| Layer | Technology |
|-------|-------------|
| **UI** | Jetpack Compose |
| **Architecture** | MVVM |
| **Navigation** | androidx.navigation.compose |
| **Database** | Room (SQLite) |
| **Dependency Injection (optional)** | Manual factories |
| **Async Operations** | Kotlin Coroutines |
| **State Management** | ViewModel + SavedStateHandle |

---

## 📂 Project Structure

```
com.mwema.todolist
│
├── data/
│   ├── TODODatabase.kt
│   ├── TODODao.kt
│   ├── TODORepository.kt
│   └── TODORepositoryImplementation.kt
│
├── ui/
│   ├── addtodo/
│   │   ├── AddEditTODOScreen.kt
│   │   ├── AddEditTODOViewModel.kt
│   │   └── AddEditTodoViewModelFactory.kt
│   │
│   └── todo_list/
│       ├── TODOScreen.kt
│       ├── TODOViewModel.kt
│       └── TODOViewModelFactory.kt
│
├── util/
│   ├── Routes.kt
│   └── UiEvent.kt
│
└── MainActivity.kt
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repository
```bash
git clone https://github.com/MarkpaulNduthu/TODO_LIST_APP.git
cd todolist-compose
```

### 2️⃣ Open in Android Studio
- Use **Android Studio Flamingo** or newer.  
- Let Gradle sync and download dependencies automatically.

### 3️⃣ Build and Run
- Select a device or emulator.  
- Click **▶️ Run** in Android Studio.

---

## 💡 How It Works

- `MainActivity` hosts the **Navigation Graph** using `NavHost` and `NavController`.
- `TODOScreen` displays the full list of tasks.
- `AddEditTODOScreen` lets users create or modify a task.
- Both screens have their own `ViewModel`s.
- `TODORepositoryImplementation` provides an abstraction over the Room DAO.
- The `SavedStateHandle` automatically retrieves the `todoId` argument for editing.

---

## 🧠 Key Concepts

- **MVVM Pattern** → separates UI and data logic.  
- **Compose Navigation** → declarative and argument-safe routing.  
- **Compose UI** → Developed a reusable UI
- **Room Database** → local offline data persistence.  
- **Coroutines** → non-blocking background work.  
- **SavedStateHandle** → survives process death and provides navigation arguments.

---

## 🛠️ Future Improvements

- [ ] Add task categories and due dates  
- [ ] Implement search and filter  
- [ ] Integrate Hilt for dependency injection - **I'll think about this, for now I need to master how everything piece together first**
- [ ] Add Room migrations for data backup  
- [ ] Support light/dark themes  

---

## 📸 Screenshots 
**TO BE ADDED**

---

## 🧑‍💻 Author

#### **_Markpaul Nduthu Mwema_**  

📚 Computer Science Student | Full-Stack Web & Mobile Developer (Java, Kotlin, React, Spring Boot) | AI Explorer (Python, TensorFlow, NumPy, Pandas, Matplotlib,Scikit-learn)  
💼 [LinkedIn](https://www.linkedin.com/in/markpaul-mwema-1b35921b6/) · [GitHub](https://github.com/MarkpaulNduthu/)

---

## 🪪 License

This project is licensed under the [MIT License](LICENSE).
