package lotto.Model;

public class LottoPurchase {
    private final int purchaseAmount;

    public LottoPurchase(String input) {
        this.purchaseAmount = validate(input);
    }

    private int validate(String input) {
        try {
            int amount = Integer.parseInt(input);
            if (amount <= 0) {
                throw new IllegalArgumentException("[ERROR] 구입 금액은 0보다 큰 숫자여야 합니다.");
            }
            if (amount % 1000 != 0) {
                throw new IllegalArgumentException("[ERROR] 구입 금액은 1000 단위여야 합니다.");
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR] 숫자만 입력해 주세요.");
        }
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }
}
