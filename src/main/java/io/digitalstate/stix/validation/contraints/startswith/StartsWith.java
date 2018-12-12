package io.digitalstate.stix.validation.contraints.startswith;

import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.validation.contraints.vocab.StixVocabValidatorCollection;
import io.digitalstate.stix.vocabularies.StixVocabulary;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * <p>Provides a Starts With validator of String values.</p>
 * <br>
 * <p>Defaults to {@link StixDataFormats#CUSTOM_PROPERTY_PREFIX}</p>
 */
@Documented
@Constraint(validatedBy = {StixStartsWithValidatorString.class})
@Target( { ANNOTATION_TYPE, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface StartsWith {
    String message() default "{io.digitalstate.stix.validation.contraints.startswith.StartsWith}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String value() default StixDataFormats.CUSTOM_PROPERTY_PREFIX;

}
