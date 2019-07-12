public interface BowlingFrame {
    byte MAX_FRAME_SCORE = 10;

    BowlingHit[] calculateFrameHits(String result, FrameType frameType);

    FrameType calculateFrameType(String result);

    default byte hitScore(char a) {
        if (a == '-')
            return 0;
        if (a == 'X')
            return 10;
        return Byte.parseByte(String.valueOf(a));
    }

    BowlingHit[] getHits();

    int getFrameScore();

    FrameType getFrameType();
}
