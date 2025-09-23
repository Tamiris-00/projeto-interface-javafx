package gui;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {

    private Department entity;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private Label labelErrorName;
    @FXML
    private Button btSave;
    @FXML
    private Button btCancel;

    public void setDepartment(Department entity)  {
        this.entity = entity;
    }

    @FXML
    public void onBtSaveAction(){
        System.out.println("save");
    }
    @FXML
    public void onBtCancelAction(){
        System.out.println("cancel");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();

    }
    private void initializeNodes(){
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }

    public void updateFormData() {
        if(entity == null){
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(entity.id() == null ? "" : String.valueOf(entity.id()));
        txtName.setText(entity.name() == null ? "" : entity.name());
    }
}
