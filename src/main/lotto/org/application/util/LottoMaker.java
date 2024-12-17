package org.application.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.application.domain.model.LottoNumber;

public class LottoMaker {

    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 45;
    private static final int COUNT = 6;
    private static int[] lotto = new int[6];

    public static List<LottoNumber> make(){
        for(int i=0; i<lotto.length; i++){
            int num = (int) (Math.random() * 45) + 1;    // 1~46까지의 임의의 수를 받는다.
            lotto[i] = num;
            for (int j = 0; j < i; j++) {    // 중복된 번호가 있으면 이전 포문으로 돌아가 다시 시행한다.
                if (lotto[i] == lotto[j]) {
                    i--;
                    break;
                }
            }
        }
        return Arrays.stream(lotto).mapToObj(num->new LottoNumber(num)).toList();
    }

}
