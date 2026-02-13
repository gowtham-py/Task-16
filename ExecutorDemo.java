import java.util.*;
import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            futures.add(executor.submit(new OrderTask(i)));
        }

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (ExecutionException e) {
                System.out.println("Task Error: " + e.getCause().getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
