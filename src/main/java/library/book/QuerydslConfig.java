package library.book;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@EnableJpaAuditing
public class QuerydslConfig {
    @Configuration
    public class QueryDslConfig {
        @PersistenceContext
        private EntityManager entityManager;
        @Bean
        public JPAQueryFactory jpaQueryFactory(){
            return new JPAQueryFactory(entityManager);
        }
    }
}
