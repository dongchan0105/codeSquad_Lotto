package org.application.domain.model;

import java.util.Objects;

public class LottoNumber {

    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 45;

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (number < MINIMUM || number > MAXIMUM) {
            throw new IllegalArgumentException("로또 숫자는"+MINIMUM+"~에서 "+MAXIMUM+" 사이여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LottoNumber lottoNumber = (LottoNumber) o;
        return Objects.equals(number, lottoNumber.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public int getNumber() {
        return number;
    }
}

