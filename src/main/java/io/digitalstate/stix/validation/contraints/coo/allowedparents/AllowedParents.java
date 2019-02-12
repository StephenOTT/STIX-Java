package io.digitalstate.stix.validation.contraints.coo.allowedparents;

import io.digitalstate.stix.coo.CyberObservableObject;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Values must be Array of Cyber Observable Object Interfaces!.
 */
@Documented
@Target( { ANNOTATION_TYPE, TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedParents {
    Class<? extends CyberObservableObject>[] value();
}
