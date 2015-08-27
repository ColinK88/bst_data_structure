
/* Methods for Relation interface */ 
public interface Relation<X extends Comparable<X>, Y extends Comparable<Y>> {

	public boolean contains(X x, Y y);
	public void add(X x, Y y);
	public void remove(X x, Y y);	
	public void clear();

	// Removes all Relations that contain a given element X or Y
	public void removeAllX(X x);
	public void removeAllY(Y y);
	
	// Returns a Relation Set of all X and Y, respectively
	public Relation<X,Y> getAllX(X x);	
	public Relation<X,Y> getAllY(Y Y);
	
	// Returns the contents of a Relation as string. (implemented as in-order traversal in case of a BST)
	public String printAll();
	public int size();
	
	
	
	
	
}
