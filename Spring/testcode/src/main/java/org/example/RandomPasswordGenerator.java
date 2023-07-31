package org.example;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class RandomPasswordGenerator implements PasswordGeneratePolicy {
    /**
     * Special characters allowed in password.
     */
    public static final String ALLOWED_SPL_CHARACTERS = "!@#$%^&*()_+";

    public static final String ERROR_CODE = "ERRONEOUS_SPECIAL_CHARS";

    @Override
    public String generatePassword() {
        PasswordGenerator gen = new PasswordGenerator();

        // 소문자 설정
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2); // 비밀번호에 소문자가 최소 2개 포함되도록 설정

        // 대문자 설정
        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2); // 비밀번호에 대문자가 최소 2개 포함되도록 설정

        // 숫자 설정
        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2); // 비밀번호에 숫자가 최소 2개 포함되도록 설정

        // 특수 문자 설정
        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return ALLOWED_SPL_CHARACTERS;
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2); // 비밀번호에 특수 문자가 최소 2개 포함되도록 설정

        // 0 ~ 12 길이의 비밀번호 생성
        return gen.generatePassword((int) (Math.random() * 13), splCharRule, lowerCaseRule, upperCaseRule, digitRule);
    }
}
