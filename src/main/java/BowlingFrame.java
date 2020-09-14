import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BowlingFrame {
  private List<Integer> hitNumber = Arrays.asList(0, 0);
  private int frameIndex;

  public BowlingFrame(int frameIndex, List<Integer> hitNumber) {
    this.hitNumber = hitNumber;
    this.frameIndex = frameIndex;
  }

  public void setHitNumber(List<Integer> hitNumber) {
    this.hitNumber = hitNumber;
  }

  public Integer getHitNumber() {
    return hitNumber.get(0) + hitNumber.get(1);
  }
}
