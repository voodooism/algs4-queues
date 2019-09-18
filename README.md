Queues
===================
#### Week 2 Programming assignment of Course "Algorithms, Part 1" from Princeton University in Coursera

**Double-ended queue**  
A double-ended queue or deque is a generalization of a stack and a queue that
supports adding and removing items from either the front or the back of the data structure.  
Each deque operation (including construction) takes constant worst-case time.

**Randomized queue**  
Resizable array based randomized queue. A randomized queue is similar to a stack or queue, 
except that the item removed is chosen uniformly at random from items in the data structure.  
Each randomized queue operation (besides creating an iterator) takes constant amortized time.

**Permutation**  
Takes an integer k as a command-line argument, reads a sequence of strings from standard input
using StdIn.readString() and prints exactly k of them, uniformly at random.
Print each item from the sequence at most once.  
It uses "Reservoir Sample" algorithm to use only one RandomizedQueue object of maximum size at most k.