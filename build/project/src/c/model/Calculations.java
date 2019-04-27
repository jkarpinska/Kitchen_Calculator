package c.model;

import java.util.HashMap;
import java.util.Map;

import c.MainApp;


public class Calculations {
	
	public Calculations() {
		importMaps();
	}


	//TO ADD IN THE FUTURE:
	//1. METHODS FOR ADDING NEW PRODUCTS AND UNITS AND SAVE CHANGES TO FILES
	

	private static Map<String,Integer> productsMap = new HashMap<>();
	private static Map<String,Integer> unitsVMap = new HashMap<>();
	private static Map<String,Integer> unitsMMap = new HashMap<>();
	public static double answer;
	
    public void importMaps() {

        productsMap.putAll(MainApp.getProductsMap());
        unitsVMap.putAll(MainApp.getUnitsVMap());
        unitsMMap.putAll(MainApp.getUnitsMMap());
       
    }
	

	
	private double convertVUnits(double amount, String unit1, String unit2) {
		double size1 = unitsVMap.get(unit1);
		double size2 = unitsVMap.get(unit2);
		
		double x = amount*size1/size2;
		
		return x;
	}
	
	private double convertMUnits(double amount, String unit1, String unit2) {
		double size1 = unitsMMap.get(unit1);
		double size2 = unitsMMap.get(unit2);
		
		double x = amount*size1/size2;
		
		return x;
	}
	
	private double convertVtoMUnits(String product, double amount, String unit1, String unit2) {
		double size1 = unitsVMap.get(unit1);
		double size2 = unitsMMap.get(unit2);
		double density = productsMap.get(product);
		
		double x = amount*size1*density/1000*size2;
		
		return x;
	}

	private double convertMtoVUnits(String product, double amount, String unit1, String unit2) {
		double size1 = unitsMMap.get(unit1);
		double size2 = unitsVMap.get(unit2);
		double density = productsMap.get(product);
		
		double x = amount*size1*1000/(density*size2);
		
		return x;
	}
	
	private String conversionType(String unit1, String unit2) {
			
		String convert = "";
		if(unitsVMap.containsKey(unit1)) {
			convert += "V";
		}
		else if(unitsMMap.containsKey(unit1)) {
			convert += "M";
		}
		else
			System.out.println("unit1 not found in list: "+unit1);
		if(unitsVMap.containsKey(unit2)) {
			convert += "toV";
		}
		else if(unitsMMap.containsKey(unit2)) {
			convert += "toM";
		}
		else
			System.out.println("unit2 not found in list: "+unit2);

		return convert;
	}
	
	public double convert(String product, double amount, String unit1, String unit2) {

		String type = conversionType(unit1, unit2);
		double answer = 0;
		
		switch(type) {
		case "VtoV":
			answer = convertVUnits(amount, unit1, unit2);
			break;
		case "MtoM":
			answer = convertMUnits(amount, unit1, unit2);
			break;
		case "VtoM":
			answer = convertVtoMUnits(product, amount, unit1, unit2);
			break;
		case "MtoV":
			answer = convertMtoVUnits(product, amount, unit1, unit2);
			break;
		}

		return answer;
	}
	
/*	FOR TESTING CALCULATIONS LOGIC
 * 
 * public static void main(String args[]) {
		
		Calculations calc = new Calculations();
		
		System.out.println(unitsList);
		System.out.println(productsList);
		System.out.println(unitsVMap);
		System.out.println(unitsMMap);
		System.out.println(productsMap);
		
		double answer1 = calc.convert("flour", 1, "kg", "g");
		double answer2 = calc.convert("flour", 1, "glass", "teaspoon");
		double answer3 = calc.convert("flour", 120, "g", "glass");
		double answer4 = calc.convert("flour", 1, "glass", "g");
		System.out.println(answer1+", "+answer2+", "+answer3+","+answer4);
	

		
	}*/
}
