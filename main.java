public class main {

/*
All methods are tested and don't appear to show any errors. Errors were identified throughout development and may well still be present.. but then...
You can prove the presence of bugs, but not their absence. 

Please find attached, a sample output textfile named output.txt which shows the console output.

The following methods are tested: 

.contains
.getAllX
.getAllY
.clear
.add
.remove
.removeAllX
.removeAllY
.printAll

*/
	public static void main(String[] args) {
		
	
		Relation<Integer, String> test = new TreeRelation<Integer, String>();
		
		System.out.println("Adding the following Relations - {1, cat}, {1,cat}, {1,dog}, {6,rat}, {4,snake}, {9,mouse}, {1,cat}");
		
		test.add(30, "bird");
		test.add(1, "cat");
		test.add(1,"cat");
		test.add(1, "dog");
		test.add(6, "rat");
		test.add(4, "snake");
		test.add(9, "mouse");
		test.add(1,"cat");
		
		
		System.out.println("\nAfter operation, printAll() returns the following: \n"+test.printAll());
		
		System.out.println("expected size is 6: size:"+test.size()); //  should be 6
		
		System.out.println("Check if TreeRelation contains Relation {6, rat} - "+ test.contains(6, "rat"));
		System.out.println("Check if TreeRelation contains Relation {14, rat} - "+ test.contains(14, "rat")+"\n");
		
		test.remove(9, "mouse");
		test.remove(1, "cat");
		test.remove(6, "rat");
		
		
		System.out.println("Removing the following elements - {6,rat}, {1, cat}, {9, mouse}");
		
		System.out.println("After operation, printAll() returns the following: \n"+test.printAll());
		
		System.out.println("expected size is 3: size:"+test.size());
		
		System.out.println("Check if TreeRelation contains Relation {6, rat} - "+ test.contains(6, "rat"));
		System.out.println("Check if TreeRelation contains Relation {1, dog} - "+ test.contains(1, "dog")+"\n");
		
		// add new elements to test getAllX, getAllY
		test.add(1, "bird");
		test.add(1, "tiger");
		test.add(5, "mouse");
		test.add(7, "mouse");
		
		System.out.println("Adding following Relations: {1, bird}, {1, tiger}, {5, mouse}, {7, mouse}");
		
		System.out.println("Updated TreeRelation has following output: \n"+test.printAll());
		
		Relation<Integer, String> subX = test.getAllX(1);
		Relation<Integer, String> subY = test.getAllY("mouse");
		
		System.out.println("Build new relation with all Relations containing element X - '1'");
		
		System.out.println("Has the following output: \n"+ subX.printAll());
		
		System.out.println("Build new relation with all Relations containing element Y 'mouse'");
		
		System.out.println("Has the following output: \n"+ subY.printAll());
		
		System.out.println("Removing all Relations containing element X '1'");
		test.removeAllX(1);
		
		System.out.println("Has the following output: \n"+ test.printAll()+"...and is now of size: "+test.size());
		
		System.out.println("Removing all Relations containing element Y 'mouse'");
		test.removeAllY("mouse");
		
		System.out.println("Has the following output: \n"+ test.printAll()+"...and is now of size: "+test.size());

		System.out.println("Check if TreeRelation contains {7, mouse} - "+test.contains(7, "mouse"));
		
		System.out.println("And lastly... clear the entire TreeRelation: ");
		test.clear();
		
		System.out.println("Which is now of size "+ test.size());
	}

}
