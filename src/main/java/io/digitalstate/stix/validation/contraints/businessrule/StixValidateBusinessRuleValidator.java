package io.digitalstate.stix.validation.contraints.businessrule;

import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.coo.extension.CyberObservableExtension;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;


public class StixValidateBusinessRuleValidator implements ConstraintValidator<BusinessRule, Object> {

    private String ifExp;
    private String thenExp;
    private String errorMessage;
    private boolean expectedResult;

    //@TODO review compilation settings and how to optimize this
    private SpelParserConfiguration spelConfig = new SpelParserConfiguration(SpelCompilerMode.MIXED, this.getClass().getClassLoader());
    private ExpressionParser parser = new SpelExpressionParser(spelConfig);

    @Override
    public void initialize(BusinessRule constraintAnnotation) {
        ifExp = constraintAnnotation.ifExp();
        thenExp = constraintAnnotation.thenExp();
        errorMessage = constraintAnnotation.errorMessage();
        expectedResult = constraintAnnotation.expectedResult();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext cxt) {

        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setRootObject(value);

        Expression evalIf = parser.parseExpression(ifExp);
        boolean evalIfResult = Optional.ofNullable(evalIf.getValue(evaluationContext, Boolean.class))
                .orElseThrow(() -> new IllegalArgumentException("Unable to parse business rule's ifExp"));

        if (!evalIfResult) {
            // If the if statement is false then no further eval is required as the rule does not apply
            return true;

        } else {
            // If the business rule applies then:
            Expression evalThen = parser.parseExpression(thenExp);
            boolean evalThenResult = Optional.ofNullable(evalThen.getValue(evaluationContext, Boolean.class))
                    .orElseThrow(() -> new IllegalArgumentException("Unable to parse business rule's thenExp"));

            if (evalThenResult == expectedResult) {
                return true;

            } else {
                String violationMessage = errorMessage;
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                return false;
            }
        }
    }
}
