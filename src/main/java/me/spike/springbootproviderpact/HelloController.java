package me.spike.springbootproviderpact;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final GreetingService service;

    public HelloController(GreetingService service) {
        this.service = service;
    }

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Greeting> hello(@RequestParam(name = "firstName") String firstName) {
        final Greeting greeting = service.greet(firstName);
        if (greeting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(greeting);
    }
}
