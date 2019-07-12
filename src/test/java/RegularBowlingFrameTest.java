import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegularBowlingFrameTest {

    @Test
    public void checkBowlingFrameIsStrike() {
        BowlingFrame frame = new RegularBowlingFrame();
        Assertions.assertEquals(frame.calculateFrameType("X"), FrameType.STRIKE);
    }

    @Test
    public void checkBowlingFrameIsSpare() {
        BowlingFrame frame = new RegularBowlingFrame();
        Assertions.assertEquals(frame.calculateFrameType("5/"), FrameType.SPARE);
    }

    @Test
    public void checkBowlingFrameIsRegular() {
        BowlingFrame frame = new RegularBowlingFrame();
        Assertions.assertAll(
                () -> Assertions.assertEquals(frame.calculateFrameType("6-"), FrameType.REGULAR),
                () -> Assertions.assertEquals(frame.calculateFrameType("-6"), FrameType.REGULAR),
                () -> Assertions.assertEquals(frame.calculateFrameType("--"), FrameType.REGULAR),
                () -> Assertions.assertEquals(frame.calculateFrameType("45"), FrameType.REGULAR)
        );
    }

    @Test
    public void checkRegularBowlingFrameIsIncorrect() {
        BowlingFrame frame = new RegularBowlingFrame();
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> frame.calculateFrameType("10")),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> frame.calculateFrameType("09")),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> frame.calculateFrameType("7")),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> frame.calculateFrameType("123")),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> frame.calculateFrameType("---"))
        );
    }

    @Test
    public void checkBowlingFrameIsIncorrect() {
        BowlingFrame frame = new RegularBowlingFrame();
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> frame.calculateFrameType("10")),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> frame.calculateFrameType("09")),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> frame.calculateFrameType("7")),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> frame.calculateFrameType("123")),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> frame.calculateFrameType("---"))
        );
    }
}
