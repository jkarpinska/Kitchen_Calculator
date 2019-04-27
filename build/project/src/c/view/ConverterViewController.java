package c.view;

import c.MainApp;
import c.model.Calculations;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ConverterViewController {

	
	@FXML
	private ComboBox<String> cbProducts;
	@FXML
	private ComboBox<String> cbUnits1;
	@FXML
	private ComboBox<String> cbUnits2;
	@FXML
	private TextField txtAmount;
	@FXML
	private TextField txtAnswer;
	@FXML
	private Button btConvert;
	
	
	private MainApp mainApp;
	
	public ConverterViewController() {
		
	}
	
	private void showAnswer(String answer) {
		if(answer!=null)
			txtAnswer.setText("= "+answer);
		else
			txtAnswer.setText("= ");
	}
	
	private String runCalculations(String product, double amount, String unit1, String unit2) {
		Calculations calc = new Calculations();
		String answer = String.valueOf(calc.convert(product, amount, unit1, unit2));

		return answer;
	}
	    
    private boolean isInputValid() {
    	String errorMessage = "";
    	if (cbProducts.getValue() == null) {
    		errorMessage += "Choose a product!\n";
    	}
    	if (cbUnits1.getValue() == null) {
    		errorMessage += "Choose first unit!\n";
    	}
    	if (cbUnits2.getValue() == null) {
    		errorMessage += "Choose second unit!\n";
    	}
    	if (txtAmount.getText() == null || txtAmount.getText().length() == 0) {
    		errorMessage += "Enter the amount!\n";
    	}
        if (errorMessage.length() == 0) {
            return true;
        } else {
            
            Alert alert = new Alert(AlertType.ERROR);
            //alert.initOwner(stage);
            alert.setTitle("Missing information");
            alert.setHeaderText("Please fill in the missing fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
        }
    	return false;
    }
    
    @FXML
    private void handleBt() {
    	if (isInputValid()) {
    		String prodEntered = cbProducts.getValue();
			double amountEntered = Double.parseDouble(txtAmount.getText());
			String unit1Entered = cbUnits1.getValue();
			String unit2Entered = cbUnits2.getValue();
			
			showAnswer(runCalculations(prodEntered, amountEntered, unit1Entered, unit2Entered));
			
    	}
    }
	
	@FXML
	private void initialize() {
		showAnswer(null);
		
		txtAmount.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    txtAmount.setText(oldValue);
                }
            }
        });
		
		btConvert.setOnAction(new EventHandler<ActionEvent>(){
			@Override 
			public void handle(ActionEvent e) {
				handleBt();
			}
		});
	}
	
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        cbProducts.getItems().addAll(mainApp.getProductsList());
        cbUnits1.getItems().addAll(mainApp.getUnitsList());
        cbUnits2.getItems().addAll(mainApp.getUnitsList());

    }

}
