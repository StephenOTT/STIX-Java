package io.digitalstate.stix.coo.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.validation.contraints.businessrule.BusinessRule;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * x509-certificate
 * <p>
 * The X509 Certificate Object represents the properties of an X.509 certificate.
 * Note that the X.509 v3 Extensions type is not a STIX Cyber Observables extension,
 * it is a type that describes X.509 extensions.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "x509-v3-extensions-type", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Obj", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonSerialize(as = X509v3Extensions.class) @JsonDeserialize(builder = X509v3Extensions.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "basic_constraints", "name_constraints", "policy_constraints", "key_usage", "extended_key_usage",
        "subject_key_identifier", "authority_key_identifier", "subject_alternative_name", "issuer_alternative_name",
        "subject_directory_attributes", "crl_distribution_points", "inhibit_any_policy",
        "private_key_usage_period_not_before", "private_key_usage_period_not_after", "certificate_policies",
        "policy_mappings" })
@JsonTypeName("x509-v3-extensions-type")
@BusinessRule(ifExp = "true", thenExp = "getBasicConstraints().isPresent() == true || getNameConstraints().isPresent() == true || getPolicyConstraints().isPresent() == true || getKeyUsage().isPresent() == true || getExtendedKeyUsage().isPresent() == true || getSubjectKeyIdentifier().isPresent() == true || getAuthorityKeyIdentifier().isPresent() == true || getSubjectAlternativeName().isPresent() == true || getIssuerAlternativeName().isPresent() == true || getSubjectDirectoryAttributes().isPresent() == true || getCrlDistributionPoints().isPresent() == true || getInhibitAnyPolicy().isPresent() == true || getPrivateKeyUsagePeriodNotBefore().isPresent() == true || getPrivateKeyUsagePeriodNotAfter().isPresent() == true || getCertificatePolicies().isPresent() == true || getPolicyMappings().isPresent() == true")
public interface X509v3ExtensionsObj {

    @JsonProperty("basic_constraints")
    @JsonPropertyDescription("Specifies a multi-valued extension which indicates whether a certificate is a CA certificate.")
    Optional<String> getBasicConstraints();

    @JsonProperty("name_constraints")
    @JsonPropertyDescription("Specifies a namespace within which all subject names in subsequent certificates in a certification path MUST be located.")
    Optional<String> getNameConstraints();

    @JsonProperty("policy_constraints")
    @JsonPropertyDescription("Specifies any constraints on path validation for certificates issued to CAs.")
    Optional<String> getPolicyConstraints();

    @JsonProperty("key_usage")
    @JsonPropertyDescription("Specifies a multi-valued extension consisting of a list of names of the permitted key usages.")
    Optional<String> getKeyUsage();

    @JsonProperty("extended_key_usage")
    @JsonPropertyDescription("Specifies a list of usages indicating purposes for which the certificate public key can be used for.")
    Optional<String> getExtendedKeyUsage();

    @JsonProperty("subject_key_identifier")
    @JsonPropertyDescription("Specifies the identifier that provides a means of identifying certificates that contain a particular public key.")
    Optional<String> getSubjectKeyIdentifier();

    @JsonProperty("authority_key_identifier")
    @JsonPropertyDescription("Specifies the identifier that provides a means of identifying the public key corresponding to the key used to sign a certificate.")
    Optional<String> getAuthorityKeyIdentifier();

    @JsonProperty("subject_alternative_name")
    @JsonPropertyDescription("Specifies the additional identities to be bound to the subject of the certificate.")
    Optional<String> getSubjectAlternativeName();

    @JsonProperty("issuer_alternative_name")
    @JsonPropertyDescription("Specifies the additional identities to be bound to the issuer of the certificate.")
    Optional<String> getIssuerAlternativeName();

    @JsonProperty("subject_directory_attributes")
    @JsonPropertyDescription("Specifies the identification attributes (e.g., nationality) of the subject.")
    Optional<String> getSubjectDirectoryAttributes();

    @JsonProperty("crl_distribution_points")
    @JsonPropertyDescription("Specifies how CRL information is obtained.")
    Optional<String> getCrlDistributionPoints();

    @JsonProperty("inhibit_any_policy")
    @JsonPropertyDescription("Specifies the number of additional certificates that may appear in the path before anyPolicy is no longer permitted.")
    Optional<String> getInhibitAnyPolicy();

    @JsonProperty("private_key_usage_period_not_before")
    @JsonPropertyDescription("Specifies the date on which the validity period begins for the key, if it is different from the validity period of the certificate.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    Optional<Instant> getPrivateKeyUsagePeriodNotBefore();

    @JsonProperty("private_key_usage_period_not_after")
    @JsonPropertyDescription("Specifies the date on which the validity period ends for the key, if it is different from the validity period of the certificate.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    Optional<Instant> getPrivateKeyUsagePeriodNotAfter();

    @JsonProperty("certificate_policies")
    @JsonPropertyDescription("Specifies a sequence of one or more policy information terms, each of which consists of an object identifier (OID) and optional qualifiers.")
    Optional<String> getCertificatePolicies();

    @JsonProperty("policy_mappings")
    @JsonPropertyDescription("Specifies one or more pairs of OIDs(); each pair includes an issuerDomainPolicy and a subjectDomainPolicy")
    Optional<String> getPolicyMappings();

}
