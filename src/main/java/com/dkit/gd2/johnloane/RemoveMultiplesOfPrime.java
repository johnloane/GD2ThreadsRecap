package com.dkit.gd2.johnloane;

public class RemoveMultiplesOfPrime implements Runnable
{
    int currentNumber;
    boolean[] primes100 = new boolean[100];

    public RemoveMultiplesOfPrime(int currentNumber, boolean[] primes100)
    {
        this.currentNumber = currentNumber;
        this.primes100 = primes100;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName());
        if(isPrime(currentNumber+1))
        {
            setAllMultiplesToFalse(currentNumber+1);
        }
    }

    private void setAllMultiplesToFalse(int currentNumber)
    {
        //e.g currentNumber = 2
        //want to remove 2*2, 3*2, 4*2, ..... 50*2 stop when bigger than 100
        for(int i=2; (currentNumber*i) <=100; i++)
        {
            if(primes100[(currentNumber*i)-1] != false)
            {
                primes100[(currentNumber*i)-1] = false;
            }
        }
    }

    private boolean isPrime(int testForPrime)
    {
        //Enough to check 2 up to sqrt(number) to test for prime
        //https://en.wikipedia.org/wiki/Primality_test#:~:text=The%20simplest%20primality%20test%20is,Otherwise%2C%20it%20is%20prime.
        for(int i=2; i < Math.sqrt(testForPrime); ++i)
        {
            if(testForPrime % i == 0)
            {
                return false;
            }
        }
        return true;
    }
}
