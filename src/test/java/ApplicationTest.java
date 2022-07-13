import com.sberbank.domclick.model.Data;
import com.sberbank.domclick.system.Application;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ApplicationTest {
    private final CyclicBarrier cyclicBarrier = new CyclicBarrier(8 + 1);
    private final Application application = new Application();

    @Test
    void handleDataTest() {
        assertDoesNotThrow(this::execute);
    }

    private void execute() throws InterruptedException, BrokenBarrierException {
        var executorService = Executors.newFixedThreadPool(8);
        IntStream.range(0, 8).forEach((i) -> executorService.submit(this::run));
        cyclicBarrier.await();
        cyclicBarrier.await();
    }

    private void run() {
        try {
            cyclicBarrier.await();
            application.handleData(buildData());
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException();
        }
    }


    private Data buildData() {
        return new Data(String.valueOf((int) (Math.random() * 2)), "json");
    }
}