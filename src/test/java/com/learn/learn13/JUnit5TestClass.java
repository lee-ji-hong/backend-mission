package com.learn.learn13;

import org.junit.jupiter.api.*;

public class JUnit5TestClass {
    @BeforeAll
    static void setUpBeforeAll() {
        System.out.println("@BeforeAll Annotation");
    }

    @Test
    void testAddingTwoNumbers() {
        // given : 초기 상태 지정
        int a = 2;
        int b = 3;

        // when : 테스트 동작 정의
        int result = Calculator.add(a, b);

        // then : 검증
        int expected = 5;
        Assertions.assertEquals(expected, result);
        System.out.println("Test for adding two numbers passed");
    }

    @Test
    void testDividingTwoNumbers() {
        // given : 초기 상태 지정
        int a = 10;
        int b = 2;

        // when : 테스트 동작 정의
        int result = Calculator.divide(a, b);

        // then : 검증
        int expected = 5;
        Assertions.assertEquals(expected, result);
        System.out.println("Test for dividing two numbers passed");
    }

    @AfterAll
    static void cleanUpAfterAll() {
        System.out.println("@AfterAll Annotation");
    }
}

class Calculator {
    static int add(int a, int b) {
        return a + b;
    }

    static int divide(int a, int b) {
        return a / b;
    }
}
