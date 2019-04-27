package c;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import c.view.ConverterViewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<String> productsList = FXCollections.observableArrayList();
    private ObservableList<String> unitsList = FXCollections.observableArrayList();
    private static ObservableMap<String, Integer> unitsVMap = FXCollections.observableHashMap();
    private static ObservableMap<String, Integer> unitsMMap = FXCollections.observableHashMap();    
    private static ObservableMap<String, Integer> productsMap = FXCollections.observableHashMap();
    
    
	public ObservableList<String> getProductsList() {
		return productsList;
	}

	public ObservableList<String> getUnitsList() {
		return unitsList;
	}

	public static ObservableMap<String, Integer> getUnitsVMap() {
		return unitsVMap;
	}

	public static ObservableMap<String, Integer> getUnitsMMap() {
		return unitsMMap;
	}

	public static ObservableMap<String, Integer> getProductsMap() {
		return productsMap;
	}

	public MainApp() {
		importFile("productsDens.txt", productsMap, productsList);
		importFile("unitsM.txt", unitsMMap, unitsList);
		importFile("unitsV.txt", unitsVMap, unitsList);
	
    }
    
	public void importFile(String path, Map<String, Integer> map, List<String> list){
		
//		File importedFile = new File(path);
		String readFile="";
		

		try {
			
			ClassLoader cLoader = getClass().getClassLoader();
			InputStream input = cLoader.getResourceAsStream(path);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			while ((readFile = br.readLine()) != null) {
				String[] item = readFile.split(",");
				map.put(item[0], Integer.parseInt(item[1]));
				list.add(item[0]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: Incorrect file");
			//e.printStackTrace();
		}	
	}
	

    
    
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Kitchen Calculator");
		primaryStage.getIcons().add(new Image("iconCupcake.png"));
		
		initRootLayout();
		showConverterView();

		
	}



	public void initRootLayout() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
		
		try {
			rootLayout = (BorderPane) loader.load();
			
			Scene mainScene = new Scene(rootLayout);
			
			primaryStage.setScene(mainScene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showConverterView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/ConverterView.fxml"));
		
		try {
			AnchorPane ConverterView = (AnchorPane) loader.load();
			
			rootLayout.setCenter(ConverterView);
			
			ConverterViewController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		
		launch(args);
		
		
	}
}
