package tp5.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ClientTransfer implements ServletContextListener
{
    private static final int CLIENT_THREAD_COUNT = 10;

    private final Executor executor = Executors.newFixedThreadPool(CLIENT_THREAD_COUNT);


    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        int count = 0;

        while(count < CLIENT_THREAD_COUNT)
        {
            executor.execute(() -> {
                System.out.println("Thread Name-ID : " + Thread.currentThread().getName() + "-" + Thread.currentThread().getId());
            });

            count++;
        }
    }
}
