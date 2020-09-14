import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingFrame {
  private List<Integer> hitNumberList = Arrays.asList(0, 0);
  private int frameIndex;

  public BowlingFrame(int frameIndex, List<Integer> hitNumber) {
    this.hitNumberList = hitNumber;
    this.frameIndex = frameIndex;
  }

  public int getFirstHitNum() {
    return hitNumberList.get(0);
  }

  public Integer getHitNumberSum() {
    return hitNumberList.get(0) + hitNumberList.get(1);
  }

  public boolean isSpareFrame() {
    return hitNumberList.get(0) < 10 && hitNumberList.stream().collect(Collectors.summingInt(Integer::intValue)) == 10;
  }

  public boolean isStrikeFrame() {
    return hitNumberList.get(0) == 10;
  }
}
