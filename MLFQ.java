
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
public class MLFQ extends javax.swing.JFrame {

    /**
     * Creates new form MLFQ
     */
    
    private List<Process> processes;
    private double avgWaitingTime;
    private double avgTurnaroundTime;
    private int timeQuantum1;
    private int timeQuantum2;
    private ArrayList<GanttRecord> gantt;
    private int startTime;
    private ReadyQueue q1Queue;
    private ReadyQueue q2Queue;
    private ReadyQueue q3Queue;
    
    public MLFQ(List<Process> processes) {
        initComponents();
        setVisible(true);
        this.processes = processes;
        this.gantt = new ArrayList<>();
        this.q1Queue = new ReadyQueue();
        this.q2Queue = new ReadyQueue();
        this.q3Queue = new ReadyQueue();
    }

    public void schedule() {
        // Initialize queues with processes based on their arrival times
        for (Process process : processes) {
            q1Queue.enqueue(process);
        }

        int currentTime = calculateStartTime();
        
        while (!q1Queue.isEmpty() || !q2Queue.isEmpty() || !q3Queue.isEmpty()) {
            // Process Queue 1
            while (!q1Queue.isEmpty()) {
                Process currentProcess = q1Queue.peek(); // Check the front of the queue
                if (currentProcess.getArrivalTime() <= currentTime) {
                    q1Queue.dequeue(); // Remove from Queue 1
                    int remainingBurst = Math.min(timeQuantum1, currentProcess.getRemainingBurstTime());
                    int startTime = Math.max(currentTime, currentProcess.getArrivalTime());
                    currentTime += remainingBurst;
                    currentProcess.setRemainingBurstTime(currentProcess.getRemainingBurstTime() - remainingBurst);

                    if (currentProcess.getRemainingBurstTime() > 0) {
                        q2Queue.enqueue(currentProcess);
                    } else {
                        currentProcess.setTurnaroundTime(currentTime - currentProcess.getArrivalTime());
                        currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getCpuBurst());
                    }
                    gantt.add(new GanttRecord(startTime, currentTime, currentProcess.getPid()));
                } else {
                    break; // No more processes to execute at current time
                }
            }

            // Process Queue 2
            while (!q2Queue.isEmpty()) {
                Process currentProcess = q2Queue.peek(); // Check the front of the queue
                int remainingBurst = Math.min(timeQuantum2, currentProcess.getRemainingBurstTime());
                int startTime = currentTime;
                currentTime += remainingBurst;
                currentProcess.setRemainingBurstTime(currentProcess.getRemainingBurstTime() - remainingBurst);

                if (currentProcess.getRemainingBurstTime() > 0) {
                    q3Queue.enqueue(currentProcess);
                } else {
                    currentProcess.setTurnaroundTime(currentTime - currentProcess.getArrivalTime());
                    currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getCpuBurst());
                }
                gantt.add(new GanttRecord(startTime, currentTime, currentProcess.getPid()));
                q2Queue.dequeue(); // Remove from Queue 2 after processing
            }

