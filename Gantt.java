import java.awt.*;
import java.util.List;

public class Gantt extends Panel {
    private List<GanttRecord> ganttRecords;

    public Gantt(List<GanttRecord> ganttRecords) {
        this.ganttRecords = ganttRecords;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int startX = 50; // Starting X position
        int startY = 50; // Starting Y position
        int barHeight = 50; // Height of the bars

        // Calculate total time
        int totalTime = ganttRecords.get(ganttRecords.size() - 1).getOutTime();

        // Calculate the scale factor for time
        double scaleFactor = (double) (getWidth() - startX * 2) / totalTime;

        // Draw chart title
        g.drawString("Gantt Chart", getWidth() / 2 - 40, 30);

        // Draw time scale
        g.drawLine(startX, startY + barHeight + 10, getWidth() - startX, startY + barHeight + 10);
        for (int i = 0; i <= totalTime; i++) {
            g.drawLine(startX + (int) (i * scaleFactor), startY + barHeight + 5,
                    startX + (int) (i * scaleFactor), startY + barHeight + 15);
            g.drawString(String.valueOf(i), startX + (int) (i * scaleFactor) - 5, startY + barHeight + 25);
        }

        // Draw process bars
        for (GanttRecord record : ganttRecords) {
            int barWidth = (int) ((record.getOutTime() - record.getInTime()) * scaleFactor);
            g.setColor(getColor(record.getProcessId().hashCode()));
            g.fillRect(startX + (int) (record.getInTime() * scaleFactor), startY, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(startX + (int) (record.getInTime() * scaleFactor), startY, barWidth, barHeight);
            g.drawString(record.getProcessId(), startX + (int) (record.getInTime() * scaleFactor) + barWidth / 2 - 10, startY + barHeight / 2 + 5);
        }
    }

    // Generate different colors for different processes
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
                return Color.decode("#F5F1E1");
            default:
                return Color.BLACK;  // Default color
        }
    }

}
