package org.application.view;

import org.application.util.InputValidator;
import org.application.util.Parser;

public class InputView {

    private static final String LOTTO_REQUEST = "구매할 로또 개수를 입력해주세요";

    public String getLottoCount(){
        System.out.println("구매할 로또 개수를 입력해주세요");
        return InputValidator.validateEmpty(System.console().readLine());
    }

}
