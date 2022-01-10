package ua.com.alevel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ModelAndView entityNotFoundErrorHandler(EntityNotFoundException exception) {
        return generateModelAndView(exception.getMessage());
    }

    @ExceptionHandler(value = {EntityExistException.class})
    public ModelAndView entityExistErrorHandler(EntityExistException exception) {
        return generateModelAndView(exception.getMessage());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ModelAndView constraintViolationErrorHandler(ConstraintViolationException exception) {
        return generateModelAndView(exception.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ModelAndView methodArgumentNotValidErrorHandler(MethodArgumentNotValidException exception) {
        return generateModelAndView(exception.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ModelAndView methodArgumentNotValidErrorHandler(MethodArgumentTypeMismatchException exception) {
        return generateModelAndView("incorrect value");
    }

    @ExceptionHandler(Throwable.class)
    public String serverError(final Throwable throwable, final Model model) {
        System.out.println("GlobalExceptionHandler.serverError");
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

    private ModelAndView generateModelAndView(String msg) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", msg);
        mav.setViewName("error");
        return mav;
    }
}
