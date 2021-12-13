package vendingmachine.domain.machine;

import java.util.Objects;

import vendingmachine.domain.product.Price;

public class Balance {
	private static final int BALANCE_MINIMUM = 100;
	private static final int BALANCE_DIVISION_STANDARD = 10;
	private static final int BALANCE_ZERO = 0;
	private static final String ERROR_BALANCE_NOT_PURCHASE = "해당 잔액으로 구매할 수 없습니다.";
	private static final String ERROR_BALANCE_NOT_ONE_HUNDRED_OVER = "투입 금액은 100원 이상이여야 합니다.";
	private static final String ERROR_BALANCE_NOT_TEN_PERCENT_DIVISION = "투입 금액은 10의 단위로 나누어 떨어져야 합니다.";

	private final int balance;

	private Balance(int balance) {
		this.balance = balance;
	}

	public static Balance from(int balance) {
		isValidateBalanceMinimum(balance);
		isValidateBalance10PercentDivision(balance);
		return new Balance(balance);
	}

	private static void isValidateBalanceMinimum(int balance) {
		if (balance < BALANCE_MINIMUM) {
			throw new IllegalArgumentException(ERROR_BALANCE_NOT_ONE_HUNDRED_OVER);
		}
	}

	private static void isValidateBalance10PercentDivision(int balance) {
		if (balance % BALANCE_DIVISION_STANDARD != BALANCE_ZERO) {
			throw new IllegalArgumentException(ERROR_BALANCE_NOT_TEN_PERCENT_DIVISION);
		}
	}

	public boolean isValidateHasBalanceZero() {
		return this.balance == BALANCE_ZERO;
	}

	public Balance payment(Price price) {
		final int calculate = balance - price.getPrice();
		isValidatePaymentCalculateMinus(calculate);

		return new Balance(calculate);
	}

	private void isValidatePaymentCalculateMinus(int calculate) {
		if (calculate < BALANCE_ZERO) {
			throw new IllegalArgumentException(ERROR_BALANCE_NOT_PURCHASE);
		}
	}

	public int getBalance() {
		return balance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Balance))
			return false;
		Balance balance1 = (Balance)o;
		return balance == balance1.balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance);
	}
}
