import java.util.LinkedList;
import java.util.List;

public class BowlingGame {
  private List<BowlingFrame> bowlingFrames;
  private BowlingFrame extraFrame;

  public BowlingGame(List<BowlingFrame> hitBowlingNumbers) {
    this.bowlingFrames = hitBowlingNumbers;
  }

  public BowlingGame(List<BowlingFrame> bowlingFrames, BowlingFrame extraFrame) {
    this.bowlingFrames = bowlingFrames;
    this.extraFrame = extraFrame;
  }

  public int calculateSumPoint() {
    int sum = 0;
    for (int i = 0; i < bowlingFrames.size(); i++) {
      if (bowlingFrames.get(i).isStrikeFrame()) {
        BowlingFrame nextFrame = (i == 9 ? extraFrame : bowlingFrames.get(i + 1));
        sum += calculateStrikePoint(i, nextFrame);
      } else if (bowlingFrames.get(i).isSpareFrame()) {
        sum += calculateSparePoint(i);
      } else {
        sum += bowlingFrames.get(i).getHitNumberSum();
      }
    }
    return sum;
  }

  private int calculateStrikePoint(int index, BowlingFrame nextFrame) {
    int sum = 10 + nextFrame.getHitNumberSum();
    if (nextFrame.isStrikeFrame() && !nextFrame.isExtraFrame()) {
      sum = sum + bowlingFrames.get(index + 2).getFirstHitNum();
    }
    return sum;
  }

  private int calculateSparePoint(int index) {
    BowlingFrame nextFrame = bowlingFrames.get(index + 1);
    int sum = 10 + nextFrame.getFirstHitNum();
    return sum;
  }
}
