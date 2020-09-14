import exception.BowlingGameException;

import java.util.Arrays;
import java.util.List;

public class BowlingGame {
  private List<BowlingFrame> bowlingFrames;
  private BowlingFrame extraFrame;
  private final String EXTRA_FRAME_SECOND_PARA_MUST_BE_TRUE = "额外轮的第二个参数必须为true";
  private final String EXTRA_FRAME_ThROW_NUM_ERROR = "额外轮的投球次数错误";
  private final String FRAME_NUMBER_IS_NOT_TEN = "投球轮数错误";

  public BowlingGame(List<BowlingFrame> bowlingFrames) {
    if (isFrameNumIsTen(bowlingFrames) && isExtraFrameExists(bowlingFrames)) {
      this.bowlingFrames = bowlingFrames;
    }
  }

  public BowlingGame(List<BowlingFrame> bowlingFrames, BowlingFrame extraFrame) {
    if (isFrameNumIsTen(bowlingFrames) && isExtraFrameLegal(bowlingFrames, extraFrame)) {
      this.bowlingFrames = bowlingFrames;
      this.extraFrame = extraFrame;
    }
  }

  public int calculateSumPoint() {
    int sum = 0;
    for (int i = 0; i < bowlingFrames.size(); i++) {
      if (bowlingFrames.get(i).isStrikeFrame()) {
        if (i == 9) {
          sum += calculateStrikePoint(i, extraFrame);
        }else {
          sum += calculateStrikePoint(i, bowlingFrames.get(i + 1));
        }

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
    if (index == 8 && nextFrame.isStrikeFrame()) {
      sum += extraFrame.getFirstHitNum();
    } else if (nextFrame.isStrikeFrame() && !nextFrame.isExtraFrame()) {
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
      throw new BowlingGameException(EXTRA_FRAME_SECOND_PARA_MUST_BE_TRUE);
    }

    if (bowlingFrames.get(bowlingFrames.size() - 1).isStrikeFrame() && extraFrame.getHitNumberList().size() != 2) {
      throw new BowlingGameException(EXTRA_FRAME_ThROW_NUM_ERROR);
    }

    if (bowlingFrames.get(bowlingFrames.size() - 1).isSpareFrame() && extraFrame.getHitNumberList().size() != 1) {
      throw new BowlingGameException(EXTRA_FRAME_ThROW_NUM_ERROR);
    }

    return true;
  }

  private boolean isFrameNumIsTen(List<BowlingFrame> bowlingFrames) {
    if (bowlingFrames.size() != 10) {
      throw new BowlingGameException(FRAME_NUMBER_IS_NOT_TEN);
    }
    return true;
  }

  private boolean isExtraFrameExists(List<BowlingFrame> bowlingFrames) {
    if (bowlingFrames.get(bowlingFrames.size() - 2).isStrikeFrame()
        && !bowlingFrames.get(bowlingFrames.size() - 1).isStrikeFrame()
        && extraFrame == null) {
      throw new BowlingGameException(EXTRA_FRAME_ThROW_NUM_ERROR);
    }
    if (bowlingFrames.get(bowlingFrames.size() - 1).isStrikeFrame() || bowlingFrames.get(bowlingFrames.size() - 1).isSpareFrame()) {
      if (extraFrame == null) {
        throw new BowlingGameException(EXTRA_FRAME_ThROW_NUM_ERROR);
      }
    }
    return true;
  }
}
