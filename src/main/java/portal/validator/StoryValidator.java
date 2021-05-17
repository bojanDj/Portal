package portal.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import portal.dto.Story;


@Component
public class StoryValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Story.class.equals(clazz) ;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Story story = (Story) target;

        System.out.println("Validating story: " + story);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tekst", "story.tekst.empty", "story.tekst.empty = Tekst nije unet.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "naslov", "story.naslov.empty", "story.naslov.empty = Naslov nije unet.");
        
        if (errors.hasErrors()) {
            return;
        }

//        if () {
//            errors.rejectValue("broj", "ocena.broj.empty", "ocena.broj.empty = Ocena mora biti u rasponu od 1 do 5");
//        }
        
    }
}
