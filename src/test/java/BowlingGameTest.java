import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

public class BowlingGameTest {
  @Test
  void given_no_strike_or_spare_then_no_bonus_points() {
    List<BowlingFrame> bowlingFrameList = Arrays.asList(new BowlingFrame(1, Arrays.asList(2, 4)),
        new BowlingFrame(1, Arrays.asList(3, 3)), new BowlingFrame(1, Arrays.asList(0, 5)),
        new BowlingFrame(1, Arrays.asList(4, 5)), new BowlingFrame(1, Arrays.asList(3, 3)),
        new BowlingFrame(1, Arrays.asList(3, 2)), new BowlingFrame(1, Arrays.asList(1, 5)),
        new BowlingFrame(1, Arrays.asList(3, 6)), new BowlingFrame(1, Arrays.asList(0, 0)),
        new BowlingFrame(1, Arrays.asList(0, 3)));
    BowlingGame bowlingGame = new BowlingGame(bowlingFrameList);
    assertEquals(55, bowlingGame.calculateSumPoint());
  }
}
