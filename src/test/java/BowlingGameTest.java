import exception.FrameIllegalException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

public class BowlingGameTest {
  @Test
  void given_no_strike_or_spare_then_no_bonus_points() {
    List<BowlingFrame> bowlingFrameList = Arrays.asList(new BowlingFrame(Arrays.asList(2, 4)),
        new BowlingFrame(Arrays.asList(3, 3)), new BowlingFrame(Arrays.asList(0, 5)),
        new BowlingFrame(Arrays.asList(4, 5)), new BowlingFrame(Arrays.asList(3, 3)),
        new BowlingFrame(Arrays.asList(3, 2)), new BowlingFrame(Arrays.asList(1, 5)),
        new BowlingFrame(Arrays.asList(3, 6)), new BowlingFrame(Arrays.asList(0, 0)),
        new BowlingFrame(Arrays.asList(0, 3)));
    BowlingGame bowlingGame = new BowlingGame(bowlingFrameList);
    assertEquals(55, bowlingGame.calculateSumPoint());
  }

  @Test
  void should_add_bonus_point_when_strike_exist() {
    List<BowlingFrame> bowlingFrameList = Arrays.asList(new BowlingFrame(Arrays.asList(10)),
        new BowlingFrame(Arrays.asList(10)), new BowlingFrame(Arrays.asList(3, 5)),
        new BowlingFrame(Arrays.asList(4, 5)), new BowlingFrame(Arrays.asList(3, 3)),
        new BowlingFrame(Arrays.asList(3, 2)), new BowlingFrame(Arrays.asList(1, 5)),
        new BowlingFrame(Arrays.asList(3, 6)), new BowlingFrame(Arrays.asList(0, 0)),
        new BowlingFrame(Arrays.asList(0, 3)));
    BowlingGame bowlingGame = new BowlingGame(bowlingFrameList);
    assertEquals(87, bowlingGame.calculateSumPoint());
  }

  @Test
  void should_add_bonus_point_when_spare_exist() {
    List<BowlingFrame> bowlingFrameList = Arrays.asList(new BowlingFrame(Arrays.asList(10)),
        new BowlingFrame(Arrays.asList(10)), new BowlingFrame(Arrays.asList(3, 5)),
        new BowlingFrame(Arrays.asList(4, 5)), new BowlingFrame(Arrays.asList(3, 3)),
        new BowlingFrame(Arrays.asList(3, 7)), new BowlingFrame(Arrays.asList(1, 5)),
        new BowlingFrame(Arrays.asList(3, 6)), new BowlingFrame(Arrays.asList(0, 0)),
        new BowlingFrame(Arrays.asList(0, 3)));
    BowlingGame bowlingGame = new BowlingGame(bowlingFrameList);
    assertEquals(93, bowlingGame.calculateSumPoint());
  }

  @Test
  void should_add_bonus_point_when_10th_frame_is_strike() {
    List<BowlingFrame> bowlingFrameList = Arrays.asList(new BowlingFrame(Arrays.asList(10)),
        new BowlingFrame(Arrays.asList(10)), new BowlingFrame(Arrays.asList(3, 5)),
        new BowlingFrame(Arrays.asList(4, 5)), new BowlingFrame(Arrays.asList(3, 3)),
        new BowlingFrame(Arrays.asList(3, 7)), new BowlingFrame(Arrays.asList(1, 5)),
        new BowlingFrame(Arrays.asList(3, 6)), new BowlingFrame(Arrays.asList(0, 0)),
        new BowlingFrame(Arrays.asList(10)));
    BowlingGame bowlingGame = new BowlingGame(bowlingFrameList, new BowlingFrame(Arrays.asList(4, 3), true));
    assertEquals(107, bowlingGame.calculateSumPoint());
  }

  @Test
  void should_add_bonus_point_when_10th_frame_is_spare() {
    List<BowlingFrame> bowlingFrameList = Arrays.asList(new BowlingFrame(Arrays.asList(10)),
        new BowlingFrame(Arrays.asList(10)), new BowlingFrame(Arrays.asList(3, 5)),
        new BowlingFrame(Arrays.asList(4, 5)), new BowlingFrame(Arrays.asList(3, 3)),
        new BowlingFrame(Arrays.asList(3, 7)), new BowlingFrame(Arrays.asList(1, 5)),
        new BowlingFrame(Arrays.asList(3, 6)), new BowlingFrame(Arrays.asList(0, 0)),
        new BowlingFrame(Arrays.asList(5, 5)));
    BowlingGame bowlingGame = new BowlingGame(bowlingFrameList, new BowlingFrame(Arrays.asList(4), true));
    assertEquals(104, bowlingGame.calculateSumPoint());
  }

  @Test
  void should_throw_exception_when_throw_number_more_than_2() {
    assertThrows(FrameIllegalException.class, () -> {
      new BowlingFrame(Arrays.asList(3, 5, 7));
    });
  }

  @Test
  void should_throw_exception_when_strike_has_two_throws() {
    assertThrows(FrameIllegalException.class, () -> {
      new BowlingFrame(Arrays.asList(10, 0));
    });
  }

  @Test
  void should_throw_exception_when_hit_num_more_than_10() {
    assertThrows(FrameIllegalException.class, () -> {
      new BowlingFrame(Arrays.asList(3, 9));
    });
  }

  @Test
  void should_throw_exception_when_hit_num_out_of_range() {
    assertThrows(FrameIllegalException.class, () -> {
      new BowlingFrame(Arrays.asList(3, -1));
    });
  }

  @Test
  void should_throw_exception_when_extra_frame_is_illegal() {
    assertThrows(FrameIllegalException.class, () -> {
      new BowlingFrame(Arrays.asList(6, 7), true);
    });
  }

  @Test
  void should_throw_exception_when_extra_frame_throw_num_illegal() {
    assertThrows(FrameIllegalException.class, () -> {
      new BowlingFrame(Arrays.asList(6, 7), true);
    });
  }

  @Test
  void should_throw_exception_when_extra_frame_throw_num_incorrect() {
    List<BowlingFrame> bowlingFrameList = Arrays.asList(new BowlingFrame(Arrays.asList(10)),
        new BowlingFrame(Arrays.asList(10)), new BowlingFrame(Arrays.asList(3, 5)),
        new BowlingFrame(Arrays.asList(4, 5)), new BowlingFrame(Arrays.asList(3, 3)),
        new BowlingFrame(Arrays.asList(3, 7)), new BowlingFrame(Arrays.asList(1, 5)),
        new BowlingFrame(Arrays.asList(3, 6)), new BowlingFrame(Arrays.asList(0, 0)),
        new BowlingFrame(Arrays.asList(10)));
    assertThrows(FrameIllegalException.class, () -> {
      new BowlingGame(bowlingFrameList, new BowlingFrame(Arrays.asList(4), true));
    });
  }


}
