import com.sberbank.domclick.model.Data;
import com.sberbank.domclick.system.Application;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ApplicationTest {
    private final Application application = new Application();

    @Test
    void handleDataTest() {
        assertDoesNotThrow(this::execute);
    }

    private void execute() {
        var executorService = Executors.newFixedThreadPool(8);
        IntStream.range(0, 8).forEach((i) -> executorService.submit(this::run));
        executorService.shutdown();
        await().atMost(15, TimeUnit.SECONDS).until(executorService::isTerminated);
    }

    private void run() {
        try {
            application.handleData(buildData());
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }


    private Data buildData() {
        return new Data(String.valueOf((int) (Math.random())), "json");
    }
}