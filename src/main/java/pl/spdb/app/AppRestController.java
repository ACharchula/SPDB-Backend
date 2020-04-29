package pl.spdb.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController {

    @GetMapping("/hello")
    public @ResponseBody String hello() {
        return "hello";
    }
}
