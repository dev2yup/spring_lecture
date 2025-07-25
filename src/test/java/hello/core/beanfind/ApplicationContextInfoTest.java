package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("Name = " + beanDefinitionName + " Object = " + bean);
        }
    }

    /**
     * 스프링이 내부에서 사용하는 빈은 제외하고, 내가 등록한 빈만 출력해보자.
     *     스프링이 내부에서 사용하는 빈은 `getRole()` 로 구분할 수 있다.
     *  `ROLE_APPLICATION` : 일반적으로 사용자가 정의한 빈
     * `ROLE_INFRASTRUCTURE` : 스프링이 내부에서 사용하는 빈
     */
    @Test
    @DisplayName("모든 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("Name = " + beanDefinitionName + " Object = " + bean);
            }

        }
    }

}
