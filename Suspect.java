/**
 * Tyler Lericos
 * Johnson
 * CS215
 * Final Project
 */
class Suspect {
    String name;//Name
    String reasonForArrest;//Crime
    String status; // "In custody" or "Released"
    int timeOfInduction;//Time of Induction (For now it's the time step for simplicity
    boolean inHoldingCell;

    Suspect(String name, String reasonForArrest, int timeOfInduction) {
        this.name = name;
        this.reasonForArrest = reasonForArrest;
        this.status = "In custody";
        this.timeOfInduction = timeOfInduction;
        this.inHoldingCell = false;
    }
}
