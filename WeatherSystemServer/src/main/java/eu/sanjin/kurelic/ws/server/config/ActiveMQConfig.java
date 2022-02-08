package eu.sanjin.kurelic.ws.server.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@Configuration
public class ActiveMQConfig {

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory();
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        var template = new JmsTemplate();

        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName("weather_report");

        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        var factory = new DefaultJmsListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");

        return factory;
    }
}
