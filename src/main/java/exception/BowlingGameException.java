package exception;

public class BowlingGameException extends RuntimeException {
  public BowlingGameException() {
  }

  public BowlingGameException(String message) {
    super(message);
  }
}
