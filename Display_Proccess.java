import java.awt.*;
import java.util.List;

public class Display_Proccess extends Panel {
    private List<Process> processes;

    public Display_Proccess(List<Process> processes) {
        this.processes = processes;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int startX = 50; // Starting X position
        int startY = 50; // Starting Y position
        int rowHeight = 40; // Height of each row
        int colWidth = 150; // Width of each column

        // Draw table header
        g.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 20));
        g.drawString("PID", startX, startY);
        g.drawString("Arrival Time", startX + colWidth, startY);
        g.drawString("CPU Burst", startX + 2 * colWidth, startY);
        g.drawString("Waiting Time", startX + 3 * colWidth, startY);
        g.drawString("Turnaround Time", startX + 4 * colWidth, startY);

        g.drawLine(startX, startY + 10, startX + 5 * colWidth, startY + 10);

        // Draw process details
        g.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 18));
        int yPos = startY + rowHeight;
        for (Process process : processes) {
            g.setColor(getColor(process.getPid().hashCode()));
            g.fillRect(startX - 40, yPos - 20, 40, 20);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(process.getPid()), startX, yPos);
            g.drawString(String.valueOf(process.getArrivalTime()), startX + colWidth, yPos);
            g.drawString(String.valueOf(process.getCpuBurst()), startX + 2 * colWidth, yPos);
            g.drawString(String.valueOf(process.getWaitingTime()), startX + 3 * colWidth, yPos);
            g.drawString(String.valueOf(process.getTurnaroundTime()), startX + 4 * colWidth, yPos);
            yPos += rowHeight;
        }
    }

    // Generate different colors for different processes
    private Color getColor(int index) {
        int colorIndex = index % 8; // Adjust modulus to match the number of unique colors
        switch (colorIndex) {
            case 0:
                return Color.decode("#E0AF61");  // Color 1
            case 1:
                return Color.decode("#EEA995");  // Color 2
            case 2:
                return Color.decode("#EFCABF");  // Color 3
            case 3:
                return Color.decode("#DFD9CA");  // Color 4
            case 4:
                return Color.decode("#E6E6E6");  // Color 5
            case 5:
                return Color.decode("#588A97");  // Color 6
            case 6:
                return Color.decode("#768A96");  // Color 7
            case 7:
                return Color.decode("#F5F1E1");  // Color 8
            default:
                return Color.BLACK;  // Default color
        }
    }
}
