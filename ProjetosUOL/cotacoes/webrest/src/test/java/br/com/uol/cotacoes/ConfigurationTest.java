package br.com.uol.cotacoes;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


/**
 * Created by vrx_mtoledo on 09/05/17.
 */
@Configuration
@Profile("test")
public class ConfigurationTest {

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
        return hemf.getSessionFactory();
    }


    @Bean
    public CacheManager cacheManager() {
        return new NoOpCacheManager();
    }


}
