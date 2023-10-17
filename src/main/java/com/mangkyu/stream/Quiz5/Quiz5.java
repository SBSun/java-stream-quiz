package com.mangkyu.stream.Quiz5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Quiz5 {

    private static final String[] STRING_ARR = {"aaa", "bb", "c", "dddd"};

    // 5.1 모든 문자열의 길이를 더한 결과를 출력하여라.
    public int quiz1() {
        int res = Arrays.stream(STRING_ARR)
                .mapToInt(String::length)
                .sum();

        return res;
    }

    // 5.2 문자열 중에서 가장 긴 것의 길이를 출력하시오.
    public int quiz2() {
        int res = Arrays.stream(STRING_ARR)
                .mapToInt(String::length)
                .max()
                .orElse(0);

        return res;
    }

    // 5.3 임의의 로또번호(1~45)를 정렬해서 출력하시오.
    public List<Integer> quiz3() {
        // 1~45 랜덤값 생성
        List<Integer> res = new Random().ints(1, 46)
                // 중복 제거
                .distinct()
                // 6개
                .limit(6)
                // Stream<Integer> 생성
                .boxed()
                .sorted()
                .collect(Collectors.toList());

        return res;
    }

    // 5.4 두 개의 주사위를 굴려서 나온 눈의 합이 6인 경우를 모두 출력하시오.
    public List<Integer[]> quiz4() {
        // 1~6 생성
        List<Integer[]> res = IntStream.rangeClosed(1, 6)
                .boxed()
                // 1~6을 다시 한 번 생성하여 모든 경우의 수 생성
                .flatMap(a -> IntStream.rangeClosed(1, 6)
                        .boxed()
                        // 합이 6인 것만
                        .filter(b -> a + b == 6)
                        .map(b -> new Integer[]{a, b}))
                .collect(Collectors.toList());

        return res;
    }

}
