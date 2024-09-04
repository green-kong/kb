package lotto.DTO;

import java.util.Map;

public class LottoResultDTO {
    private final Map<String, Integer> rankMessages;
    private final double profitRate;

    public LottoResultDTO(Map<String, Integer> rankMessages, double profitRate) {
        this.rankMessages = rankMessages;
        this.profitRate = profitRate;
    }

    public Map<String, Integer> getRankMessages() {
        return rankMessages;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
