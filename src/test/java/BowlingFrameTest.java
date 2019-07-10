import main.java.BowlingFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BowlingFrameTest {

    @Test
    public void checkBowlingFrameIsStrike() {
        BowlingFrame frame = new BowlingFrame();
        Assertions.assertTrue(frame.isValidFrame("X"));
    }

    @Test
    public void checkBowlingFrameIsSpare() {
        BowlingFrame frame = new BowlingFrame();
        Assertions.assertTrue(frame.isValidFrame("9/"));
    }

    @Test
    public void checkBowlingFrameHaveMiss() {
        BowlingFrame frame = new BowlingFrame();
        Assertions.assertAll(
                () -> Assertions.assertTrue(frame.isValidFrame("1-")),
                () -> Assertions.assertTrue(frame.isValidFrame("-2")),
                () -> Assertions.assertTrue(frame.isValidFrame("--"))
        );
    }

    @Test
    public void checkBowlingFrameHits() {
        BowlingFrame frame = new BowlingFrame();
        Assertions.assertAll(
                () -> Assertions.assertTrue(frame.isValidFrame("19")),
                () -> Assertions.assertTrue(frame.isValidFrame("55"))
        );
    }

    @Test
    public void checkBowlingFrameInvalidStrike() {
        BowlingFrame frame = new BowlingFrame();
        Assertions.assertAll(
                () -> Assertions.assertFalse(frame.isValidFrame("1X")),
                () -> Assertions.assertFalse(frame.isValidFrame("0X")),
                () -> Assertions.assertFalse(frame.isValidFrame("-X")),
                () -> Assertions.assertFalse(frame.isValidFrame("X9")),
                () -> Assertions.assertFalse(frame.isValidFrame("XX"))
        );
    }

    @Test
    public void checkBowlingFrameInvalidSpare() {
        BowlingFrame frame = new BowlingFrame();
        Assertions.assertAll(
                () -> Assertions.assertFalse(frame.isValidFrame("/")),
                () -> Assertions.assertFalse(frame.isValidFrame("/-")),
                () -> Assertions.assertFalse(frame.isValidFrame("0/")),
                () -> Assertions.assertFalse(frame.isValidFrame("/1")),
                () -> Assertions.assertFalse(frame.isValidFrame("//"))
        );
    }
}
