package ua.com.alevel.exception;

//import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

//import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(EntityExistException.class)
//    public ResponseEntity error(EntityExistException e) {
//        return ResponseEntity.status(409).body("");
//    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ModelAndView entityNotFoundErrorHandler(EntityNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = {EntityExistException.class})
    public ModelAndView entityExistErrorHandler(EntityExistException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ModelAndView constraintViolationErrorHandler(ConstraintViolationException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ModelAndView methodArgumentNotValidErrorHandler(MethodArgumentNotValidException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String serverError(final Throwable throwable, final Model model) {
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("showMessage", true);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(final Exception throwable, final Model model) {
        System.out.println("GlobalExceptionHandler.notFound");
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("showMessage", true);
        return "error";
    }
}
