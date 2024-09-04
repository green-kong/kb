package lotto.Controller;

import lotto.DTO.LottoResultDTO;
import lotto.DTO.LottoTicketsDTO;
import lotto.Model.*;
import lotto.Service.LottoService;
import lotto.View.View;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final LottoService lottoService;
    private final View lottoView;

    public LottoController() {
        this.lottoService = new LottoService();
        this.lottoView = new View();
    }

    public void run() {
        int amount = handlePurchaseAmount();
        List<LottoTicketsDTO> tickets = handleLottoTickets(amount);
        WinningNumbers winningNumbersInput = handleWinningNumbers();
        BonusNumber bonusNumberInput = handleBonusNumber(winningNumbersInput);
        handleLottoResults(tickets, winningNumbersInput, bonusNumberInput);
    }

    private void handleLottoResults(List<LottoTicketsDTO> tickets, WinningNumbers winningNumbersInput, BonusNumber bonusNumberInput) {
        List<Lotto> lottos = lottos(tickets);
        LottoTickets genericTickets = new LottoTickets((ArrayList<Lotto>) lottos);
        LottoResultDTO result = lottoService.calculateResult(genericTickets, winningNumbersInput, bonusNumberInput);
        lottoView.printLottoResult(result);
    }

    private List<Lotto> lottos(List<LottoTicketsDTO> tickets) {
        return tickets.stream()
                .map(dto -> new Lotto(dto.getNumbers()))
                .collect(Collectors.toList());
    }

    private BonusNumber handleBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            try {
                String bonusNumberInput = lottoView.inputBonusNumber();
                return new BonusNumber(bonusNumberInput, winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningNumbers handleWinningNumbers() {
        while (true) {
            try {
               String winningNumbersInput = lottoView.inputWinningNumbers();
                return new WinningNumbers(winningNumbersInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private List<LottoTicketsDTO> handleLottoTickets(int amount) {
        List<LottoTicketsDTO> tickets = lottoService.purchaseLottoTickets(amount);
        lottoView.printLottoTickets(tickets);
        return tickets;
    }

    private int handlePurchaseAmount() {
        while (true) {
            try {
                String input = lottoView.inputPurchaseAmount();
                LottoPurchase purchase = new LottoPurchase(input);
                return purchase.getPurchaseAmount();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
