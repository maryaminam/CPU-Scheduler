/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */

public class GanttRecord {
    private int inTime;
    private int outTime;
    private String processId;

    public GanttRecord() {
        inTime = 0;
        outTime = 0;
        processId = "";
    }

    public GanttRecord(int inTime, int outTime, String processId) {
        this.inTime = inTime;
        this.outTime = outTime;
        this.processId = processId;
    }

    public int getInTime() {
        return inTime;
    }

    public void setInTime(int inTime) {
        this.inTime = inTime;
    }

    public int getOutTime() {
        return outTime;
    }

    public void setOutTime(int outTime) {
        this.outTime = outTime;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    @Override
    public String toString() {
        return "GanttRecord{" +
                "inTime=" + inTime +
                ", outTime=" + outTime +
                ", processId=" + processId +
                '}';
    }
}

