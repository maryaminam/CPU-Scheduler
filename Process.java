/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class Process {

    private String pid;
    private int arrivalTime;
    private int priority;
    private int cpuBurst;
    private int remainingBurst;
    private int completionTime;
    private int turnaroundTime;
    private int waitingTime;
    private boolean completed;
    private int lastExecutedTime;

    public Process(String pid, int arrivalTime, int priority, int cpuBurst) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.cpuBurst = cpuBurst;
        this.remainingBurst = cpuBurst;
        this.completed = false;
        this.lastExecutedTime = arrivalTime;
    }

    // Getters and setters for all fields
    public String getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCpuBurst() {
        return cpuBurst;
    }

    public int getRemainingBurstTime() {
        return remainingBurst;
    }

    public void setRemainingBurstTime(int remainingBurstTime) {
        this.remainingBurst = remainingBurstTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void reduceBurstTime(int time) {
        remainingBurst -= time;
        if (remainingBurst < 0) {
            remainingBurst = 0;
        }
    }
     public int getLastExecutedTime() {
        return lastExecutedTime;
    }

    public void setLastExecutedTime(int lastExecutedTime) {
        this.lastExecutedTime = lastExecutedTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "pid='" + pid + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", priority=" + priority +
                ", cpuBurst=" + cpuBurst +
                ", remainingBurst=" + remainingBurst +
                ", completionTime=" + completionTime +
                ", turnaroundTime=" + turnaroundTime +
                ", waitingTime=" + waitingTime +
                ", completed=" + completed +
                '}';
    }
}



