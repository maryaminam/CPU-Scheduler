import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ComparisonChart extends Panel {
    private List<SchedulingResult> results;
    private int barWidth = 60; // Reduced bar width for compactness
    private int barSpacing = 150; // Adjusted bar spacing for longer x-axis scale
    private int startX = 50;
    private int startY = 50;
    private int chartWidth = 1000; // Increased chart width for longer x-axis scale
    private int chartHeight = 200; // Reduced chart height for compactness
    private int yAxisLabelWidth = 60; // Reduced y-axis label width for compactness
    private int xAxisLabelHeight = 20;
    private int spaceBetweenCharts = 150; // Increased space between the two charts

    public ComparisonChart(List<SchedulingResult> results) {
        this.results = results;
        setPreferredSize(new Dimension(1000, 600)); // Adjusted preferred size
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBarChart(g);
    }

    private void drawBarChart(Graphics g) {
        // Set font
        g.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 14));

        // Draw chart title for Average Waiting Time
        g.drawString("Average Turnaround Time", startX + (chartWidth - 200) / 2, startY - 20);

        // Draw y-axis and x-axis for average waiting time
        g.drawLine(startX, startY, startX, startY + chartHeight); // y-axis
        g.drawLine(startX, startY + chartHeight, startX + chartWidth, startY + chartHeight); // x-axis

        // Label y-axis for average waiting time
        g.drawString("Time (ms)", startX - 40, startY - 10);

        // Find the maximum values for wait time and turnaround time
        double maxWaitTime = Double.MIN_VALUE;
        double maxTurnaroundTime = Double.MIN_VALUE;
        for (SchedulingResult result : results) {
            maxWaitTime = Math.max(maxWaitTime, result.getAvgWaitingTime());
            maxTurnaroundTime = Math.max(maxTurnaroundTime, result.getAvgTurnaroundTime());
        }

        // Draw bars for average wait time
        int x = startX + yAxisLabelWidth;
        int[] xCoordinates = new int[results.size()];
        int index = 0;
        for (SchedulingResult result : results) {
            g.setColor(getColor(index));
            double avgWaitingTime = result.getAvgWaitingTime();
            int barHeight = (int) (avgWaitingTime * 8); // Scale the height
            g.fillRect(x, startY + chartHeight - barHeight, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawString(String.format("%.2f", avgWaitingTime), x + 10, startY + chartHeight - barHeight - 5);
            g.drawString(result.getMethodName(), x - 8, startY + chartHeight + xAxisLabelHeight + 10);
            xCoordinates[index++] = x; // Store the x coordinate
            x += barWidth + barSpacing;
        }

        // Move down for the second chart
        int startY2 = startY + chartHeight + spaceBetweenCharts;

        // Draw chart title for Average Turnaround Time
        g.drawString("Average Waiting Time", startX + (chartWidth - 200) / 2, startY2 - 20);

        // Draw y-axis and x-axis for average turnaround time
        g.drawLine(startX, startY2, startX, startY2 + chartHeight); // y-axis
        g.drawLine(startX, startY2 + chartHeight, startX + chartWidth, startY2 + chartHeight); // x-axis

        // Label y-axis for average turnaround time
        g.drawString("Time (ms)", startX - 40, startY2 - 10);

        // Draw bars for average turnaround time
        index = 0;
        for (SchedulingResult result : results) {
            g.setColor(getColor(index));
            double avgTurnaroundTime = result.getAvgTurnaroundTime();
            int barHeight = (int) (avgTurnaroundTime * 8); // Scale the height
            g.fillRect(xCoordinates[index], startY2 + chartHeight - barHeight, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawString(String.format("%.2f", avgTurnaroundTime), xCoordinates[index] + 10, startY2 + chartHeight - barHeight - 5);
            g.drawString(result.getMethodName(), xCoordinates[index] - 8, startY2 + chartHeight + xAxisLabelHeight + 10);
            index++;
        }
    }

    private Color getColor(int index) {
        switch (index) {
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
