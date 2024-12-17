package org.application.domain;


import java.util.List;
import org.application.domain.model.Lotto;
import org.application.domain.model.LottoNumber;

public class WinningLotto {

    private final Lotto numbers;
    private final LottoNumber bonus;

    public WinningLotto(Lotto numbers, LottoNumber bonus) {
        this.numbers = numbers;
        this.bonus = bonus;
    }

    public List<LottoNumber> getNumbers() {
        return numbers.getNumbers();
    }

    public LottoNumber getBonus() {
        return bonus;
    }
}

