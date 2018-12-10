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
        if (source.isAssignableFrom(relationshipSro.getSourceRef().getClass())){
            List<String> typesList = Arrays.asList(relationshipTypes);
            List<String> commonTypesList = Arrays.asList(commonRelationshipsTypes);
            if (typesList.contains(relationshipSro.getRelationshipType())){
                return true;
            } else if (enforceCommonRelationshipTypes && commonTypesList.contains(relationshipSro.getRelationshipType())){
                return true;
            }else {
                String violationMessage = "Relationship Type: '" + relationshipSro.getRelationshipType() +
                        "' is not supported with class " + relationshipSro.getClass().getCanonicalName();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                return false;
            }
        } else {
            return true;
        }
    }
}
