package toy.lsd.library;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class L7checker {
	@GetMapping("l7check")
	public String l7check() {
		return "OK";
	}
}
