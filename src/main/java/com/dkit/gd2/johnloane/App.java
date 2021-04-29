package com.dkit.gd2.johnloane;

/**
 * Write a program that will find all of the prime numbers between 1 and 100. A positive integer is prime if the positive integers that divide it evenly are 1 and the number itself, where the numbers are different. To use multithreading use the following algorithm:
 * 1. Set up an array of 100 boolean and initialise them all to true. Each element acts as a flag, indicating that the number is a prime number. Set an element to false when its proven to be not prime.
 * 2. Loop through the array. If the element is true, meaning that the number is a potential prime, launch a thread for the index of the element in that thread, set the elements to false for all multiples of the number. For example, the first thread that you start should eliminate all multiples of 2 (4, 6, 8, 10, 12, 14, 16, ,,,,,,,,,,) The next thread would get rid of multiples of 3 (6, 9, 12, 15...)
 * 3. When all the threads except the main thread have completed print the numbers that are prime. There should be 25 of these.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Find all primes from 1 to 100");
        boolean[] primes100 = new boolean[100];
        initalizePrimes(primes100);
        findPrimes(primes100);
        printPrimes(primes100);
    }

    private static void printPrimes(boolean[] primes100)
    {
        int count = 0;
        for(int i=0; i < 100; i++)
        {
            if(primes100[i] == true)
            {
                System.out.println((i+1) + " is prime");
                count++;
            }
        }
        System.out.println("There are " + count + " primes between 1 and 100");
    }

    private static void findPrimes(boolean[] primes100)
    {
        //1 is not a prime
        primes100[0] = false;
        for(int i=1; i< 100; ++i)
        {
            if(primes100[i] == true && i < 50)
            {
                //spawn a thread to remove all multiples of primes
                try
                {
                    RemoveMultiplesOfPrime removeMultiplesOfPrime = new RemoveMultiplesOfPrime(i, primes100);
                    Thread removePrimesThread = new Thread(removeMultiplesOfPrime);
                    removePrimesThread.start();
                    //.join gives us control of the threads - I want all
                    //multiples of 2 to be removed before I do multiples of 3
                    removePrimesThread.join();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void initalizePrimes(boolean[] primes100)
    {
        for(int i=0; i < 100; ++i)
        {
            primes100[i] = true;
        }
    }
}
