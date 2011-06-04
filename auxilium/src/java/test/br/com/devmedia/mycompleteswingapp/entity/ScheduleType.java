
package test.br.com.devmedia.mycompleteswingapp.entity;

public enum ScheduleType {
    SHOWER("Take a Shower"), CUTTING("Ready to the Cutting");

    private String description;

    ScheduleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
