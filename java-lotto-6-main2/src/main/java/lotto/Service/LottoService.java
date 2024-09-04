package lotto.Service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.DTO.LottoResultDTO;
import lotto.DTO.LottoTicketsDTO;
import lotto.Model.BonusNumber;
import lotto.Model.LottoTickets;
import lotto.Model.Rank;
import lotto.Model.WinningNumbers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoService {

    private final LottoStore lottoStore;
    private final LottoResults lottoResults;

    public LottoService() {
        this.lottoStore = new LottoStore();
        this.lottoResults = new LottoResults();
    }

    public List<LottoTicketsDTO> purchaseLottoTickets(int amount) {
        int ticketCount = lottoStore.calculateLottoCount(amount);
        LottoTickets tickets = lottoStore.genereateLottoTickets(ticketCount);
        return tickets.getLottoTickets().stream()
                .map(lotto -> new LottoTicketsDTO(lotto.getNumbers()))
                .collect(Collectors.toList());
    }

    public LottoResultDTO calculateResult(LottoTickets tickets, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        Map<Rank, Integer> resultMap = lottoResults.calculateResult(tickets, winningNumbers, bonusNumber);
        long totalEarnings = lottoResults.calculateTotalEarnings(resultMap);
        double profitRate = getProfitRate(tickets, (double) totalEarnings);
        Map<String, Integer> rankMessages = resultsMessages(resultMap);
        return new LottoResultDTO(rankMessages, profitRate);
    }

    private static Map<String, Integer> resultsMessages(Map<Rank, Integer> resultMap) {
        Map<String, Integer> rankMessages = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            if (rank != Rank.NONE) {
                rankMessages.put(rank.getMessage(), resultMap.getOrDefault(rank, 0));
            }
        }
        return rankMessages;
    }

    private double getProfitRate(LottoTickets tickets, double totalEarnings) {
        return (totalEarnings / (tickets.size() * 1000)) * 100;
    }

}
