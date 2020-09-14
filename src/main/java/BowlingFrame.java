import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingFrame {
  private List<Integer> hitNumberList;
  private boolean isExtraFrame = false;

  public BowlingFrame(List<Integer> hitNumber) {
    this.hitNumberList = hitNumber;
  }

  public BowlingFrame(List<Integer> hitNumberList, boolean isExtraFrame) {
    this.hitNumberList = hitNumberList;
    this.isExtraFrame = isExtraFrame;
  }

  public boolean isExtraFrame() {
    return isExtraFrame;
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
