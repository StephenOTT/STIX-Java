package io.digitalstate.stix.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Vocabularies {

    @JsonProperty("attack_motivations")
    private static Set<String> attackMotivations = new HashSet<>(Arrays.asList(
            "accidental", "coercion", "dominance",
            "ideology", "notoriety", "organizational-gain",
            "personal-gain", "personal-satisfaction", "revenge",
            "unpredictable"));

    @JsonProperty("attack_resource_levels")
    private static Set<String> attackResourceLevels = new HashSet<>(Arrays.asList(
            "individual", "club", "content",
            "team", "organization", "government"));

    @JsonProperty("identity_classes")
    private static Set<String> IdentityClasses = new HashSet<>(Arrays.asList(
            "individual", "group", "organization",
            "class", "unknown"));

    @JsonProperty("indiator_labels")
    private static Set<String> IndicatorLabels = new HashSet<>(Arrays.asList(
            "anomalous-activity", "anonymization", "benign",
            "compromised", "malicious-activity", "attribution"));

    @JsonProperty("malware_labels")
    private static Set<String> malwareLabels = new HashSet<>(Arrays.asList(
            "adware", "backdoor", "bot",
            "ddos", "dropper", "exploit-kit",
            "keylogger", "ransomware", "remote-access-trojan",
            "resource-exploitation", "rogue-security-software", "rootkit",
            "screen-capture", "spyware", "trojan",
            "virus", "worm"));

    @JsonProperty("report_labels")
    private static Set<String> reportLabels = new HashSet<>(Arrays.asList(
            "threat-report", "attack-pattern", "campaign",
            "identity", "indicator", "malware",
            "observed-data", "threat-actor", "tool",
            "vulnerability"));

    @JsonProperty("industry_sectors")
    private static Set<String> industrySectors = new HashSet<>(Arrays.asList(
            "agriculture", "aerospace", "automotive",
            "communications", "construction", "defence",
            "education", "energy", "entertainment",
            "financial-services", "government-national", "government-regional",
            "government-local", "government-public-services", "healthcare",
            "hospitality-leisure", "infrastructure", "insurance",
            "manufacturing", "mining", "non-profit",
            "pharmaceuticals", "retail", "technology",
            "telecommunications", "transportation", "utilities"));

    @JsonProperty("threat_actor_labels")
    private static Set<String> threatActorLabels = new HashSet<>(Arrays.asList(
            "activist", "competitor", "crime-syndicate",
            "criminal", "hacker", "insider-accidental",
            "insider-disgruntled", "nation-state", "sensationalist",
            "spy", "terrorist"));

    @JsonProperty("threat_actor_roles")
    private static Set<String> threatActorRoles = new HashSet<>(Arrays.asList(
            "agent", "director", "independent",
            "infrastructure-architect", "infrastructure-operator", "malware-author",
            "sponsor"));

    @JsonProperty("threat_actor_sophistication")
    private static Set<String> threatActorSophisticationLevels = new HashSet<>(Arrays.asList(
            "none", "minimal", "intermediate",
            "advanced", "expert", "innovator",
            "strategic"));

    @JsonProperty("tool_labels")
    private static Set<String> toolLabels = new HashSet<>(Arrays.asList(
            "denial-of-service", "exploitation", "information-gathering",
            "network-capture", "credential-exploitation", "remote-access",
            "vulnerability-scanning"));

    @JsonProperty("tlp_levels")
    private static Set<String> tlpLevels = new HashSet<>(Arrays.asList(
            "white", "green", "amber", "red"));


    ///
    //Setters and Getters
    //

    public static Set<String> getAttackMotivations() {
        return attackMotivations;
    }

    public static void setAttackMotivations(Set<String> attackMotivations) {
        Vocabularies.attackMotivations = attackMotivations;
    }

    public static Set<String> getAttackResourceLevels() {
        return attackResourceLevels;
    }

    public static void setAttackResourceLevels(Set<String> attackResourceLevels) {
        Vocabularies.attackResourceLevels = attackResourceLevels;
    }

    public static Set<String> getIdentityClasses() {
        return IdentityClasses;
    }

    public static void setIdentityClasses(Set<String> identityClasses) {
        IdentityClasses = identityClasses;
    }

    public static Set<String> getIndicatorLabels() {
        return IndicatorLabels;
    }

    public static void setIndicatorLabels(Set<String> indicatorLabels) {
        IndicatorLabels = indicatorLabels;
    }

    public static Set<String> getMalwareLabels() {
        return malwareLabels;
    }

    public static void setMalwareLabels(Set<String> malwareLabels) {
        Vocabularies.malwareLabels = malwareLabels;
    }

    public static Set<String> getReportLabels() {
        return reportLabels;
    }

    public static void setReportLabels(Set<String> reportLabels) {
        Vocabularies.reportLabels = reportLabels;
    }

    public static Set<String> getIndustrySectors() {
        return industrySectors;
    }

    public static void setIndustrySectors(Set<String> sectors) {
        Vocabularies.industrySectors = sectors;
    }

    public static Set<String> getThreatActorLabels() {
        return threatActorLabels;
    }

    public static void setThreatActorLabels(Set<String> threatActorLabels) {
        Vocabularies.threatActorLabels = threatActorLabels;
    }

    public static Set<String> getThreatActorRoles() {
        return threatActorRoles;
    }

    public static void setThreatActorRoles(Set<String> threatActorRoles) {
        Vocabularies.threatActorRoles = threatActorRoles;
    }

    public static Set<String> getThreatActorSophisticationLevels() {
        return threatActorSophisticationLevels;
    }

    public static void setThreatActorSophisticationLevels(Set<String> threatActorSophisticationLevels) {
        Vocabularies.threatActorSophisticationLevels = threatActorSophisticationLevels;
    }

    public static Set<String> getToolLabels() {
        return toolLabels;
    }

    public static void setToolLabels(Set<String> toolLabels) {
        Vocabularies.toolLabels = toolLabels;
    }


    public static Set<String> getTlpLevels() {
        return tlpLevels;
    }

    public static void setTlpLevels(Set<String> tlpLevels) {
        Vocabularies.tlpLevels = tlpLevels;
    }
}

