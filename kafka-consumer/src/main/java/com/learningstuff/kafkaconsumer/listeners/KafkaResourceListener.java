package com.learningstuff.kafkaconsumer.listeners;

import com.learningstuff.kafkaconsumer.models.Student;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ১৩/১/২০
 * Time: ৪:২২ PM
 * Email: mdshamim723@gmail.com
 */

@Service
public class KafkaResourceListener {

    /* Note
     * default containerFactory:  kafkaListenerContainerFactory
     * */
    @KafkaListener(topics = "Hello_World", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }


    @KafkaListener(topics = "Demo", groupId = "group_json",
            containerFactory = "kafkaListenerJsonFactory")
    public void consumeJson(Student student) {
        System.out.println("Consumed JSON Message: " + student);
    }

}
