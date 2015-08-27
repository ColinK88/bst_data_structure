public class TreeRelation<X extends Comparable<X>, Y extends Comparable<Y>> implements Relation<X ,Y>{
	// X, Y variables
	private X x;
	private Y y;
	// size of TreeRelation
	private int size = 0;
	
	// output for printAll() is declared here to ensure string isn't overwritten after each recrusion
	private static String printedRelation = "";
	
	// top node of a given Tree
	private Node root;
	
	/* Default constructor */
	public void TreeRelation(){

		root = null;

	}

	/* inner class, defining a node */
	private static class Node<X extends Comparable<X>, Y extends Comparable<Y>>{

		protected X x;
		protected Y y;

		protected Node<X, Y> left, right;

		public Node(X x, Y y){

			// elements to be stored
			this.x = x;
			this.y = y;

			// child nodes of current node
			left = null;
			right = null;

		}
		
		/* Recursive - call to delete a specified node. used in _remove() and remove() */
		public Node<X, Y> deleteTopMost(){
			if (this.left == null){
				return this.right;
			}
			else if(this.right == null){
				return this.left;
			}		
			else{// node has two children
				this.x = this.right.getLeftMostX();
				this.right = this.right.deleteTopMost();
				return this;
			}
		}
		
		/* find and return leftmost node in a given tree */
		private X getLeftMostX(){
			Node<X,Y> curr = this;
			// while there is a left node, traverse through tree, left.
			while(curr.left != null){
				curr = curr.left;
			}
			//
			return curr.x;

		}
		
		/* finds leftmost node and deletes */
		public Node<X, Y> deleteLeftMost(){
			if (this.left == null){
				return this.right;
			}else{
				Node<X,Y> parent = this, curr = this.left;
				while(curr.left != null){
					parent = curr;
					curr = curr.left;
				}

				parent.left = curr.right;
				return this;
			}
		}

	}
	/* End of Inner Class */

	/* Implementation of Relation methods*/
	
	/* Search to determine whether a given Relation is contained within TreeRelation */
	public boolean contains(X x, Y y){
		
		int directionX = 0;
		int directionY = 0;
		
		// target is a given relation (x,y)
		// initially set to false as it hasn't been found...
		boolean target = false;
		// set current node to root to begin traversal of Tree
		TreeRelation.Node<X,Y> curr = root;

		for(;;){
			if(curr==null){
				// nothing is contained in list, return false and stop search
				target = false;
				break;
			}
			// Determine which direction to go by order of comparable x
			directionX = x.compareTo(curr.x);
			// Use direction Y if directionX returns 0, indicating a matching element(x)
			directionY = y.compareTo(curr.y);
			// target is a given relation of comparable(x,y)
			target = (x.compareTo(curr.x) ==0 && y.compareTo(curr.y) ==0);
			if(target){
				// target has been found, return true and stop search
				target = true;
				break;
			}else{
				
				/* Tree Traversal */			
				if(curr.right ==null && curr.left==null){
					// if both right and left nodes are null, max depth has been reached
					// target is not contained in list, return false and stop search
					target = false;
					break;
				}
				// traverse through tree via X
				if(directionX == 0){
					// if matching x has been found, decide next direction by comparing y element
					if(directionY < 0){
						curr = curr.left;
					}
					else if(directionY > 0){
						curr = curr.right;
					}
				
				// go left or right depending on compareTo value of x
				}else if(directionX < 0){
					curr = curr.left;
				}else if(directionX > 0){
					curr = curr.right;
				} 

			}
		}
		return target;
	}



	public void add(X x, Y y){
		int directionX = 0;
		int directionY = 0;
		boolean duplicate;

		// Instantiate parent and current nodes for traversal
		TreeRelation.Node<X, Y> parent = null, curr = root;

		for(;;){
			// if current node is null,
			if(curr==null){
				TreeRelation.Node<X, Y> ins = new TreeRelation.Node<X, Y>(x, y);

				// if root has no node, insert relation to root.
				if (root==null){
					root = ins;
					size++;
				}
				// if X insert is less than X at that current node, move left and set X to that new node
				else if(directionX<0){
					parent.left = ins;
					size++;
				}
				// if X insert is greater than X at that current node, move right and set X to that new node
				else if(directionX >0){
					parent.right = ins;
					size++;
					// 	// if X insert is equal to X at that current node, move right and set X to that new node
				}else if(directionX==0){
					if(directionY < 0){
						parent.left = ins;

					}else if(directionY > 0 ){
						parent.right = ins;
					}
					size++;
				}
				return;
			}
			// Determine which direction to go by order of comparable x
			directionX = x.compareTo(curr.x);
			// Use direction Y if directionX returns 0, indicating a matching element(x)
			directionY = y.compareTo(curr.y);

			// duplicate is true if compareTo returns 0 on both X and Y.
			duplicate = (x.compareTo(curr.x) ==0 && y.compareTo(curr.y) ==0);

			/* Tree Traversal */
			if(duplicate){
				// If a duplicate relation has been found, do nothing and return from loop.
				return;
				// otherwise begin traversal
			}else{
				// traverse through Tree via comparable X
				if(directionX==0){
					// if matching x has been found, decide next direction by comparing y element
					if(directionY <0){
						parent = curr;
						curr = curr.left;
					}
					else if(directionY>0){
						parent = curr;
						curr = curr.right;
					}

				}
				if(directionX < 0){
					parent = curr;
					curr = curr.left;
				}else if(directionX > 0){
					parent = curr;
					curr = curr.right;
				}
			}

		}
	}

	public void remove(X x, Y y){
		
		int directionX = 0;
		int directionY = 0;
		// target is Relation of comparable(x,y)
		boolean target = false;
		
		// set current node to root and define a parent to reconnect to after deletion
		TreeRelation.Node<X,Y> parent = null, curr = root;

		for(;;){
			if(curr ==null){
				// nothing in Relation to delete, return and stop search
				return;
			}
			
			// target is Relation of comparable(x,y) both returning 0
			target = (x.compareTo(curr.x) ==0 && y.compareTo(curr.y) ==0);

			// Determine which direction to go by order of comparable x
			directionX = x.compareTo(curr.x);
			// Use direction Y if directionX returns 0, indicating a matching element(x)
			directionY = y.compareTo(curr.y);

			if(target){
				// if target has been found. call deleteTopMost on that node
				TreeRelation.Node<X,Y> del = curr.deleteTopMost();
				
				// find parent of target node to be deleted
				if(curr==root){
					root = del;
					size--;
				}else if(curr== parent.left){
					size--;
					parent.left = del;
				}else if(curr == parent.right){
					size--;
					parent.right=del;
				}
				return;

			}
			
			/* tree traversal from root to target */
			parent = curr;
			if(directionX == 0){
				if(directionY <0){
					curr = parent.left;
				}
				else if(directionY>0){
					curr = parent.right;
				}
			}
			else if(directionX <0){
				curr = parent.left;
			}else if(directionX >0){
				curr = parent.right;
			}
		}
	}


	/* calls recursive method to delete each node in turn */
	public void clear(){
	
		_clear(root);
		
	}
	/* In order traversal of BST, deleting each node until root is null */
	private <X extends Comparable<X>, Y extends Comparable<Y>> void _clear(TreeRelation.Node<X,Y> top){
		if(top != null){

			_clear(top.left);
			_remove(top);				
			_clear(top.right);
		}
	}

	/* Call of recursive method, removes all Relations that contain a given element X*/
	public void removeAllX(X x){

		_removeAllX(root, x);


	}
	/* In order traversal of BST, calls recursive method, deleting target node which has matching element X. Traverses through BST until each node has been visited*/
	private <X extends Comparable<X>, Y extends Comparable<Y>> void _removeAllX(TreeRelation.Node<X,Y> top, X x) {

		int target = 0;
		if(top != null){
			// Target is compareTo of input X and current node x == 0
			target = (x.compareTo(top.x));
			_removeAllX(top.left, x);
			// if target X matches X in current node..
			if(target == 0){
				// call remove method on that node
				_remove(top);				
			}
			_removeAllX(top.right, x);
		}

	}
	
	/* Call of recursive method, removes all Relations that contain a given element Y*/
	public void removeAllY(Y y){

		_removeAllY(root, y);


	}

	/* In order traversal of BST, calls recursive method, deleting target node which has matching element Y. Traverses through BST until each node has been visited*/
	private <X extends Comparable<X>, Y extends Comparable<Y>> void _removeAllY(TreeRelation.Node<X,Y> top, Y y) {

		int target = 0;
		if(top != null){
			// Target is compareTo of input Y and current node Y == 0
			target = (y.compareTo(top.y));
			_removeAllY(top.left, y);
			// if target X matches X in current node..
			if(target == 0){
				// call remove method on that node
				_remove(top);				
			}
			_removeAllY(top.right, y);
		}

	}
	/* Identical to standard remove() method, except that it takes a Node as an argument, making it compatible with above methods */
	private void _remove(TreeRelation.Node targetNode){
		int directionX = 0;
		int directionY = 0;
		// target is comparable X and Y elements of target node = 0. initially false.
		boolean target = false;

		// set current node to root and define a parent to reconnect to after deletion
		TreeRelation.Node<X,Y> parent = null, curr = root;

		for(;;){
			if(curr ==null){
				// nothing in Relation to delete
				return;
			}
			// target is comparable X and Y elements of target node = 0.
			target = (targetNode.x.compareTo(curr.x) ==0 && targetNode.y.compareTo(curr.y) == 0);

			
			directionX = targetNode.x.compareTo(curr.x);
			directionY = targetNode.y.compareTo(curr.y);

			if(target){
				// call deletion method on target node
				TreeRelation.Node<X,Y> del = curr.deleteTopMost();
				
				// find parent of target node to be deleted
				if(curr==root){
					root = del;
					size--;
				}else if(curr== parent.left){
					size--;
					parent.left = del;
				}else if(curr == parent.right){
					size--;
					parent.right=del;
				}
				return;
			}
			// set parent to curr after deletion
			parent = curr;
			
			/* Traversal of Tree */
			if(directionX == 0){
				// if directionX is 0, matching element has been found.. look at Y instead
				if(directionY <0){
					curr = parent.left;
				}
				else if(directionY>0){
					curr = parent.right;
				}
			}
			else if(directionX <0){
				curr = parent.left;
			}else if(directionX >0){
				curr = parent.right;
			}
		}
	}


	public Relation<X,Y> getAllX(X x){
		// declare sub TreeRelation which will contain all Relations with target element X
		Relation<X,Y> sub = new TreeRelation<X,Y>();
		
		// Populate sub list by calling recursive method
		Relation<X,Y> subReturn = _getAllX(root, x, sub);

		return subReturn;
	}

	/* recursive call for iterating through entire tree
	 * @param current node, target X, sub 
	 * @return sub TreeRelation containing all target X
	 */
	private <X extends Comparable<X>, Y extends Comparable<Y>> Relation _getAllX(TreeRelation.Node<X,Y> top, X x, Relation sub) {

		int target = 0;
		if(top != null){
			// target is every instance of x compared to input parameter(x)
			target = (x.compareTo(top.x));
			// call method recursively to get all left branches
			_getAllX(top.left, x, sub);
			// if target x has been found...
			if(target == 0){
				// add target Relation to list
				sub.add(top.x, top.y);
			}
			// call method recursively to check all right branches
			_getAllX(top.right, x, sub);
		}
		// return sub TreeRelation back to public method.
		return sub;
	}

	public Relation<X,Y> getAllY(Y y){

		Relation<X,Y> sub = new TreeRelation<X,Y>();

		Relation<X,Y> subReturn = _getAllY(root, y, sub);

		return subReturn;
	}

	/* recursive call for iterating through entire tree
	 * @param current node, target X, sub 
	 * @return sub TreeRelation containing all target X
	 */
	private <X extends Comparable<X>, Y extends Comparable<Y>> Relation _getAllY(TreeRelation.Node<X,Y> top, Y y, Relation sub) {

		int target = 0;
		if(top != null){
			// target is every instance of y compared to input parameter(x)
			target = (y.compareTo(top.y));
			// call method recursively to get all left branches
			_getAllY(top.left, y, sub);
			// if target y has been found...
			if(target == 0){		

				// add target Relation to list
				sub.add(top.x, top.y);
			}
			// call method recursively to check all right branches
			_getAllY(top.right, y, sub);
		}
		// return sub TreeRelation back to public method.
		return sub;
	}

	// Returns the size of a given TreeRelation. Mostly for debugging.
	public int size(){
		return size;
	}
	
	// public method call to return formatted string
	public String printAll(){
		printedRelation = "";
	
		
		// pass empty string to recursive method, initially
		String print="";
		// string to be returned-
		String xy = "";
		
		xy += _printAll(root, print);

		return xy;

	}

	/* return formatted string of every X and Y element in TreeRelation. In order traversal 
	 * prints in order of X
	 * 
	 */
	private <X extends Comparable<X>, Y extends Comparable<Y>> String _printAll(TreeRelation.Node<X,Y> top, String xy){

		
		String x ="";
		String y= "";
		
		// continue while next node is not null
		if(top != null){
			_printAll(top.left, xy);
			
			x += ""+top.x;
			y += ""+top.y;
			
			// Build string with element of each current(top) node
			printedRelation += String.format("%s - %s%n", x, y);
			_printAll(top.right, xy);

		}

		return printedRelation;

	}

}


