package io.digitalstate.stix.redaction;

import org.immutables.annotate.InjectAnnotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


@Documented
@Target( { ANNOTATION_TYPE, TYPE, FIELD, METHOD })
@Retention(RetentionPolicy.RUNTIME)
@InjectAnnotation(target = InjectAnnotation.Where.ACCESSOR, code = "([[*]])", type = Redactable.class)
public @interface Redactable {

    boolean useMask() default false;
    String redactionMask() default ""+ ((char)0x2588) + ((char)0x2588) + "REDACTED" + ((char)0x2588) + ((char)0x2588);

    //@TODO:
    // Add Masking config
    // Add Conditional
    // Add object Refs
}
