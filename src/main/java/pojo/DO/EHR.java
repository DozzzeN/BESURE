package pojo.DO;

import java.io.Serializable;
import java.util.Arrays;

public class EHR implements Serializable {
    public byte[] pidD;
    public String depName;//
    public byte[] depID;//
    public String idP;
    public String idD;
    public String HName;//
    public byte[] HID;//
    public byte[] block;//TODO
    public long startCreateTime;
    public long endCreateTime;
    public long startViewTime;//TODO
    public long endViewTime;//TODO

    @Override
    public String toString() {
        return "EHR{" +
                "pidD=" + Arrays.toString(pidD) +
                ", depName='" + depName + '\'' +
                ", depID=" + Arrays.toString(depID) +
                ", idP='" + idP + '\'' +
                ", hospName='" + HName + '\'' +
                ", hospID=" + Arrays.toString(HID) +
                ", block=" + Arrays.toString(block) +
                ", startCreateTime=" + startCreateTime +
                ", endCreateTime=" + endCreateTime +
                ", startViewTime=" + startViewTime +
                ", endViewTime=" + endViewTime +
                '}';
    }

    public byte[] getPidD() {
        return pidD;
    }

    public void setPidD(byte[] pidD) {
        this.pidD = pidD;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public byte[] getDepID() {
        return depID;
    }

    public void setDepID(byte[] depID) {
        this.depID = depID;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
    }

    public String getHName() {
        return HName;
    }

    public void setHName(String HName) {
        this.HName = HName;
    }

    public byte[] getHID() {
        return HID;
    }

    public void setHID(byte[] HID) {
        this.HID = HID;
    }

    public byte[] getBlock() {
        return block;
    }

    public void setBlock(byte[] block) {
        this.block = block;
    }

    public long getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(long startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public long getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(long endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public long getStartViewTime() {
        return startViewTime;
    }

    public void setStartViewTime(long startViewTime) {
        this.startViewTime = startViewTime;
    }

    public long getEndViewTime() {
        return endViewTime;
    }

    public void setEndViewTime(long endViewTime) {
        this.endViewTime = endViewTime;
    }
}
