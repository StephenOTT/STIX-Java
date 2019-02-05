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
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;


public class StixValidateBusinessRuleValidator implements ConstraintValidator<BusinessRule, Object> {

    private String ifExp;
    private String thenExp;
    private String errorMessage;
    private boolean expectedResult;

    @Override
    public void initialize(BusinessRule constraintAnnotation) {
        ifExp = constraintAnnotation.ifExp();
        thenExp = constraintAnnotation.thenExp();
        errorMessage = constraintAnnotation.errorMessage();
        expectedResult = constraintAnnotation.expectedResult();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext cxt) {
        SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.MIXED,
                this.getClass().getClassLoader());

        ExpressionParser parser = new SpelExpressionParser(config);
        ParserContext parserContext = new TemplateParserContext();
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
//        new SimpleEvaluationContext()
        evaluationContext.setRootObject(value);

        Expression evalIf = parser.parseExpression(ifExp);

        Expression evalThen = parser.parseExpression(thenExp);

        boolean evalIfResult = Optional.ofNullable(evalIf.getValue(evaluationContext, Boolean.class))
                .orElseThrow(() -> new IllegalArgumentException("Unable to parse business rule's ifExp"));

        if (evalIfResult){
            boolean evalThenResult = Optional.ofNullable(evalThen.getValue(evaluationContext, Boolean.class))
                    .orElseThrow(() -> new IllegalArgumentException("Unable to parse business rule's thenExp"));

            if (evalThenResult == expectedResult){
                return true;
            } else {
                String violationMessage = errorMessage;
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                return false;
            }
        } else {
            return true;
        }
    }
}
