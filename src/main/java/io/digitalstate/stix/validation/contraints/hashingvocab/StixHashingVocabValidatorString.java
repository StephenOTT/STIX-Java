package io.digitalstate.stix.validation.contraints.hashingvocab;

import com.google.common.collect.Sets;
import io.digitalstate.stix.vocabulary.StixVocabulary;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StixHashingVocabValidatorString implements ConstraintValidator<HashingVocab, String> {

    private Class<? extends StixVocabulary> vocabulary;

    @Override
    public void initialize(HashingVocab hashingVocabConstraint) {
        vocabulary = hashingVocabConstraint.value();
    }

    @Override
    public boolean isValid(String vocab,
                           ConstraintValidatorContext cxt) {
        if (vocab.startsWith("x_")) {
            return true;
        } else {
            try {
                Set<String> vocabTerms = vocabulary.newInstance().getAllTerms();
                boolean evalContains = vocabTerms.contains(vocab);
                if (!evalContains) {
                    Sets.SetView<String> difference = Sets.difference(new HashSet<>(Arrays.asList(vocab)), vocabTerms);

                    String violationMessage = "Item: " + difference.toString() + " is not found in class " + vocabulary.getCanonicalName();
                    cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                    return false;
                } else {
                    return true;
                }

            } catch (InstantiationException e) {
                String violationMessage = "InstantiationException from " + vocabulary.getSimpleName();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                e.printStackTrace();
                return false;

            } catch (IllegalAccessException e) {
                String violationMessage = "IllegalAccessException from " + vocabulary.getSimpleName();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                e.printStackTrace();
                return false;
            }
        }
    }
}
