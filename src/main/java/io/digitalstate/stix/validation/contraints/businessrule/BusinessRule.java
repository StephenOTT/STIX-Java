package io.digitalstate.stix.validation.contraints.businessrule;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Documented
@Constraint(validatedBy = {StixValidateBusinessRuleValidator.class})
@Target( { ANNOTATION_TYPE, TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(BusinessRule.List.class)
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
    //@TODO look into making the thenExp param into a array. So you can provide multiple independent conditions that all must result in the ExpectedResult value
    String thenExp();
    String errorMessage() default "An error occurred";
    boolean expectedResult() default true;

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target( { ANNOTATION_TYPE, TYPE })
    @interface List {
        BusinessRule[] value();
    }

}
