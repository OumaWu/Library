package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("View is now loaded!");
	}

	@FXML
	private VBox vBox;
	// private Stage window;
	@FXML
	private TextField tfLogin, tfPassword;
	@FXML
	private Button btLogin, btExit;

	// public void setStage(Stage stage) {
	// this.vBox = stage;
	// }

	@FXML
	public void loginProcess() {
		System.out.println(tfLogin.getText());
		System.out.println(tfPassword.getText());
		this.openManagementWindow();
	}

	@FXML
	public void exit() {
		((Stage) this.vBox.getScene().getWindow()).close();
	}

	// Open the management window
	private void openManagementWindow() {

		Parent root;

		try {
			root = FXMLLoader.load(getClass().getResource("/View/Management.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			// stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Books Management");
			stage.setScene(new Scene(root));
			stage.show();
			// Close the current window
			this.exit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error ! Open failed !!");
			e.printStackTrace();
		}
	}

	// public void closeWindow() {
	//
	// }

}
