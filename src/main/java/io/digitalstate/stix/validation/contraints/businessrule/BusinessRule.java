package io.digitalstate.stix.validation.contraints.businessrule;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Documented
@Constraint(validatedBy = {StixValidateBusinessRuleValidator.class})
@Target( { ANNOTATION_TYPE, TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessRule {

    // @BusinessRule(if = “some_field”, is = PRESENT, then = {“some1”, “some11”}, mustBe = PRESENT)
    // @BusinessRule(if = “url”, is = PRESENT, then = {“payload_bin”}, mustBe = EMPTY)
    // @BusinessRule(if = “url”, is = PRESENT, then = {“hashes”}, mustBe = PRESENT)
    // @BusinessRule(if = “payload_bin”, is = PRESENT, then = {“url”}, mustBe = EMPTY)

    // @BusinessRule(if = “url”, is = PRESENT, then = {“payload_bin”}, mustBe = EMPTY)
    // @BusinessRule(if = “url”, is = PRESENT, then = {“some1”, "some2}, mustBe = PRESENT, MIN=, MAX=, )

    //getAslrEnabled.isPresent() == true

    String message() default "{io.digitalstate.stix.validation.contraints.businessrule.BusinessRule}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String ifExp();
    String thenExp();
    String errorMessage() default "An error occurred";
    boolean expectedResult() default true;

}
