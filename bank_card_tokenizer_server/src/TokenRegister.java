import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class TokenRegister extends DataBase<Hashtable<String, String>>  {
    public TokenRegister() {
        init("tokens.xml");
    }

    private static boolean isFirstDigitGood(char[] bankCardChars) {
        return bankCardChars[0] == '3' ||
                bankCardChars[0] == '4' ||
                bankCardChars[0] == '5' ||
                bankCardChars[0] == '6';
    }

    private static boolean isLuhnAlgorithmWork(char[] bankCardChars) {
        int[] weight = {2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1};

        int productSum = 0;

        for (int i = 0; i < 16; i++) {
            int product = (int)(bankCardChars[i] - '0') * weight[i];
            productSum += product;
            if (product >= 10) {
                ++productSum;
            }
        }

        return productSum % 10 == 0;
    }

    private synchronized String generateToken(char[] bankCardChars) {
        char[] tokenChars = bankCardChars.clone();

        Random rn = new Random();

        while ( isFirstDigitGood(tokenChars) ) {

            int num = rn.nextInt(0, 10);
            tokenChars[0] = (char)('0' + rn.nextInt(0, 10));
        }

        for (int i = 1; i < 12; i++) {
            char old = tokenChars[i];
            while ( tokenChars[i] == old ) {
                tokenChars[i] = (char)('0' + rn.nextInt(0, 10));
            }
        }

        if (isLuhnAlgorithmWork(tokenChars)) {
            return  generateToken(bankCardChars);
        } else {
            String token = new String(tokenChars);

            if (data.contains(token)) {
                return generateToken(bankCardChars);
            } else {
                return token;
            }
        }
    }

    public synchronized String add(String bankCard, User user) {
        char[] bankCardChars = bankCard.toCharArray();

        if (bankCardChars.length != 16) {
            return "";
        }

        if (!isFirstDigitGood(bankCardChars)) {
            return "";
        }

        if (!isLuhnAlgorithmWork(bankCardChars)) {
            return "";
        }

        String token = generateToken(bankCardChars);

        data.put(token, bankCard);
        user.addToken(token);

        return token;
    }

    public synchronized String get(String token, User user) {
        if (!user.canUseToken(token)) {
            return "";
        }

        return data.get(token);
    }
}
