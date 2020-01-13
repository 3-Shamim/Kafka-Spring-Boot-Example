package com.learningstufff.kafkaproducre.controllers;

import com.learningstufff.kafkaproducre.models.Student;
import com.learningstufff.kafkaproducre.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ১৩/১/২০
 * Time: ২:৫৯ PM
 * Email: mdshamim723@gmail.com
 */

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Value(value = "${kafka.topic}")
    private String topic;
    private final KafkaProducerService kafkaProducerService;

    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> test() {
        return ResponseEntity.status(HttpStatus.OK).body("Kafka test api.");
    }

    /* Note
     * Custom Topic "Hello_World"
     * */
    @GetMapping(value = "/publish/{name}")
    public ResponseEntity<?> kafkaPostString(@PathVariable(value = "name") String name) {

        kafkaProducerService.sendMessage("Hello_World", name);

        return ResponseEntity.status(HttpStatus.OK).body("Publish successfully.");
    }

    /* Note
    * Custom Topic "Hello_World"
    * */
    @PostMapping(value = "/publish/student")
    public ResponseEntity<?> kafkaPostStudent(@RequestBody Student student) {

        kafkaProducerService.sendMessage("Hello_World", student);

        return ResponseEntity.status(HttpStatus.OK).body("Publish successfully.");
    }

    @PostMapping(value = "/publish-callback/student")
    public ResponseEntity<?> kafkaPostStudentCallBack(@RequestBody Student student) {

        kafkaProducerService.sendMessageCallBack(topic, student);

        return ResponseEntity.status(HttpStatus.OK).body("Publish successfully.");
    }



}
