package main;

public class Main {
	public static void main(String[] args) {
		LocationImport locationImport = new LocationImport();
		CompanyImport companyImport=new CompanyImport();
		PersonImport personImport = new PersonImport();
		SubsidyImport subsidyImport = new SubsidyImport();
		ViolationsImport violationsImport = new ViolationsImport();
		TrainingImport trainingImport = new TrainingImport();
		ImmuneImport immuneImport = new ImmuneImport();
		//locationImport.importLocation();
		//companyImport.importCompany();
		//personImport.importPerson();
		subsidyImport.importSubsidy();
		//violationsImport.importViolations();
		//trainingImport.importTraining();
		immuneImport.importImmune();
		//		tab.Import_location();
//		tab.Import_person();
		//locationImport.Import_company();
	}
}
