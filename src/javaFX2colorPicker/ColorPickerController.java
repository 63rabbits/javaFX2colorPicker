package javaFX2colorPicker;

import javafx.beans.binding.ObjectBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ColorPickerController {

	@FXML
	private ColorPicker crp;
	@FXML
	private Pane pan;

	@FXML
	private ColorPicker crpLBind;
	@FXML
	private Pane panLBind;

	@FXML
	void initialize() {

		// Using Event Handler
		assert crp != null : "fx:id=\"crp\" was not injected: check your FXML file 'ColorPicker.fxml'.";
		assert pan != null : "fx:id=\"pan\" was not injected: check your FXML file 'ColorPicker.fxml'.";
		this.crp.setValue(Color.BLACK);
		String sStyleBase = "-fx-background-color: $bgcolor";
		String sStyle = crp.getValue().toString().replace("0x", "#");
		this.pan.setStyle(sStyleBase.replace("$bgcolor", sStyle));

		// Using Bind (High-level API)

		// Using Bind (Low-level API)
		assert crpLBind != null : "fx:id=\"crpLBind\" was not injected: check your FXML file 'ColorPicker.fxml'.";
		assert panLBind != null : "fx:id=\"panLBind\" was not injected: check your FXML file 'ColorPicker.fxml'.";
		this.panLBind.styleProperty().bind(observer(crpLBind));
		this.crpLBind.setValue(Color.BLACK);

	}

	// Using Event Handler
	@FXML
	void crpOnAction(ActionEvent event) {
		String sStyleBase = "-fx-background-color: $bgcolor";
		String sStyle = crp.getValue().toString().replace("0x", "#");
		this.pan.setStyle(sStyleBase.replace("$bgcolor", sStyle));
	}

	// Using Bind (High-level API)

	// Using Bind (Low-level API)
	private ObjectBinding<String> observer(ColorPicker p) {
		final ColorPicker crp = p;
		ObjectBinding<String> sBinding = new ObjectBinding<String>() {
			{
				super.bind(crp.valueProperty());
			}

			@Override
			protected String computeValue() {
				String sStyleBase = "-fx-background-color: $bgcolor";
				String sStyle = crp.getValue().toString().replace("0x", "#");
				return sStyleBase.replace("$bgcolor", sStyle);
			}
		};
		return sBinding;
	}
}
