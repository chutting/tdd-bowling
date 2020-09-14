import exception.FrameIllegalException;

import java.util.LinkedList;
import java.util.List;

public class BowlingGame {
  private List<BowlingFrame> bowlingFrames;
  private BowlingFrame extraFrame;
  private final String EXTRA_FRAME_SECOND_PARA_MUST_BE_TRUE = "额外轮的第二个参数必须为true";
  private final String EXTRA_FRAME_ThROW_NUM_ERROR = "额外轮的投球次数错误";


  public BowlingGame(List<BowlingFrame> hitBowlingNumbers) {
    this.bowlingFrames = hitBowlingNumbers;
  }

  public BowlingGame(List<BowlingFrame> bowlingFrames, BowlingFrame extraFrame) {
    if (isExtraFrameLegal(bowlingFrames, extraFrame)) {
      this.bowlingFrames = bowlingFrames;
      this.extraFrame = extraFrame;
    }
  }

  public int calculateSumPoint() {
    int sum = 0;
    for (int i = 0; i < bowlingFrames.size(); i++) {
      if (bowlingFrames.get(i).isStrikeFrame()) {
        BowlingFrame nextFrame = (i == 9 ? extraFrame : bowlingFrames.get(i + 1));
        sum += calculateStrikePoint(i, nextFrame);
      } else if (bowlingFrames.get(i).isSpareFrame()) {
        BowlingFrame nextFrame = (i == 9 ? extraFrame : bowlingFrames.get(i + 1));
        sum += calculateSparePoint(nextFrame);
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

  private int calculateSparePoint(BowlingFrame nextFrame) {
    int sum = 10 + nextFrame.getFirstHitNum();
    return sum;
  }

  private boolean isExtraFrameLegal(List<BowlingFrame> bowlingFrames, BowlingFrame extraFrame) {
    if (!extraFrame.isExtraFrame()) {
      throw new FrameIllegalException(EXTRA_FRAME_SECOND_PARA_MUST_BE_TRUE);
    }

    if (bowlingFrames.get(bowlingFrames.size() - 1).isStrikeFrame() && extraFrame.getHitNumberList().size() != 2) {
      throw new FrameIllegalException(EXTRA_FRAME_ThROW_NUM_ERROR);
    }

    if (bowlingFrames.get(-1).isSpareFrame() && extraFrame.getHitNumberList().size() != 1) {
      throw new FrameIllegalException(EXTRA_FRAME_ThROW_NUM_ERROR);
    }

    return true;
  }
}
