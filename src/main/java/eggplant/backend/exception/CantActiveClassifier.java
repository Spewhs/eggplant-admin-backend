package eggplant.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason="No classifier with this id")
public class CantActiveClassifier extends RuntimeException {
}
