# CPU Scheduler Simulator (Java Swing)

A desktop-based **CPU Scheduling Simulator** built with **Java** and **Java Swing** to visualize and compare classic operating system scheduling algorithms.  
The project focuses on algorithm behavior, process-level metrics, and scheduling performance analysis through an interactive GUI.

---

## 🚀 Project Overview

This simulator allows users to:

- Load process data from an input file
- Run multiple CPU scheduling algorithms
- Visualize execution using a **Gantt Chart**
- Inspect per-process metrics (waiting time, turnaround time, etc.)
- Compare algorithm-level average results

It is designed as an educational and practical implementation of core Operating Systems scheduling concepts.

---

## ✨ Key Features

- **Interactive Java Swing GUI** for simulation control and output display
- **Input file parser** for process definitions
- **Gantt chart timeline visualization** of execution order
- **Per-process metrics display**
- **Average waiting time / turnaround time** calculation
- **Cross-algorithm comparison** using aggregated result logs

---

## 🧠 Implemented Scheduling Algorithms

The simulator currently includes:

1. **First Come First Serve (FCFS)**
2. **Shortest Remaining Time First (SRTF)** *(preemptive)*
3. **Round Robin (RR)** *(user-defined quantum)*
4. **Multilevel Feedback Queue (MLFQ)** *(user-defined Queue 1 and Queue 2 quantums, Queue 3 FCFS)*
5. **Priority Scheduling** *(preemptive, runtime priority input)*

---

## 🏗️ Architecture & Core Components

- `Main.java`  
  Entry point and primary GUI window. Handles input loading, algorithm selection, and navigation.

- `Process.java`  
  Process model with scheduling attributes:
  PID, arrival time, priority, burst, remaining burst, completion, waiting, turnaround, etc.

- `ProcessScheduler.java`  
  Parses process input files and initializes process objects.

- Algorithm Screens / Logic:
  - `FCFS.java`
  - `SRTF.java`
  - `RoundRobin.java`
  - `MLFQ.java`
  - `PreemptivePriorityScheduling.java`

- Visualization:
  - `Gantt.java`, `GanttRecord.java`
  - `Display_Proccess.java`, `Display_ProccessP.java`

- Comparison:
  - `Comparison.java`, `ComparisonChart.java`
  - `algorithm_results.txt` (stores aggregate algorithm metrics for comparison)

---

## 📄 Input Format

The simulator expects each process in CSV format:

`PID,ArrivalTime,Priority,CPUBurst`

Example:
```txt
P1,0,2,5
P2,1,1,3
P3,2,3,8
```

A sample file is included as `processes.txt`.

---

## ▶️ How to Run

### Prerequisites
- Java JDK (8+ recommended)
- NetBeans IDE (recommended due to `.form` Swing designer files)

### Steps
1. Clone/download the repository.
2. Open the project in NetBeans.
3. Build and run `Main.java`.
4. Click **Browse** to load a process file.
5. Select an algorithm from the dropdown.
6. Click **Proceed** to run and view:
   - Process table
   - Gantt chart
   - Average waiting / turnaround times
7. Use **Compare** to view cross-algorithm performance from `algorithm_results.txt`.

---

## 📊 Metrics Produced

For each run, the simulator computes:

- **Waiting Time** (per process and average)
- **Turnaround Time** (per process and average)
- **Execution order and intervals** via Gantt records

Results are appended to `algorithm_results.txt` for later comparison.

---


## 📚 UI Screens
Main Menu:

<img width="940" height="623" alt="image" src="https://github.com/user-attachments/assets/8eaa1b72-bd64-47e5-8d55-fcd4e9e8b7c6" />

<img width="940" height="622" alt="image" src="https://github.com/user-attachments/assets/d1f915d3-96a2-45e3-9d12-b08e127b2eea" />

<img width="940" height="621" alt="image" src="https://github.com/user-attachments/assets/e7fdfeae-0a2d-40e8-883b-a7dfdcbc3ce5" />


**First Come First Serve (FCFS)**
<img width="940" height="625" alt="image" src="https://github.com/user-attachments/assets/9099af79-d7a6-4f09-bb7f-844d432452c1" />

**Shortest Remaining Time First (SRTF)**
<img width="940" height="627" alt="image" src="https://github.com/user-attachments/assets/0cbfc46c-f1dc-492d-98fa-37847ee4df66" />

**Round Robin (RR)**
<img width="940" height="624" alt="image" src="https://github.com/user-attachments/assets/5f7c9828-d40f-477f-b4dc-083a6218ae6b" />

**Priority Scheduling**
<img width="940" height="626" alt="image" src="https://github.com/user-attachments/assets/d9674984-bd0a-4844-8f61-8189a2d8532c" />

<img width="940" height="624" alt="image" src="https://github.com/user-attachments/assets/2995b667-c9f6-4fe3-8e3e-91884af0ad93" />

**Multilevel Feedback Queue (MLFQ)**
<img width="940" height="626" alt="image" src="https://github.com/user-attachments/assets/3867bbb0-86b1-4a29-b725-366c47a2fad2" />


<img width="940" height="624" alt="image" src="https://github.com/user-attachments/assets/3a2a0a9e-4331-48ec-b3a1-28fdcdd4396b" />

---

## 👩‍💻 Author

**Maryam Inam**  
If you’re a recruiter or collaborator, feel free to explore the code and reach out for discussion.
