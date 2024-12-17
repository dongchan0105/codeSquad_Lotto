import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Lotto {


    public static void main(String[] args) {

        HashSet<Integer> set = new HashSet<>();
        Random random = new Random();
        Integer[] LottoResult = new Integer[7];

        // 중복 제거를 위한 set 사용
        while(set.size() < 45){
            int lottoNumber = random.nextInt(45) + 1;
            set.add(lottoNumber);
        }

        List<Integer> lottoMachine = new ArrayList<>(set);

        // 머신 끝부분에 충돌하면 해당 숫자를 반환하며 제거
        for(int i = 0; i < 7; i++){
            int number2 = lottoMachine.get(lottoMachine.size() - 1);

            LottoResult[i] = number2;
            lottoMachine.remove(number2);
        }

        for (Integer integer : LottoResult) {
            System.out.println(integer);
        }

    }







}
