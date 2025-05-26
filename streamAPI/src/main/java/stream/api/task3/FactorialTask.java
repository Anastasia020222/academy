package stream.api.task3;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {

    private long n;
    private long start;
    private long end;

    public FactorialTask(long n) {
        this.n = n;
        this.start = 1;
        this.end = n;
    }

    public FactorialTask(long start, long end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        if (end - start <= 1) {
            long result = 1;
            for (long i = start; i <= end; i++) {
                result *= i;
            }
            return result;
        }

        long division = (start + end)/2;
        FactorialTask factorialRight = new FactorialTask(start, division);
        FactorialTask factorialLeft = new FactorialTask(division+1, end);

        factorialLeft.fork();
        long c = factorialRight.compute();
        long x = factorialLeft.join();

        return c*x;
    }
}
