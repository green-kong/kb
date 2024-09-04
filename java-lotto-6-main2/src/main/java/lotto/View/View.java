package lotto.View;

import camp.nextstep.edu.missionutils.Console;
import lotto.DTO.LottoResultDTO;
import lotto.DTO.LottoTicketsDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class View {

    public String inputPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요.");
        return Console.readLine();
    }

    public String inputWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public String inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public void printLottoTickets(List<LottoTicketsDTO> lottoTickets) {
        System.out.println(lottoTickets.size() + "개를 구매했습니다.");
        lottoTickets.forEach(tickets -> System.out.println(tickets.getNumbers()));
    }

    public void printLottoResult(LottoResultDTO result) {
        System.out.println("당첨 통계");
        System.out.println("---");
        Map<String, Integer> rankMessages = result.getRankMessages();
        rankMessages.forEach((message, count) ->
                System.out.println(message + " - " + count + "개"));
        System.out.printf("총 수익률은 %.1f%%입니다.\n", result.getProfitRate());
    }
}
