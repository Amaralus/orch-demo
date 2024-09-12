package apps.amaralus.madstub;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("mad-stub")
public class MadController {

    private final Random random = new Random();

    @GetMapping
    public ResponseEntity<Object> get() {
        randomDelay();
        return new ResponseEntity<>(randomCode());
    }

    private HttpStatusCode randomCode() {
        var errorStatuses = List.of(BAD_GATEWAY, BAD_REQUEST, INTERNAL_SERVER_ERROR, UNAUTHORIZED, FORBIDDEN);
        if (random.nextInt(100) < 95)
            return OK;
        else
            return errorStatuses.get(random.nextInt(errorStatuses.size()));

    }

    @SuppressWarnings("all")
    private void randomDelay() {
        try {
            Thread.sleep(random.nextLong(100, 4000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
