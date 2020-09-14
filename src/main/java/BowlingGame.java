import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGame {
  private List<BowlingFrame> hitBowlingFrames = new LinkedList<>();

  public BowlingGame(List<BowlingFrame> hitBowlingNumbers) {
    this.hitBowlingFrames = hitBowlingNumbers;
  }

  public int calculateSumPoint() {
    if (!hasBonusPointOrNot()) {
      Integer pointSum = hitBowlingFrames.stream().collect(Collectors.summingInt(BowlingFrame::getHitNumber));
      return pointSum;
    }

    return 0;
  }

  private boolean hasBonusPointOrNot() {
    return hitBowlingFrames.stream().anyMatch(bowlingFrame ->bowlingFrame.getHitNumber() == 10);
  }
}
