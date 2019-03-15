package io.digitalstate.stix.validation.contraints.businessrule;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

/**
 * The interface Business rule.
 */
@Documented
@Constraint(validatedBy = {StixValidateBusinessRuleValidator.class})
@Target( { ANNOTATION_TYPE, TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(BusinessRule.List.class)
public @interface BusinessRule {

    /**
     * Message string.  Not Used.  The Error Message is used instead.
     *
     * @return the string
     */
    String message() default "An business rule failed to validate";

    /**
     * Groups class [ ].
     *
     * @return the class [ ]
     */
    Class<?>[] groups() default {};

    /**
     * Payload class [ ].
     *
     * @return the class [ ]
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * If exp string.
     *
     * @return the string
     */
    String ifExp();

    /**
     * Then exp string.
     *
     * @return the string
     */
//@TODO look into making the thenExp param into a array. So you can provide multiple independent conditions that all must result in the ExpectedResult value
    String thenExp();

    /**
     * Error message string.
     *
     * @return the string
     */
    String errorMessage();

    /**
     * Expected result boolean.
     *
     * @return the boolean
     */
    boolean expectedResult() default true;

    /**
     * The interface List.
     */
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target( { ANNOTATION_TYPE, TYPE })
    @interface List {
        /**
         * Value business rule [ ].
         *
         * @return the business rule [ ]
         */
        BusinessRule[] value();
    }
}
