Submitted by: Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)

Below is the description of the content of the files:

1. axg137230_Client.java : This file contains the main method. It reads input from Standard Input and parse the operations.

2. axg137230_InputOperations.java : This file contains the class implementing method which accept single operation and decides what to do with it. It also stores the output of all the operations and return in the end.

3. axg137230_ParallelDataStructure.java : This file contains the class implementing parallel data structures storing reference of item at multiple locations. The class also contains the method implementing various operations on the data structures like inserting, searching, updating ...

4. axg137230_Item.java : This file contains class implementing the structure of an item.

5. axg137230_HashMapById.java : This file contains class implementing hash map with key as id of the item and value as item reference.

6. axg137230_LinkedListById.java : This file contains implementation of linked list containing items. This helps in collision resolution of the items stored in the hash map.

7. axg137230_HashMapByName.java : This file contains class implementing hash map with key as name of the item and value as Red black tree storing item reference.

8. axg137230_LinkedListByName.java : This file contains implementation of linked list containing Red black trees. This helps in collision resolution of the trees stored in the hash map by name.

9. axg137230_Queue.java : This file contains implementation of standard Queue.

10. axg137230_RedBlackBST.java : This file contains implementation of Red black tree in which key is price of the item and value is linked list of items. A linked list in the node of tree contains items with same price.

11. axg137230_RedBlackKeyTree.java : This file contains implementation of Red black tree in which key is id and value is also id of the item.

HOW TO RUN THE PROGRAM:- 
Compile each file using :  $ javac axg137230_Client.java
Run using : $ java axg137230_Client < Input_file
