package io.digitalstate.stix.validation.contraints.vocab;

import io.digitalstate.stix.vocabularies.StixVocabulary;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * <p>Provides STIX Vocabulary validation for {@code String} and {@code Set<String>} fields.</p>
 * <br>
 * <p>Value is the vocabulary class to be used for validation.
 * The class must implement {@link StixVocabulary} interface.</p>
 * <br>
 * <p>Example usage: {@code @HashingVocab(AttackMotivations.class)}</p>
 */
@Documented
@Constraint(validatedBy = {StixVocabValidatorString.class, StixVocabValidatorCollection.class})
@Target( { METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Vocab {
    String message() default "{io.digitalstate.stix.validation.contraints.VocabContains}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<? extends StixVocabulary>  value();

}
