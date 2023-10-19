import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumberParallelSieve {
    public static void main(String[] args) {
        int n = 100;
        List<Integer> primes = findPrimesParallel(n);
        
        System.out.println("Prime numbers up to " + n + ":");
        primes.forEach(System.out::println);
    }

    public static List<Integer> findPrimesParallel(int n) {
        boolean[] isPrime = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        int processors = Runtime.getRuntime().availableProcessors();
        List<Thread> threads = new ArrayList<>();

        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }
}
