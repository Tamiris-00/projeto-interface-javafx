package gui;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable{

    @FXML
    private DepartmentService service;
    @FXML
    private TableView<Department> tableViewDepartment;
    @FXML
    private TableColumn <Department, Integer> tableColumnId;
    @FXML
    private TableColumn<Department, String> tableColumnName;
    @FXML
    private Button btNew;

    @FXML
    public void onBtNewAction(ActionEvent event){
        Stage parentStage = Utils.currentStage(event);
        Department obj = new Department(null, null);
        createDialogForm(obj, "/gui/DepartmentForm.fxml", parentStage);
    }
    public void setDepartmentService(DepartmentService service){
        this.service = service;
    }

    public void initialize(URL url, ResourceBundle rb){
        initializeNodes();

    }

    private void initializeNodes(){
        tableColumnId.setCellValueFactory(cd ->
                new ReadOnlyObjectWrapper<>(cd.getValue().id()));   // <- id()

        tableColumnName.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(cd.getValue().name()));
        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView(){
        if(service ==null){
            throw new IllegalStateException("Service null");
        }
        List<Department> list = service.findAll();
        ObservableList<Department> obsList = FXCollections.observableArrayList(list);
        tableViewDepartment.setItems(obsList);
    }

    private void createDialogForm(Department obj,String absoluteName, Stage parentStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            DepartmentFormController controller = loader.getController();
            controller.setDepartment(obj);
            controller.updateFormData();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Enter department data");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();


        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error Loading view",e.getMessage(), Alert.AlertType.ERROR );
        }

    }
}
