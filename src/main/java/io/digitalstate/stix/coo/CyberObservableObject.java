package io.digitalstate.stix.coo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.digitalstate.stix.common.StixCustomProperties;
import io.digitalstate.stix.coo.objects.Artifact;
import io.digitalstate.stix.coo.objects.AutonomousSystem;
import io.digitalstate.stix.coo.objects.Directory;
import io.digitalstate.stix.coo.objects.DomainName;
import io.digitalstate.stix.coo.objects.EmailAddress;
import io.digitalstate.stix.coo.objects.EmailMessage;
import io.digitalstate.stix.coo.objects.File;
import io.digitalstate.stix.coo.objects.Ipv4Address;
import io.digitalstate.stix.coo.objects.Ipv6Address;
import io.digitalstate.stix.coo.objects.MacAddress;
import io.digitalstate.stix.coo.objects.Mutex;
import io.digitalstate.stix.coo.objects.NetworkTraffic;
import io.digitalstate.stix.coo.objects.Software;
import io.digitalstate.stix.coo.objects.Url;
import io.digitalstate.stix.coo.objects.UserAccount;
import io.digitalstate.stix.coo.objects.WindowsRegistryKey;
import io.digitalstate.stix.coo.objects.X509Certificate;
import io.digitalstate.stix.validation.GenericValidation;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY)

@JsonSubTypes({
	@Type(value = Artifact.class, name="attack-pattern"),
	@Type(value = AutonomousSystem.class, name = "autonomous-system"),
	@Type(value = Directory.class, name = "directory"),
	@Type(value = DomainName.class, name = "domain-name"),
	@Type(value = EmailAddress.class, name = "email-addr"),
	@Type(value = EmailMessage.class, name = "email-message"),
	@Type(value = File.class, name = "file"),
	@Type(value = Ipv4Address.class, name = "ipv4-addr"),
	@Type(value = Ipv6Address.class, name = "ipv6-addr"),
	@Type(value = MacAddress.class, name = "mac-addr"),
	@Type(value = Mutex.class, name = "mutex"),
	@Type(value = NetworkTraffic.class, name = "network-traffic"),
	@Type(value = Process.class, name = "process"),
	@Type(value = Software.class, name = "software"),
	@Type(value = Url.class, name = "url"),
	@Type(value = UserAccount.class, name = "user-account"),
	@Type(value = WindowsRegistryKey.class, name = "windows-registry-key"),
	@Type(value = X509Certificate.class, name = "x509-certificate")
})
public interface CyberObservableObject extends Serializable,
        GenericValidation,
        CyberObservableObjectCommonProperties,
        StixCustomProperties {

}
