package lotto.Model;

import java.util.*;

public class LottoTickets {
    private final List<Lotto> lottoTickets;

    public LottoTickets(ArrayList<Lotto> lottoTickets) {
        validateLottoTickets(lottoTickets);
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    private void validateLottoTickets(List<Lotto> lottoTickets) {
        if (lottoTickets == null || lottoTickets.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 로또 티켓은 최소 한 장 이상이어야 합니다.");
        }

        Set<Lotto> uniqueTickets = new HashSet<>(lottoTickets);
        if (uniqueTickets.size() != lottoTickets.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 로또 티켓이 있습니다.");
        }

        for (Lotto lotto : lottoTickets) {
            if (lotto == null) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 로또 티켓입니다.");
            }
        }
    }

    public List<Lotto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int size() {
        return lottoTickets.size();
    }
}
