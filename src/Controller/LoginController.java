package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.DatabaseManager;
import Persistence.DBTool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DatabaseManager.configCRUDInstance(DBTool.MYSQL, "localhost", "library", "root", "111");
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
		boolean result;
		
		try {
			String login = tfLogin.getText();
			String pwd = tfPassword.getText();
			
			//Check if the user is registered
			result = DatabaseManager.administratorCRUD.loginAdministrator(login, pwd);
			
			if(result)
				this.openManagementWindow();
			else
				WindowManager.getInstance().promptAlert("Error! Wrong login or password!");
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
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
