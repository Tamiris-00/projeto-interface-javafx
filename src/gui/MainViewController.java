package gui;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuItemSeller;
    @FXML
    private MenuItem menuItemDepartment;
    @FXML
    private MenuItem menuItemAbout;

    @FXML
    public void onMenuItemSellerAction(){


    }

    @FXML
    public void onMenuItemDepartmentAction(){

    }

    @FXML
    public void onMenuItemAboutAction(){
        loadView();

    }


    @Override
    public void initialize(URL uri, ResourceBundle rb){

    }

    private void loadView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/About.fxml"));
            VBox newVbox = loader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVbox =(VBox) ((ScrollPane)mainScene.getRoot()).getContent();

            Node mainMenu = mainVbox.getChildren().getFirst();
            mainVbox.getChildren().clear();
            mainVbox.getChildren().addAll(newVbox.getChildren());

        }catch (IOException e){
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
