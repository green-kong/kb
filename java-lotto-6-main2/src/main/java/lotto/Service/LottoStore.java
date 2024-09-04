package lotto.Service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Model.Lotto;
import lotto.Model.LottoTickets;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LottoStore {

    public int calculateLottoCount(int inputMoney) {
        return inputMoney / 1000;
    }

    public LottoTickets genereateLottoTickets(int count) {
        ArrayList<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lotto lotto = genereateLotto();
            lottoTickets.add(lotto);
        }
        return new LottoTickets(lottoTickets);
    }

    public Lotto genereateLotto() {
        List<Integer> lottoNumbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        lottoNumbers.sort(Comparator.naturalOrder());
        return new Lotto(lottoNumbers);
    }
}
