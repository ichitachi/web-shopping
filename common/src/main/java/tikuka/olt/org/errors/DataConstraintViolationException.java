package tikuka.olt.org.errors;


import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import tikuka.olt.org.constant.Constants;

public class DataConstraintViolationException extends AbstractThrowableProblem {
    private static final long serialVersionUID = 1L;
    public DataConstraintViolationException(String message) {
        super(Constants.DEFAULT_TYPE, "Rate limit invalid", Status.INTERNAL_SERVER_ERROR, message);
    }
}
