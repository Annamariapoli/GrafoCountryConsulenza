package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import bean.Country;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class SampleController {
	
	private Model model = new Model();
	
	public void setModel(Model model){
		this.model=model;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Country> comboStart;

    @FXML
    private ComboBox<Country> comboEnd;

    @FXML
    private Button btnCompute;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCompute(ActionEvent event) {
    	txtResult.clear();
    	Country c1 = comboStart.getValue();
    	Country c2 = comboEnd.getValue();
    	if(c1 == null || c2 == null){
    		txtResult.appendText("Selezionare due stati! \n ");
    		return;
    	}
    	if(c1.equals(c2)){
    		txtResult.appendText("Selezionare due stati diversi! \n ");
    		return;
    	}	
    	model.buildGraph();
    	List<Country> nazionePerLeQualiDevoPassare = model.calcolaPercorso(c1, c2);
    	if(nazionePerLeQualiDevoPassare.size()==0){
    		txtResult.appendText("I due stati non sono raggiungibili via terra! \n" );
    		return ;  				
    	}
    	else {
    		txtResult.appendText("Il percorso calcolato è : " +nazionePerLeQualiDevoPassare.toString());
    	}
    	
    	//dopo mi da il numero di componenti connesse del grafo creato
    	//ma dell'intero grafo? 

    }

    @FXML
    void initialize() {
        assert comboStart != null : "fx:id=\"comboStart\" was not injected: check your FXML file 'Sample.fxml'.";
        assert comboEnd != null : "fx:id=\"comboEnd\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnCompute != null : "fx:id=\"btnCompute\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

        comboStart.getItems().addAll(model.getCountry());
        comboEnd.getItems().addAll(model.getCountry());
    }
}

