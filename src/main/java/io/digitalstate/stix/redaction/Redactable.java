package io.digitalstate.stix.redaction;

import org.immutables.annotate.InjectAnnotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Marker to indicate which classes and methods are Redactable.
 * Redaction is the modification of JSON properties or removal of properties and
 * entire objects during serialization based on STIX Marking Definitions and Granular Markings.
 */
@Documented
@Target( { ANNOTATION_TYPE, TYPE, METHOD })
@Retention(RetentionPolicy.RUNTIME)
@InjectAnnotation(target = {InjectAnnotation.Where.ACCESSOR, InjectAnnotation.Where.IMMUTABLE_TYPE}, code = "([[*]])", type = Redactable.class)
public @interface Redactable {

    boolean useMask() default false;
    String redactionMask() default ""+ ((char)0x2588) + ((char)0x2588) + "REDACTED" + ((char)0x2588) + ((char)0x2588);

    //@TODO:
    // Add Masking config
    // Add Conditional
    // Add object Refs
}
