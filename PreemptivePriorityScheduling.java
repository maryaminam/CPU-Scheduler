
import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Zainab
 */
public class PreemptivePriorityScheduling extends javax.swing.JFrame {

    /**
     * Creates new form PreemptivePriorityScheduling
     */
    private List<Process> processes;
    private ArrayList<GanttRecord> gantt;
    private int currentTime;
    private ReadyQueue readyQueue;
    private double avgWaitingTime;
    private double avgTurnaroundTime;
    private Process currentProcess; // Track the currently executing process
    private int remainingBurstTime; // Track remaining burst time of the current process

    public PreemptivePriorityScheduling(List<Process> processes) {
        initComponents();
        setVisible(true);
        this.processes = processes;
        gantt = new ArrayList<>();
        currentTime = 0;
        readyQueue = new ReadyQueue();
        
        promptForPriorities(); // Prompt user for priorities
        
        for (Process process : processes) {
            readyQueue.enqueue(process);
        }
        avgWaitingTime = 0;
        avgTurnaroundTime = 0;
        currentProcess = null;
        remainingBurstTime = 0;
    }
    
    private void promptForPriorities() {
        for (Process process : processes) {
            String priorityStr = JOptionPane.showInputDialog(this, "Enter priority for Process " + process.getPid() + ":");
            int priority = Integer.parseInt(priorityStr);
            process.setPriority(priority);
        }
    }

