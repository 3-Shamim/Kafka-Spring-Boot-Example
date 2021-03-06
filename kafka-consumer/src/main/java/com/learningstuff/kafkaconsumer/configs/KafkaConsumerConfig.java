package com.learningstuff.kafkaconsumer.configs;

import com.learningstuff.kafkaconsumer.models.Student;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ১৩/১/২০
 * Time: ৪:১০ PM
 * Email: mdshamim723@gmail.com
 */

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.boot-strap-server}")
    private String kafkaBootStrapServer;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootStrapServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    /* Note
    * default name:  kafkaListenerContainerFactory
    * */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    ConsumerFactory<String, Student> consumerJsonFactory() {

        JsonDeserializer<Student> deserializer = new JsonDeserializer<>(Student.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootStrapServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Student> kafkaListenerJsonFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Student> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerJsonFactory());
        return factory;
    }

}
