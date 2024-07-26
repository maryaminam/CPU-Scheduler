/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessScheduler {
    private List<Process> processes;

    public ProcessScheduler() {
        this.processes = new ArrayList<>();
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void parseProcessFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.err.println("Invalid line format: " + line);
                    continue;
                }

                try {
                    String pid = parts[0];
                    int arrivalTime = Integer.parseInt(parts[1]);
                    int priority = Integer.parseInt(parts[2]);
                    int cpuBurst = Integer.parseInt(parts[3]);
                    processes.add(new Process(pid, arrivalTime, priority, cpuBurst));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