    public void schedule() {
        int totalProcesses = readyQueue.size();
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        while (!readyQueue.isEmpty() || currentProcess != null) {

            if (currentProcess == null && !readyQueue.isEmpty()) {
                currentProcess = readyQueue.dequeue();
                remainingBurstTime = currentProcess.getRemainingBurstTime();
            }

            // Update Gantt chart
            if (currentProcess != null) {
                int startTime = Math.max(currentTime, currentProcess.getArrivalTime());
                remainingBurstTime--;
                int in = startTime;
                currentTime = startTime + 1; // Time unit increments
                currentProcess.setRemainingBurstTime(remainingBurstTime);
                gantt.add(new GanttRecord(in, currentTime, currentProcess.getPid()));

                // If current process completes, calculate waiting and turnaround times
                if (remainingBurstTime == 0) {
                    int waitingTime = currentTime - currentProcess.getArrivalTime() - currentProcess.getCpuBurst();
                    int turnaroundTime = currentTime - currentProcess.getArrivalTime();
                    currentProcess.setWaitingTime(waitingTime);
                    currentProcess.setTurnaroundTime(turnaroundTime);

                    totalWaitingTime += waitingTime;
                    totalTurnaroundTime += turnaroundTime;
                    currentProcess = null;
                }
            }

            // Check for arrival of new processes during execution
            List<Process> arrivedProcesses = getArrivedProcesses(readyQueue, currentTime);
            for (Process p : arrivedProcesses) {
                if (currentProcess == null) {
                    currentProcess = p;
                    remainingBurstTime = currentProcess.getRemainingBurstTime();
                } else {
                    // If a new process has higher priority than the current one, preempt the current process
                    if (p.getPriority() < currentProcess.getPriority() ||
                        (p.getPriority() == currentProcess.getPriority() && p.getArrivalTime() < currentProcess.getArrivalTime())) {
                        readyQueue.enqueue(currentProcess); // Enqueue current process with remaining burst time
                        currentProcess = p; // Make the arriving process the current one
                        remainingBurstTime = currentProcess.getRemainingBurstTime();
                    } else {
                        readyQueue.enqueue(p); // Enqueue the arriving process
                    }
                }
            }
        }

        // Calculate average waiting time and turnaround time
        avgWaitingTime = (double) totalWaitingTime / totalProcesses;
        avgTurnaroundTime = (double) totalTurnaroundTime / totalProcesses;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("algorithm_results.txt", true))) {
            writer.write("Priority Scheduling" +"," + avgTurnaroundTime + "," + avgWaitingTime);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        waittime.setText(Double.toString(avgWaitingTime));
        turnaroundtime.setText(Double.toString(avgTurnaroundTime));

        // Set Processes 
        Display_ProccessP process_panel = new Display_ProccessP(processes);
        processpanel.removeAll();
        processpanel.setLayout(new BorderLayout());
        processpanel.add(process_panel, BorderLayout.CENTER);
        processpanel.revalidate();
        processpanel.repaint();

        // Set Gantt chart
        Gantt ganttChart = new Gantt(gantt);
        ganttchartpanel.removeAll();
        ganttchartpanel.setLayout(new BorderLayout());
        ganttchartpanel.add(ganttChart, BorderLayout.CENTER);
        ganttchartpanel.revalidate();
        ganttchartpanel.repaint();

        ganttchartpanel.add(ganttChart);

    }

    private List<Process> getArrivedProcesses(ReadyQueue readyQueue, int currentTime) {
        List<Process> arrivedProcesses = new ArrayList<>();
        while (!readyQueue.isEmpty() && readyQueue.peek().getArrivalTime() <= currentTime) {
            arrivedProcesses.add(readyQueue.dequeue());
        }
        return arrivedProcesses;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        processpanel = new java.awt.Panel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        turnaroundtime = new javax.swing.JTextField();
        waittime = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ganttchartpanel = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(182, 198, 190));
        jLabel7.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(88, 138, 151));
        jLabel7.setText("Processes:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        processpanel.setBackground(new java.awt.Color(182, 198, 190));

        javax.swing.GroupLayout processpanelLayout = new javax.swing.GroupLayout(processpanel);
        processpanel.setLayout(processpanelLayout);
        processpanelLayout.setHorizontalGroup(
            processpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1120, Short.MAX_VALUE)
        );
        processpanelLayout.setVerticalGroup(
            processpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        getContentPane().add(processpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 1120, 260));

        jLabel2.setBackground(new java.awt.Color(182, 198, 190));
        jLabel2.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 60)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(209, 142, 123));
        jLabel2.setText("PRIORITY SCHEDULING");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, -1));

        jLabel5.setBackground(new java.awt.Color(182, 198, 190));
        jLabel5.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(88, 138, 151));
        jLabel5.setText("Average Turnaround Time:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 550, -1, -1));

        turnaroundtime.setEditable(false);
        turnaroundtime.setBackground(new java.awt.Color(182, 198, 190));
        turnaroundtime.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 0, 20)); // NOI18N
        turnaroundtime.setForeground(new java.awt.Color(209, 142, 123));
        turnaroundtime.setBorder(null);
        turnaroundtime.setRequestFocusEnabled(false);
        turnaroundtime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turnaroundtimeActionPerformed(evt);
            }
        });
        getContentPane().add(turnaroundtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 580, 370, -1));

        waittime.setEditable(false);
        waittime.setBackground(new java.awt.Color(182, 198, 190));
        waittime.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 0, 20)); // NOI18N
        waittime.setForeground(new java.awt.Color(209, 142, 123));
        waittime.setBorder(null);
        waittime.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        waittime.setRequestFocusEnabled(false);
        waittime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waittimeActionPerformed(evt);
            }
        });
        getContentPane().add(waittime, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, 370, -1));

        jLabel4.setBackground(new java.awt.Color(182, 198, 190));
        jLabel4.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(88, 138, 151));
        jLabel4.setText("Gantt Chart:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        jLabel3.setBackground(new java.awt.Color(182, 198, 190));
        jLabel3.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(88, 138, 151));
        jLabel3.setText("Average Wait Time:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, -1, -1));

        ganttchartpanel.setBackground(new java.awt.Color(182, 198, 190));

        javax.swing.GroupLayout ganttchartpanelLayout = new javax.swing.GroupLayout(ganttchartpanel);
        ganttchartpanel.setLayout(ganttchartpanelLayout);
        ganttchartpanelLayout.setHorizontalGroup(
            ganttchartpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ganttchartpanelLayout.setVerticalGroup(
            ganttchartpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(ganttchartpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 1110, 140));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(1200, 840));
        jLabel1.setMinimumSize(new java.awt.Dimension(1200, 840));
        jLabel1.setPreferredSize(new java.awt.Dimension(1200, 840));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 840));

        jMenuBar5.setBackground(new java.awt.Color(182, 198, 190));
        jMenuBar5.setForeground(new java.awt.Color(209, 142, 123));

        jMenu5.setBackground(new java.awt.Color(182, 198, 190));
        jMenu5.setForeground(new java.awt.Color(209, 142, 123));
        jMenu5.setText("Back");

        jMenuItem5.setBackground(new java.awt.Color(182, 198, 190));
        jMenuItem5.setForeground(new java.awt.Color(209, 142, 123));
        jMenuItem5.setText("Back");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuBar5.add(jMenu5);

        setJMenuBar(jMenuBar5);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void turnaroundtimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turnaroundtimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_turnaroundtimeActionPerformed

    private void waittimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waittimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_waittimeActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        //TODO add your handling code here:
        Main main = new Main();
        setVisible(false);
        main.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Panel ganttchartpanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuItem jMenuItem5;
    private java.awt.Panel processpanel;
    private javax.swing.JTextField turnaroundtime;
    private javax.swing.JTextField waittime;
    // End of variables declaration//GEN-END:variables
}
