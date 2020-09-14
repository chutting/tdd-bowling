package exception;

public class FrameIllegalException extends RuntimeException {
  public FrameIllegalException() {
  }

  public FrameIllegalException(String message) {
    super(message);
  }
}
