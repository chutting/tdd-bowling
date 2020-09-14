import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGame {
  private List<BowlingFrame> bowlingFrames = new LinkedList<>();

  public BowlingGame(List<BowlingFrame> hitBowlingNumbers) {
    this.bowlingFrames = hitBowlingNumbers;
  }

  public int calculateSumPoint() {
    int sum = 0;
//    if (!hasBonusPointOrNot()) {
//      sum = bowlingFrames.stream().collect(Collectors.summingInt(BowlingFrame::getHitNumberSum));
//    }

    for (int i = 0; i < bowlingFrames.size(); i++) {
      if (bowlingFrames.get(i).isStrikeFrame()) {
        sum += calculateStrikePoint(i);
      } else if (bowlingFrames.get(i).isSpareFrame()) {
        sum += 1;
      } else {
        sum += bowlingFrames.get(i).getHitNumberSum();
      }
    }

    return sum;
  }

  private int calculateStrikePoint(int index) {
    BowlingFrame nextFrame = bowlingFrames.get(index + 1);
    int sum = 10 + nextFrame.getHitNumberSum();
    if (nextFrame.isStrikeFrame()) {
      sum = sum + bowlingFrames.get(index + 2).getFirstHitNum();
    }
    return sum;
  }

  private boolean hasBonusPointOrNot() {
    return bowlingFrames.stream().anyMatch(bowlingFrame ->bowlingFrame.getHitNumberSum() == 10);
  }
}
