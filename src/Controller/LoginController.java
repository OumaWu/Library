package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Login page is now loaded!");
	}

	@FXML
	private VBox vBox;
	@FXML
	private TextField tfLogin, tfPassword;
	@FXML
	private Button btLogin, btExit;

	@FXML
	public void loginProcess() {
		System.out.println(tfLogin.getText());
		System.out.println(tfPassword.getText());
		this.openManagementWindow();
	}

	@FXML
	public void close() {
		((Stage) this.vBox.getScene().getWindow()).close();
	}

	// Open the management window
	private void openManagementWindow() {

		WindowManager.getInstance().openManagementWindow();
		// Close the current window
		this.close();
	}

}
