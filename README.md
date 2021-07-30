# Final Project: 
## A comparison between Suurballe and TSSP algorithms to build a survivable path from 3 graphs: NSFNet, CORONET, and a trap graph.
### GraphBuilder:
Build the adjacency matrix.  
In the adjacency matrix, a value of `Double.MAX_VALUE` acts as infinity which means that there is no edge between those nodes, `0` if it is an intersection of the same node, and any other value is the weight between the nodes.
First, the Scanner goes through the file collecting the names of the nodes and puts them into a sorted set to give each node a unique ID number, which will be their indices in the adjacency matrix.  
Then the Scanner goes through the file again and using the `indexOf()` method get the node indices and put the weight at that point.  
Then the program goes through the matrix again and “closes” the non-existent paths by setting them to infinity.

### PriorityQueue:
This is the Min-Priority Queue I use for my Dijkstra algorithm.  
It is pretty typical priority queue and there isn’t anything special to it.  
It is based on pseudocode from *Introduction to Algorithms: 3rd Edition*.  

### Dijkstra:
This class is also based on pseudocode found in the book *Introduction to Algorithms: 3rd Edition*.

### Suurballe and TSSP:
Implementations of the Suurballe and TSSP dijoint-pathfinding algorithms.


# Socket Programming:
## A simple Java socket program to transfer text between a client and a server.  Not going to lie, I had no idea what I was doing here.

