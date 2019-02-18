package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.coo.types.X509v3ExtensionsObj;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.validation.contraints.businessrule.BusinessRule;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.hashingvocab.HashingVocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabulary.vocabularies.HashingAlgorithms;
import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * x509-certificate
 * <p>
 * The X509 Certificate Object represents the properties of an X.509 certificate.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "x509-certificate", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true, depluralizeDictionary = {"hash:hashes"})
@JsonTypeName("x509-certificate")
@JsonSerialize(as = X509Certificate.class) @JsonDeserialize(builder = X509Certificate.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "type", "extensions", "is_self_signed", "hashes", "version", "serial_number",
        "signature_algorithm", "issuer", "validity_not_before", "validity_not_after",
        "subject", "subject_public_key_algorithm", "subject_public_key_modulus",
        "subject_public_key_exponent", "x509_v3_extensions"
})
//@TODO refactor BusinessRule annotation with custom emthod that looks up every field and does a check if there is a value
@BusinessRule(ifExp = "true", thenExp = "isSelfSigned().isPresent() == true || getHashes().isEmpty() == false || getVersion().isPresent() == true || getSerialNumber().isPresent() == true || getSignatureAlgorithm().isPresent() == true || getIssuer().isPresent() == true || getValidityNotBefore().isPresent() == true || getValidityNotAfter().isPresent() == true || getSubject().isPresent() == true || getSubjectPublicKeyAlgorithm().isPresent() == true || getSubjectPublicKeyModulus().isPresent() == true || getSubjectPublicKeyExponent().isPresent() == true || getX509V3Extensions().isPresent() == true")
public interface X509CertificateCoo extends CyberObservableObject {

    @JsonProperty("is_self_signed")
    @JsonPropertyDescription("Specifies whether the certificate is self-signed, i.e., whether it is signed by the same entity whose identity it certifies.")
    Optional<Boolean> isSelfSigned();

    @JsonProperty("hashes")
    @JsonPropertyDescription("Specifies any hashes that were calculated for the entire contents of the certificate.")
    Map<@Length(min = 3, max = 256) @HashingVocab(HashingAlgorithms.class) String, String> getHashes();

    @JsonProperty("version")
    @JsonPropertyDescription("Specifies the version of the encoded certificate.")
    Optional<String> getVersion();

    @JsonProperty("serial_number")
    @JsonPropertyDescription("Specifies the unique identifier for the certificate, as issued by a specific Certificate Authority.")
    Optional<String> getSerialNumber();

    @JsonProperty("signature_algorithm")
    @JsonPropertyDescription("Specifies the name of the algorithm used to sign the certificate.")
    Optional<String> getSignatureAlgorithm();

    @JsonProperty("issuer")
    @JsonPropertyDescription("Specifies the name of the Certificate Authority that issued the certificate.")
    Optional<String> getIssuer();

    @JsonProperty("validity_not_before")
    @JsonPropertyDescription("Specifies the date on which the certificate validity period begins.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    Optional<Instant> getValidityNotBefore();

    @JsonProperty("validity_not_after")
    @JsonPropertyDescription("Specifies the date on which the certificate validity period ends.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    Optional<Instant> getValidityNotAfter();

    @JsonProperty("subject")
    @JsonPropertyDescription("Specifies the name of the entity associated with the public key stored in the subject public key field of the certificate.")
    Optional<String> getSubject();

    @JsonProperty("subject_public_key_algorithm")
    @JsonPropertyDescription("Specifies the name of the algorithm with which to encrypt data being sent to the subject.")
    Optional<String> getSubjectPublicKeyAlgorithm();

    @JsonProperty("subject_public_key_modulus")
    @JsonPropertyDescription("Specifies the modulus portion of the subject\u2019s public RSA key.")
    Optional<String> getSubjectPublicKeyModulus();

    @JsonProperty("subject_public_key_exponent")
    @JsonPropertyDescription("Specifies the exponent portion of the subject\u2019s public RSA key, as an integer.")
    Optional<Long> getSubjectPublicKeyExponent();

    @JsonProperty("x509_v3_extensions")
    @JsonPropertyDescription("Specifies any standard X.509 v3 extensions that may be used in the certificate.")
    Optional<X509v3ExtensionsObj> getX509V3Extensions();

}
