package org.application.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.application.domain.Prize;
import org.application.domain.PrizeResult;
import org.application.domain.WinningLotto;
import org.application.domain.model.Lotto;
import org.application.domain.model.LottoNumber;
import org.application.util.LottoMaker;

public class LottoService {

    public RandomLottos createRandomLottos(int count) {
        List<Lotto> randomLottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            randomLottos.add(new Lotto(LottoMaker.make()));
        }

        return new RandomLottos(randomLottos);
    }

    public Lotto createWinningLottoNumbers(String numbersInput) {

    }

    public WinningLotto createWinningLotto(Lotto winningNumbers, LottoNumber bonus) {
        validateDuplication(winningNumbers, bonus);

        return new WinningLotto(winningNumbers, bonus);
    }

    private void validateDuplication(Lotto winningNumbers, LottoNumber bonus) {
        if (winningNumbers.getNumbers().contains(bonus)) {
            throw new IllegalArgumentException("보너스 숫자는 중복될 수 없습니다");
        }
    }

    public PrizeResult calculateResult(RandomLottos randomLottos, WinningLotto winningLotto) {
        PrizeResult prizeResult = PrizeResult.create();

        for (Lotto radnomLotto : randomLottos.lottos()) {
            List<LottoNumber> mergedLotto = merge(winningLotto, radnomLotto);

            int matchingCount = countMatching(mergedLotto);
            boolean hasNumber = radnomLotto.hasNumber(winningLotto.getBonus());

            Prize foundPrize = Prize.findPrize(matchingCount, hasNumber);
            prizeResult.increaseCountOf(foundPrize);
        }
        prizeResult.removeNoPrize();

        return prizeResult;
    }

    private List<LottoNumber> merge(WinningLotto winningLotto, Lotto randomLotto) {
        return Stream.of(winningLotto.getNumbers(), randomLotto.getNumbers())
                .flatMap(lottoNumber -> lottoNumber.stream())
                .toList();
    }

    private int countMatching(List<LottoNumber> lottNumbers) {
        Set<LottoNumber> removalDuplicatedNumber = new HashSet<>(lottNumbers);

        return lottNumbers.size() - removalDuplicatedNumber.size();
    }

    public record RandomLottos(List<Lotto> lottos) {}
}