package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	private VBox vBox;

	@FXML
	private Button closeButton;

	@FXML
	private Label lbMsg;

	public void setMessage(String msg) {
		lbMsg.setText(msg);
	}

	public void close() {
		((Stage) this.vBox.getScene().getWindow()).close();
	}

}
