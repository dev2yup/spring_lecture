package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A 사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //statefulService1.order("userA", 10000);
        //ThreadB: B 사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);
//        statefulService2.order("userB", 20000);

        //ThreadA: 사용자 A가 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("userAPrice = " + userAPrice);

        // 싱글톤 패턴이기 때문에 20000원이 조회 됨 -> 문제
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean // 공유 필드는 조심해야 함. 스프링 빈은 무상태(stateless)로 설계해야 한다.
        public StatefulService statefulService() {
        return new StatefulService();
        }
    }

}