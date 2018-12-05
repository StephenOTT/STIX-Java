package io.digitalstate.stix.validation.contraints.relationship;

import com.google.common.collect.Sets;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sro.objects.RelationshipSro;
import io.digitalstate.stix.vocabularies.StixVocabulary;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StixRelationshipTypeLimitValidator implements ConstraintValidator<RelationshipTypeLimit, RelationshipSro> {

    private Class<? extends DomainObject> source;
    private String[] relationshipTypes;
    private String[] commonRelationshipsTypes;
    private boolean enforceCommonRelationshipTypes;

    @Override
    public void initialize(RelationshipTypeLimit relationshipTypeLimitConstraint) {
        source = relationshipTypeLimitConstraint.source();
        relationshipTypes = relationshipTypeLimitConstraint.relationshipTypes();
        commonRelationshipsTypes = relationshipTypeLimitConstraint.commonRelationshipTypes();
        enforceCommonRelationshipTypes = relationshipTypeLimitConstraint.enforceCommonRelationshipTypes();
    }

    @Override
    public boolean isValid(RelationshipSro relationshipSro,
                           ConstraintValidatorContext cxt) {
        System.out.println(relationshipSro.getClass());
        System.out.println(source);
        if (source.isAssignableFrom(relationshipSro.getSourceRef().getClass())){
            List<String> typesList = Arrays.asList(relationshipTypes);
            List<String> commonTypesList = Arrays.asList(commonRelationshipsTypes);
        System.out.println("1");
            if (typesList.contains(relationshipSro.getRelationshipType())){
                System.out.println("2");
                return true;
            } else if (enforceCommonRelationshipTypes && commonTypesList.contains(relationshipSro.getRelationshipType())){
                System.out.println("3");
                return true;
            }else {
                System.out.println("5");
                String violationMessage = "Relationship Type: '" + relationshipSro.getRelationshipType() +
                        "' is not supported with class " + relationshipSro.getClass().getCanonicalName();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                return false;
            }
        } else {
            System.out.println("4");
            return true;
        }
    }
}
