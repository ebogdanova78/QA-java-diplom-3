package constants;

import org.apache.commons.lang3.RandomStringUtils;

public class Constants {

    public static final String MAINPAGE = "https://stellarburgers.nomoreparties.site";
    //public static final String LOGINPAGE = "https://stellarburgers.nomoreparties.site/login";
    public static final String REGISTRATIONPAGE = "https://stellarburgers.nomoreparties.site/register";
    public static final String FORGOTPASSWORDPAGE ="https://stellarburgers.nomoreparties.site/forgot-password";
    //public static final String ACCOUNTPAGE = "https://stellarburgers.nomoreparties.site/account/profile";
    public static final String VALID_NAME = "User";
    public static final String USER_NAME = "User_name";
    public static final String USER_EMAIL ="User1133@yandex.ru";
    public static final String USER_PASSWORD = "11331133";
    public static String randomCorrectEmail = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
    public static String randomCorrectPassword = RandomStringUtils.randomAlphanumeric(8);
    public static String randomIncorrectPassword = RandomStringUtils.randomAlphanumeric(5);

}
