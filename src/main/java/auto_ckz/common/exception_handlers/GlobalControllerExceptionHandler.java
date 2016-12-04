package auto_ckz.common.exception_handlers;

import auto_ckz.site.error.NotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	public String handleNotFoundException(final NotFoundException ex, Model model) {
		model.addAttribute("errorMessage", ex.getMessage());
		return "error/general";
	}
}
