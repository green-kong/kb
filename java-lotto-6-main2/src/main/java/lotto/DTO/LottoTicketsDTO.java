package lotto.DTO;

import java.util.List;

public class LottoTicketsDTO {
    private final List<Integer> numbers;

    public LottoTicketsDTO(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
