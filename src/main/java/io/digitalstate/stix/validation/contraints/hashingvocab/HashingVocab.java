package io.digitalstate.stix.validation.contraints.hashingvocab;

import io.digitalstate.stix.vocabulary.StixVocabulary;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;

/**
 * <p>A modified version of the @StartsWith annotation, where this modified version
 * provides a parameter level control over Map keys, that meet the Hashes spec,
 * and allows x_ key names in addition</p>
 */
@Documented
@Constraint(validatedBy = {StixHashingVocabValidatorString.class})
@Target( { ANNOTATION_TYPE, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface HashingVocab {
    String message() default "{io.digitalstate.stix.validation.contraints.hashingvocab}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<? extends StixVocabulary>  value();

}
