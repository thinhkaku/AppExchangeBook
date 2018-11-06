package com.example.administrator.demo_app_mua_ban_trao_doi_sach.config;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class KiemTraKiTuDacBiet {
    private Pattern pattern;
    private Matcher matcher;

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._]{2,50}$";

    public KiemTraKiTuDacBiet() {
        pattern = Pattern.compile(USERNAME_PATTERN);
    }

    /**
     * Validate username with regular expression
     *
     * @param username username for validation
     * @return true valid username, false invalid username
     */
    public boolean validate(final String username) {
        matcher = pattern.matcher(username);
        return matcher.matches();
    }
}
