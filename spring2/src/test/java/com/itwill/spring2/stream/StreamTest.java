package com.itwill.spring2.stream;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

// 스프링 컨텍스(application-context.xml 또는 servlet-context.xml)을 사용하지 않는
// 단위 테스트에서는 @ExtendWith, @ContextConfiguration 애너테이션을 사용할 필요가 없음.
public class StreamTest {

    @Test
    public void test() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println(numbers);
        
        // numbers에서 홀수들만 필터링한 결과:
        List<Integer> odds = numbers.stream()
                    .filter((x) -> x % 2 == 1) // 아규먼트를 전달하면 리턴값(true, false)만 있으면 됨.
                    .toList();
        
        System.out.println(odds);
        
        // numbers의 원소들의 제곱
        List<Integer> squares = numbers.stream() // numbers를 stream으로 보냄.
                .map((x) -> x * x) // 원소 개수만큼 어떤 값이 들어가면 다른 값으로 변환된다 라는게 mapper
                .toList();
        System.out.println(squares);
        
        // numbers의 원소들 중 홀수들의 제곱
        List<Integer> oddSquares = numbers.stream()
                .filter((x) -> x % 2 == 1)
                .map((x) -> x * x)
                .toList();
        
        System.out.println(oddSquares);
       
        List<String> languages = Arrays.asList("Java", "SQL", "JavaScript");
        System.out.println(languages);
        
        // languages가 가지고 있는 문자열들의 길이를 원소로 갖는 리스트
        List<Integer> lengths = languages.stream()
//                .map((x) -> x.length()) 
                .map(String::length) // 함수 이름을 쓰는 람다 표현식, 아규먼트가 하나밖에없고 리턴값이 아규먼트에서 메서드를 호출(x.length())
                .toList();
        System.out.println(lengths);
        
        List<LocalDateTime> times = Arrays.asList(
                    LocalDateTime.of(2023, 5, 23, 11, 30, 0),
                    LocalDateTime.of(2023, 5, 24, 15, 30, 0),
                    LocalDateTime.of(2023, 5, 25, 19, 00, 0)
                );
                System.out.println(times);
                
                // LocalDateTime  타입을 Timestamp 타입으로 변환
                List<Timestamp> timestamps = times.stream()
                        .map((x) -> Timestamp.valueOf(x)) // 전달한 x가 새로운 메서드의 아규먼트로 들어감. 아규먼트를 전달해서 그대로 아규먼트로 들어감.
                        .toList();                                    // 메서드의 바디가 한문장 밖에 없으면 return 생략.
                System.out.println(timestamps);
        
    }
    
}
