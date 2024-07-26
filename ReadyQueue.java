/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */

import java.util.PriorityQueue;

public class ReadyQueue {
    private PriorityQueue<Process> queue;

    public ReadyQueue() {
        queue = new PriorityQueue<>((a, b) -> {
                return a.getArrivalTime() - b.getArrivalTime();
        });
    }

    public void enqueue(Process process) {
        queue.add(process);
    }

    public Process dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    public Process peek() {
        return queue.peek();
    }
    
    public int size() {
        return queue.size();
    }
}


