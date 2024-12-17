package org.application.controller;

import java.util.Map.Entry;
import org.application.domain.Prize;
import org.application.domain.PrizeResult;
import org.application.domain.WinningLotto;
import org.application.domain.model.Lotto;
import org.application.domain.model.LottoNumber;
import org.application.service.LottoService;
import org.application.service.LottoService.RandomLottos;
import org.application.util.Parser;
import org.application.view.ErrorView;
import org.application.view.InputView;
import org.application.view.OutputView;

public class LottoController {

    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;
    private final ErrorView errorView;

    public LottoController(LottoService lottoService, InputView inputView, OutputView outputView,
                           ErrorView errorView) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.errorView = errorView;
    }

    public void playLotto() {
        int ticket = requestLottoCount();

        outputView.printTicketGuide(ticket);
        RandomLottos randomLottos = lottoService.createRandomLottos(ticket);
        noticeRandomLottos(randomLottos);

        Lotto winningNumbers = requestWinningNumber();
        LottoNumber bonus = requestBonus();

        WinningLotto winningLotto = createWinningLotto(winningNumbers, bonus);
        calculatePrizeResult(randomLottos, winningLotto);
    }

    private int requestLottoCount() {
        try {
            String priceInput = inputView.getLottoCount();

            return Parser.parseToInt(priceInput);
        } catch (IllegalArgumentException e) {
            errorView.printErrorMessage(e.getMessage());

            return requestLottoCount();
        }
    }


    private void noticeRandomLottos(RandomLottos randomLottos) {
        for (Lotto lotto : randomLottos.lottos()) {
            outputView.printNumbers(lotto.getNumberValues());
        }
    }

    private Lotto requestWinningNumber() {
        try {
            String winningNumberInput;

            return lottoService.createWinningLottoNumbers(winningNumberInput);
        } catch (IllegalArgumentException e) {
            errorView.printErrorMessage(e.getMessage());

            return requestWinningNumber();
        }
    }

    private LottoNumber requestBonus() {
        try {
            int bonusInput;

            return new LottoNumber(Parser.parseToInt(bonusInput));
        } catch (IllegalArgumentException e) {
            errorView.printErrorMessage(e.getMessage());

            return requestBonus();
        }
    }

    private WinningLotto createWinningLotto(Lotto winningNumbers, LottoNumber bonus) {
        try {
            return lottoService.createWinningLotto(winningNumbers, bonus);
        } catch (IllegalArgumentException e) {
            errorView.printErrorMessage(e.getMessage());
            LottoNumber newBonus = requestBonus();

            return createWinningLotto(winningNumbers, newBonus);
        }
    }

    private void calculatePrizeResult(RandomLottos randomLottos, WinningLotto winningLotto) {
        PrizeResult prizeResult = lottoService.calculateResult(randomLottos, winningLotto);

        outputView.printGuideMessage();
        for (Entry<Prize, Integer> prize : prizeResult.getEntrySet()) {
            outputView.printWinningResults(
                    prize.getKey().getMatchingCount(), prize.getKey().getMoney(),
                    prize.getValue(), prize.getKey().isMatchBonus());
        }

    }
}

