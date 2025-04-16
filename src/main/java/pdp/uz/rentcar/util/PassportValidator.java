package pdp.uz.rentcar.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportValidator {

    private static final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    private static final Pattern pattern = Pattern.compile(regex);

    public static Matcher matcher(String password) {
        return pattern.matcher(password);
    }


}
