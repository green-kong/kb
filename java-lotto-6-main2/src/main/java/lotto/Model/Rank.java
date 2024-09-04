package lotto.Model;

import java.util.Arrays;

public enum Rank {
    THREE(3, false, 5000, "3개 일치 (5,000원)"),
    FOUR(4, false, 50000, "4개 일치 (50,000원)"),
    FIVE(5, false, 1500000, "5개 일치 (1,500,000원)"),
    FIVE_BONUS(5, true, 30000000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    SIX(6, false, 2000000000, "6개 일치 (2,000,000,000원)"),
    NONE(0, false, 0, "꽝");

    private int matchCount;
    private boolean matchBonus;
    private long prize;
    private String message;

    Rank(int matchCount, boolean matchBonus, int prize, String message) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
        this.message = message;
    }

    public static Rank matchLotto(int matchCount, boolean matchBonus) {
        if (matchCount == 5 && matchBonus) {
            return Rank.FIVE_BONUS;
        }

        if (matchCount == 5) {
            return Rank.FIVE;
        }
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findAny().
                orElse(Rank.NONE);
    }

    public String getMessage() {
        return message;
    }

    public long calculateTotalEarnings(int count) {
        return count * prize;
    }


}
