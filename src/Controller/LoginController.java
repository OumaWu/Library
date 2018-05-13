package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class LoginController implements Initializable {

	public void loginButtonClicked() {
		System.out.println("User login...");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("View is now loaded!");
	}

	// fx:id="button" onAction="#handleButtonClick"
	// public Button button;
	//
	// public void handleButtonClick() {
	// System.out.println("Action handled in Controller");
	// button.setText("Stop touching me!");
	// }

}