            // Process Queue 3 (FCFS)
            while (!q3Queue.isEmpty()) {
                Process currentProcess = q3Queue.peek(); // Check the front of the queue
                int startTime = currentTime;
                currentTime += currentProcess.getRemainingBurstTime();
                currentProcess.setRemainingBurstTime(0);
                currentProcess.setTurnaroundTime(currentTime - currentProcess.getArrivalTime());
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getCpuBurst());
                gantt.add(new GanttRecord(startTime, currentTime, currentProcess.getPid()));
                q3Queue.dequeue(); // Remove from Queue 3 after processing
            }
        }

        // Calculate average turnaround and waiting times
        int n = processes.size();
        avgTurnaroundTime = 0;
        avgWaitingTime = 0;
        for (Process process : processes) {
            avgTurnaroundTime += process.getTurnaroundTime();
            avgWaitingTime += process.getWaitingTime();
        }
        avgTurnaroundTime /= n;
        avgWaitingTime /= n;
        
        // Write results to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("algorithm_results.txt", true))) {
            writer.write("MultiLevelFeedbackQueue," + avgTurnaroundTime + "," + avgWaitingTime);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Update GUI components with results
        waittime.setText(Double.toString(avgWaitingTime));
        turnaroundtime.setText(Double.toString(avgTurnaroundTime));
        
        // Display processes
        Display_Proccess process_panel = new Display_Proccess(processes);
        processpanel.removeAll();
        processpanel.setLayout(new BorderLayout());
        processpanel.add(process_panel, BorderLayout.CENTER);
        processpanel.revalidate();
        processpanel.repaint();
        
        // Display Gantt chart
        Gantt ganttChart = new Gantt(gantt);
        ganttchartpanel.removeAll();
        ganttchartpanel.setLayout(new BorderLayout());
        ganttchartpanel.add(ganttChart, BorderLayout.CENTER);
        ganttchartpanel.revalidate();
        ganttchartpanel.repaint();
    }

    private int calculateStartTime() {
        int minArrivalTime = Integer.MAX_VALUE;
        for (Process process : processes) {
            minArrivalTime = Math.min(minArrivalTime, process.getArrivalTime());
        }
        return Math.max(0, minArrivalTime);
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        processpanel = new java.awt.Panel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        turnaroundtime = new javax.swing.JTextField();
        waittime = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ganttchartpanel = new java.awt.Panel();
        quantumtime = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        quantumtime1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(182, 198, 190));
        jLabel8.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(88, 138, 151));
        jLabel8.setText("Processes:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

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

        getContentPane().add(processpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 1120, 260));

        jLabel2.setBackground(new java.awt.Color(182, 198, 190));
        jLabel2.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 60)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(209, 142, 123));
        jLabel2.setText("MULTILEVEL FEEDBACK QUEUE");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 8, -1, 70));

        jLabel5.setBackground(new java.awt.Color(182, 198, 190));
        jLabel5.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(88, 138, 151));
        jLabel5.setText("Average Turnaround Time:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 680, -1, 30));

        jLabel3.setBackground(new java.awt.Color(182, 198, 190));
        jLabel3.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(88, 138, 151));
        jLabel3.setText("Average Wait Time:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 680, -1, 30));

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
        getContentPane().add(turnaroundtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 710, 370, 30));

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
        getContentPane().add(waittime, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 710, 370, 30));

        jLabel4.setBackground(new java.awt.Color(182, 198, 190));
        jLabel4.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(88, 138, 151));
        jLabel4.setText("Gantt Chart:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, 30));

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

        getContentPane().add(ganttchartpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 1110, 200));

        quantumtime.setBackground(new java.awt.Color(182, 198, 190));
        quantumtime.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 0, 20)); // NOI18N
        quantumtime.setForeground(new java.awt.Color(209, 142, 123));
        quantumtime.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        quantumtime.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        quantumtime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantumtimeActionPerformed(evt);
            }
        });
        getContentPane().add(quantumtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 130, 30));

        jLabel6.setBackground(new java.awt.Color(182, 198, 190));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(88, 138, 151));
        jLabel6.setText("Queue 1 Quantum Time: ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 74, -1, 30));

        jLabel7.setBackground(new java.awt.Color(182, 198, 190));
        jLabel7.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(88, 138, 151));
        jLabel7.setText("Queue 2 Quantum Time: ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 114, -1, 30));

        quantumtime1.setBackground(new java.awt.Color(182, 198, 190));
        quantumtime1.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 0, 20)); // NOI18N
        quantumtime1.setForeground(new java.awt.Color(209, 142, 123));
        quantumtime1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        quantumtime1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        quantumtime1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantumtime1ActionPerformed(evt);
            }
        });
        getContentPane().add(quantumtime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 130, 30));

        jButton3.setBackground(new java.awt.Color(209, 142, 123));
        jButton3.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(210, 227, 234));
        jButton3.setText("Select");
        jButton3.setToolTipText("");
        jButton3.setBorder(null);
        jButton3.setOpaque(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 80, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(1200, 840));
        jLabel1.setMinimumSize(new java.awt.Dimension(1200, 840));
        jLabel1.setPreferredSize(new java.awt.Dimension(1200, 840));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1190, 860));

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

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        //TODO add your handling code here:
        Main main = new Main();
        setVisible(false);
        main.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void turnaroundtimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turnaroundtimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_turnaroundtimeActionPerformed

    private void waittimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waittimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_waittimeActionPerformed

    private void quantumtimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantumtimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantumtimeActionPerformed

    private void quantumtime1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantumtime1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantumtime1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String quantumTimeString = quantumtime.getText();
        String quantumTime1String = quantumtime1.getText();

        // Convert the string to an integer if needed
        try {
            timeQuantum1 = Integer.parseInt(quantumTimeString);
            timeQuantum2 = Integer.parseInt(quantumTime1String);
            // Now you have the timeQuantum value to use
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            JOptionPane.showMessageDialog(null, "Please enter a valid integer for the quantum time.");
        }
        schedule();
    }//GEN-LAST:event_jButton3ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Panel ganttchartpanel;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuItem jMenuItem5;
    private java.awt.Panel processpanel;
    private javax.swing.JTextField quantumtime;
    private javax.swing.JTextField quantumtime1;
    private javax.swing.JTextField turnaroundtime;
    private javax.swing.JTextField waittime;
    // End of variables declaration//GEN-END:variables
}
