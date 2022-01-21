package tables;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class TrailDanger {

    private final String type;
    private final TextField description;
    private final CheckBox checkBox;
    private final int dangerTypeId;

    public TrailDanger(String type, TextField description, CheckBox checkBox, int dangerTypeId) {
        this.type = type;
        this.description = description;
        this.checkBox = checkBox;
        this.dangerTypeId = dangerTypeId;
    }

    public int getDangerTypeId() {
        return dangerTypeId;
    }

    public String getType() {
        return type;
    }

    public TextField getDescription() {
        return description;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
