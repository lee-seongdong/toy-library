package toy.lsd.library.monitor.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class L7CheckController {
    @GetMapping("l7check")
    fun l7check(): String {
        return "OK"
    }
}