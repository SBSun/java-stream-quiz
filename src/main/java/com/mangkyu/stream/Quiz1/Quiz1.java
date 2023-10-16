package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Quiz1 {

    // 1.1 각 취미를 선호하는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz1() throws IOException {
        List<String[]> csvLines = readCsvLines();
        Map<String, Integer> map = csvLines.stream()
                .map(line -> line[1].replaceAll("\\s", ""))
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldValue, newValue) -> oldValue += newValue));
        return map;
    }

    // 1.2 각 취미를 선호하는 정씨 성을 갖는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();
        Map<String, Integer> map = csvLines.stream()
                // 성이 정씨인 인원만 필터링
                .filter(line -> line[0].startsWith("정"))
                .map(line -> line[1].replaceAll("\\s", ""))
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                // 해당 취미에 인원 1 추가
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldValue, newValue) -> oldValue += 1));
        return map;
    }

    // 1.3 소개 내용에 '좋아'가 몇번 등장하는지 계산하여라.
    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        int cnt = csvLines.stream()
                // 원본 문자열의 길이에서 원본 문자열에서 '좋아' 단어들을 제외한 문자열의 길이
                .map(line -> (line[2].length() - line[2].replace("좋아", "").length()) / 2)
                // 각 라인의 '좋아' 개수들의 합 계산
                .reduce(0, Integer::sum);

        return cnt;
    }

    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

}
