package org.example;

public class Main {
    public static void main(String[] args) {
        RandomPasswordGenerator passwordGenerator = new RandomPasswordGenerator();
        String password = passwordGenerator.generatePassword();
        System.out.println("랜덤 비밀번호 Generated password: " + password);
    }
}

//랜던 비밀번호 출력(RandomPasswordGenerator)
