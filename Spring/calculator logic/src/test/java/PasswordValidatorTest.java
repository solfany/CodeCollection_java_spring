import org.example.PasswordValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class PasswordValidatorTest {

    /**
     * 비밀번호는 최소 8자 이상 12자 이하여야 한다.
     * 비밀번호가 8자 미만 또는 12자 초과인 경우 IllegalArgumentException 예외를 발생시킨다.
     * 경계조건에 대해 테스트 코드를 작성해야 한다.
     */
    @DisplayName("비밀번호가 최소 8자 이상, 12자 이하면 예외가 발생하지 않는다.")
    @Test
//    주어진 비밀번호가 8자 이상 12자 이하인 경우, PasswordValidator의 validate 메소드가 예외를 발생시키지 않는지 확인하는 테스트
    void validatePasswordTest() {
        // given
        String password = "serverwizard";
        PasswordValidator passwordValidator = new PasswordValidator();

        // when, then
        assertThatCode(() -> passwordValidator.validate(password))
                .doesNotThrowAnyException();
    }
//==============================================================================================

    @DisplayName("비밀번호가 8자 미만 또는 12자 초과하는 경우 IllegalArgumentException 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"aabbcce", "aabbccddeeffg"})

//    주어진 비밀번호가 8자 미만 또는 12자 초과하는 경우,
//    PasswordValidator의 validate 메소드가 IllegalArgumentException 예외를 발생시키고
//    예외 메시지가 "비밀번호는 최소 8자 이상 12자 이하여야 한다."인지 확인하는 매개변수화 테스트이다.
//    @ValueSource를 사용하여 여러 비밀번호 값을 전달한다.
    void validatePasswordTest2(String value) {
        // given
        PasswordValidator passwordValidator = new PasswordValidator();

        // when, then
        assertThatCode(() -> passwordValidator.validate(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 최소 8자 이상 12자 이하여야 한다.");
    }

    /**
     * 테스트 하기 쉬운 코드를 작성하다 보면 더 낮은 결합도를 가진 설계를 얻을 수 있다.
     */
    @DisplayName("비밀번호가 최소 8자 이상, 12자 이하면 예외가 발생하지 않는다.")
    @Test
    void validatePasswordTest2() {
        // given
        PasswordValidator passwordValidator = new PasswordValidator();

        // when, then
        assertThatCode(() -> passwordValidator.validate2(new CorrectPasswordGenerator()))
                .doesNotThrowAnyException();
    }


    @DisplayName("비밀번호가 8자 미만 또는 12자 초과하는 경우 IllegalArgumentException 예외가 발생한다.")
    @Test
    void validatePasswordTest3() {
        // given
        PasswordValidator passwordValidator = new PasswordValidator();

        // when, then
        assertThatCode(() -> passwordValidator.validate2(new WrongPasswordGenerator()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 최소 8자 이상 12자 이하여야 한다.");
    }
}
