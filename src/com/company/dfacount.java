package com.company;

import java.util.*;

public class dfacount {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = Integer.parseInt(stdin.nextLine());
        // Loops a simulation for each DFA
        for(int i = 0; i < n; i++)
        {
            // Passes the scanner to the counter
            counter(stdin);
        }
    }

    public static void counter(Scanner stdin)
    {
        // Declares and Initializes variables
        int states = 0, alpha = 0, length = 0, numAccept = 0, temp = 0, tempState, i, j, k, a;
        long count = 0;
        ArrayList<ArrayList<Integer>> DFA = new ArrayList<>();
        ArrayList<Integer> list;

        // Reads in the first few lines from the input
        states = stdin.nextInt();
        alpha = stdin.nextInt();
        length = stdin.nextInt();
        numAccept = stdin.nextInt();

        // Initializes table with the information from above
        long[][] table = new long[states][length + 1];

        // Sets all values on the table to 0 for later
        for(i = 0; i < states; i++)
        {
            for(k = 0; k < length; k++)
            {
                table[i][k] = 0;
            }
        }

        // Sets all of the accept states to 1 in the table
        // This means that a length 0 string will be accepted if it starts on a start state
        for(i = 0; i < numAccept; i++)
        {
            table[stdin.nextInt()][0] = 1;
        }

        // Reads in the DFA and fills the DFA ArrayList with the information
        for(i = 0; i < states; i++)
        {
            list = new ArrayList<>();
            for(j = 0; j < alpha; j++)
            {
                list.add(stdin.nextInt());
            }
            DFA.add(list);
        }

        // Loops through the length form 1 to length
        for(k = 1; k <= length; k++)
        {
            // Loops through each state
            for(i = 0; i < states; i++)
            {
                // Loops through each entry in the alphabet
                for(a = 0; a < alpha; a++)
                {
                    // Adds the value from the previous lengths from each possible place state i could go
                    table[i][k] += table[DFA.get(i).get(a)][k-1];
                }
                // Mods this value by (Math.pow(10, 9) + 7) to ensure that there is no overflow issues
                table[i][k] %= (Math.pow(10, 9) + 7);
            }
        }

        // Prints the answer which will always be on the start state (0) and the final length
        System.out.println((long)(table[0][length] % (Math.pow(10, 9) + 7)));

    }
}
