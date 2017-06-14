package com.vb.contactbook.utils;

import java.util.regex.Pattern;

/**
 * Created by bonar on 6/14/2017.
 */

public class StringUtils {
    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String PHONE_PATTERN = "[0-9]+";

    public static final String NAME_PATTERN = "[a-zA-Z]+";


    public static boolean isMatching(final String string, final String pattern) {
        return Pattern.compile(pattern)
                .matcher(string)
                .matches();
    }
}
