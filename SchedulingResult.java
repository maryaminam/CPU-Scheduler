public class SchedulingResult {
    private String methodName;
    private double avgWaitingTime;
    private double avgTurnaroundTime;

    public SchedulingResult(String methodName, double avgWaitingTime, double avgTurnaroundTime) {
        this.methodName = methodName;
        this.avgWaitingTime = avgWaitingTime;
        this.avgTurnaroundTime = avgTurnaroundTime;
    }

    public String getMethodName() {
        return methodName;
    }

    public double getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public double getAvgTurnaroundTime() {
        return avgTurnaroundTime;
    }
}
