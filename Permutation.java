/* *****************************************************************************
 *  Name:    Just Me
 *  NetID:   voodooism
 *  Precept: P00
 *
 *  Description: takes an integer k as a command-line argument;
 *  reads a sequence of strings from standard input using StdIn.readString();
 *  and prints exactly k of them, uniformly at random.
 *  Print each item from the sequence at most once.
 *  It uses Reservoir Sample algorithm to use only one RandomizedQueue object
 *  of maximum size at most k.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int count = 0;
        int numberOfElements = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            count++;
            String in = StdIn.readString();
            if (count > numberOfElements && StdRandom.uniform(count) < numberOfElements) {
                queue.dequeue();
            }
            else if (count > numberOfElements) {
                continue;
            }
            queue.enqueue(in);
        }
        for (String a : queue) {
            StdOut.println(a);
        }
    }
}
