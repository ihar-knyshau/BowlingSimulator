public enum FrameType {
    REGULAR(0), SPARE(1), STRIKE(2), FINAL(0);
    private int additionalHits;

    FrameType(int additionalHits) {
        this.additionalHits = additionalHits;
    }

    int getAdditionalHits() {
        return additionalHits;
    }
}
