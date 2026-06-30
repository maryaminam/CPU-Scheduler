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


## 📚 Additional Documentation

For GUI snapshots and extended explanation, see:

- `Report.docx`

---

## 👩‍💻 Author

**Maryam Inam**  
If you’re a recruiter or collaborator, feel free to explore the code and reach out for discussion.
