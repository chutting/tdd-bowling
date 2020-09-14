import exception.FrameIllegalException;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingFrame {
  private List<Integer> hitNumberList;
  private boolean isExtraFrame = false;
  private final String THROW_COUNT_ERROR = "投球次数有误";
  private final String HIT_NUM_ERROR = "击中个数有误";
  private final String EXTRA_FRAME_THROW_NUM_ERROR = "额外的一轮投球次数有误";

  public BowlingFrame(List<Integer> hitNumbers) {
    if (isNormalFrameHitListLegal(hitNumbers)) {
      this.hitNumberList = hitNumbers;
    }
  }

  public BowlingFrame(List<Integer> hitNumberList, boolean isExtraFrame) {
    this.isExtraFrame = isExtraFrame;
    if (isExtraFrame && isExtraFrameLegal(hitNumberList)) {
    }
    this.hitNumberList = hitNumberList;
  }

  public boolean isExtraFrame() {
    return isExtraFrame;
  }

  public int getFirstHitNum() {
    return hitNumberList.get(0);
  }

  public Integer getHitNumberSum() {
    return hitNumberList.stream().collect(Collectors.summingInt(Integer::intValue));
  }

  public boolean isSpareFrame() {
    return hitNumberList.get(0) < 10 && hitNumberList.stream().collect(Collectors.summingInt(Integer::intValue)) == 10;
  }

  public boolean isStrikeFrame() {
    return hitNumberList.get(0) == 10;
  }

  private boolean isNormalFrameHitListLegal(List<Integer> hitNumbers) {
    if (hitNumbers.get(0) == 10 && hitNumbers.size() != 1) {
      throw new FrameIllegalException(THROW_COUNT_ERROR);
    }

    if (hitNumbers.get(0) != 10 && hitNumbers.size() != 2) {
      throw new FrameIllegalException(THROW_COUNT_ERROR);
    }

    return (isHitNumberRangeLegal(hitNumbers) && isHitNumberSumMoreThan10(hitNumbers));
  }

  private boolean isHitNumberSumMoreThan10(List<Integer> hitNumbers) {
    if (hitNumbers.stream().collect(Collectors.summingInt(Integer::intValue)) > 10) {
      throw new FrameIllegalException(HIT_NUM_ERROR);
    }
    return true;
  }

  private boolean isHitNumberRangeLegal(List<Integer> hitNumbers) {
    if (!hitNumbers.stream().allMatch(hitNumber -> hitNumber >= 0 && hitNumber <= 10)) {
      throw new FrameIllegalException(HIT_NUM_ERROR);
    }
    return true;
  }

  private boolean isExtraFrameLegal(List<Integer> hitNumbers) {
    if (hitNumbers.size() > 2 || hitNumbers.size() == 0) {
      throw new FrameIllegalException(EXTRA_FRAME_THROW_NUM_ERROR);
    }

    if (hitNumbers.size() == 2 && hitNumbers.get(0) != 10) {
      isHitNumberSumMoreThan10(hitNumbers);
    }

    return isHitNumberRangeLegal(hitNumbers);
  }
}
