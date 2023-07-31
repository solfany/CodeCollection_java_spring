package org.example;

public class PasswordValidator {

    /**
     * 비밀번호가 8자 미만 또는 12자 초과하는 경우 IllegalArgumentException 예외가 발생한다.
     */

    //비밀번호 조건 코드 작성


//    validate 메소드:
//    매개변수로 받은 password의 길이를 확인하여 8자 미만이거나 12자 초과인 경우 IllegalArgumentException 예외를 발생시킨다
//    비밀번호의 길이만을 검사하고 있으며, 외부에서 비밀번호를 생성하는 정책을 사용하지 않는다.
    public void validate(String password) {
        int length = password.length();

        if (length < 8 || length > 12) {
            throw new IllegalArgumentException("비밀번호는 최소 8자 이상 12자 이하여야 한다.");
        }
    }


//    validate2 메소드:
//    PasswordGeneratePolicy 인터페이스를 구현하는 클래스를 매개변수로 받아 사용한다.
//    passwordGeneratePolicy의 generatePassword 메소드를 호출하여 비밀번호를 생성한 뒤, 해당 비밀번호의 길이를 검사한다
//    generatePassword 메소드는 PasswordGeneratePolicy 인터페이스를 구현하는 클래스에서 구현되는 메소드로, 외부에서 비밀번호 생성 정책을 자유롭게 구현할 수 있도록 한다.
//    validate2 메소드는 다양한 비밀번호 생성 정책을 적용할 수 있도록 유연성을 제공한다.
    public void validate2(PasswordGeneratePolicy passwordGeneratePolicy) {
        String password = passwordGeneratePolicy.generatePassword();

        int length = password.length();
        if (length < 8 || length > 12) {
            throw new IllegalArgumentException("비밀번호는 최소 8자 이상 12자 이하여야 한다.");
        }
    }
}

// 즉, validate 메소드는 직접 비밀번호를 전달받아 길이를 검사하는 반면,
// validate2 메소드는 외부에서 비밀번호 생성 정책을 구현한 클래스를 전달받아 해당 정책에 따라 비밀번호를 생성하고 검사한다.
// 이를 통해 validate2 메소드는 다양한 비밀번호 생성 정책을 지원하며,
// PasswordGeneratePolicy 인터페이스를 구현한 클래스를 사용하여 비밀번호를 생성할 수 있다.
