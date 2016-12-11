package auto_ckz.common.enums;


import java.util.ArrayList;

public class RepairStatusWrapper {
    public static final RepairStatusWrapper instance = new RepairStatusWrapper();
    public RepairStatus getDONE() { return RepairStatus.DONE; }
    public RepairStatus getIN_PROGRESS() { return RepairStatus.IN_PROGRESS; }
    public RepairStatus getNOT_STARTED() { return RepairStatus.NOT_STARTED; }
    public RepairStatus getSUSPENDED() { return RepairStatus.SUSPENDED; }
    public ArrayList<String> getAll() {
        ArrayList repairStatusList = new ArrayList<String>();
        repairStatusList.add(RepairStatus.DONE);
        repairStatusList.add(RepairStatus.IN_PROGRESS);
        repairStatusList.add(RepairStatus.NOT_STARTED);
        repairStatusList.add(RepairStatus.SUSPENDED);
        return repairStatusList;
    }
}
