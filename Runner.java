import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import java.time.Duration;
import java.time.Instant;


public class Runner {

    public static int sum = 0;

    public static void main(String[] args)
    {
        Instant startTime = Instant.now();
        try{

            ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

            List<MyThread> threads = new ArrayList<>();
            for(int i = 0; i < 100; i++)
            {
                threads.add(new MyThread());
            }
            List<Future<Integer>> list = executor.invokeAll(threads);
            for(Future<Integer> fut : list)
            {
                sum+=fut.get();
            }
            executor.shutdown();
            Instant endTime = Instant.now();
            Duration interval = Duration.between(startTime, endTime);
            System.out.println("Sum: "+sum);
            System.out.println("Time taken: "+interval.toMillis()+" milliseconds");

        }catch(InterruptedException|ExecutionException ex){
            System.out.println(ex.getMessage());
        }
    }

}