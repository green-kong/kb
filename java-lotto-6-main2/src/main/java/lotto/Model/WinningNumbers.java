package lotto.Model;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {

    private static final String VALIDATE_COUNT_SIX_WINNING_NUMBER = "[ERROR] 당첨 번호는 6개를 입력해야합니다.";
    private static final String VALIDATE_NOT_PRESS_DISTINCT_NUMBER = "[ERROR] 당첨 번호에 중복된 숫자는 입력 불가합니다";
    private static final String VALIDATE_WINNING_NUMBER_RANGE = "[ERROR] 당첨번호는 1부터 45 사이의 숫자여야 합니다";
    private static final String VALIDATE_ONLY_NUMERIC = "[ERROR] 당첨 번호는 숫자만 입력해야 합니다";
    private static final String VALIDATE_COMMA = "[ERROR] 당첨 번호는 쉼표(,)로 구분되어야 합니다.";
    private final List<Integer> winningNumbers;

    public WinningNumbers(String winningNumberInput) {
        validateComma(winningNumberInput);
        List<String> winningNumberStrings = List.of(winningNumberInput.split(","));
        this.winningNumbers = validateAndParse(winningNumberStrings);
        validate(winningNumbers);
    }

    private void validateComma(String winningNumberInput) {
        if (!winningNumberInput.contains(",")) {
            throw new IllegalArgumentException(VALIDATE_COMMA);
        }
    }

    private List<Integer> validateAndParse(List<String> winningNumberStrings) {
            return winningNumberStrings.stream()
                    .map(String::trim)
                    .map(this::parseToInteger)
                    .collect(Collectors.toList());
    }

    private Integer parseToInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(VALIDATE_ONLY_NUMERIC);
        }
    }

    private void validate(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(VALIDATE_COUNT_SIX_WINNING_NUMBER);
        }

        if (winningNumbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException(VALIDATE_NOT_PRESS_DISTINCT_NUMBER);
        }

        if (winningNumbers.stream().anyMatch(num -> num < 1 || num > 45)) {
            throw new IllegalArgumentException(VALIDATE_WINNING_NUMBER_RANGE);
        }
    }


    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public boolean contains(int number) {
        return winningNumbers.contains(number);
    }

}
