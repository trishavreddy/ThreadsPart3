import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Runner {

    public static int sum = 0;

    public static void main(String[] args)
    {
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
            System.out.println("Sum: "+sum);

        }catch(InterruptedException|ExecutionException ex){
            System.out.println(ex.getMessage());
        }
    }

}