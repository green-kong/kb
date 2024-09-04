package lotto.Model;

public class BonusNumber {
    private final int bonus;

    public BonusNumber(String bonus, WinningNumbers winningNumbers) {
        this.bonus = validateAndParse(bonus, winningNumbers);
    }

    private int validateAndParse(String bonusInput, WinningNumbers winningNumbers) {
        int bonus;
        if (bonusInput.contains(",")) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 하나의 숫자만 입력해야 합니다.");
        }

        try {
            bonus = Integer.parseInt(bonusInput);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR] 보너스 번호는 숫자만 입력 가능합니다.");
        }
        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45까지만 입력 가능합니다.");
        }
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨번호와 중복될 수 없습니다.");
        }
        return bonus;
    }

    public int getBonus() {
        return bonus;
    }
}
