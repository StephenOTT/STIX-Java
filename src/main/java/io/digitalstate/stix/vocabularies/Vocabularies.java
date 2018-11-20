package io.digitalstate.stix.vocabularies;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Vocabularies {

    private static Set<String> attackMotivation = new HashSet<>(Arrays.asList(
            "accidental", "coercion", "dominance",
            "ideology", "notoriety", "organizational-gain",
            "personal-gain", "personal-satisfaction", "revenge",
            "unpredictable"));

    private static Set<String> attackResourceLevel = new HashSet<>(Arrays.asList(
            "individual", "club", "content",
            "team", "organization", "government"));

    private static Set<String> IdentityClass = new HashSet<>(Arrays.asList(
            "individual", "group", "organization",
            "class", "unknown"));

    private static Set<String> IndicatorLabel = new HashSet<>(Arrays.asList(
            "anomalous-activity", "anonymization", "benign",
            "compromised", "malicious-activity", "attribution"));

    private static Set<String> malwareLabel = new HashSet<>(Arrays.asList(
            "adware", "backdoor", "bot",
            "ddos", "dropper", "exploit-kit",
            "keylogger", "ransomware", "remote-access-trojan",
            "resource-exploitation", "rogue-security-software", "rootkit",
            "screen-capture", "spyware", "trojan",
            "virus", "worm"));

    private static Set<String> reportLabel = new HashSet<>(Arrays.asList(
            "threat-report", "attack-pattern", "campaign",
            "identity", "indicator", "malware",
            "observed-data", "threat-actor", "tool",
            "vulnerability"));

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

    private static Set<String> threatActorLabel = new HashSet<>(Arrays.asList(
            "activist", "competitor", "crime-syndicate",
            "criminal", "hacker", "insider-accidental",
            "insider-disgruntled", "nation-state", "sensationalist",
            "spy", "terrorist"));

    private static Set<String> threatActorRole = new HashSet<>(Arrays.asList(
            "agent", "director", "independent",
            "infrastructure-architect", "infrastructure-operator", "malware-author",
            "sponsor"));

    private static Set<String> threatActorSophistication = new HashSet<>(Arrays.asList(
            "none", "minimal", "intermediate",
            "advanced", "expert", "innovator",
            "strategic"));

    private static Set<String> toolLabel = new HashSet<>(Arrays.asList(
            "denial-of-service", "exploitation", "information-gathering",
            "network-capture", "credential-exploitation", "remote-access",
            "vulnerability-scanning"));

    private static Set<String> tlpLevels = new HashSet<>(Arrays.asList(
            "white", "green", "amber", "red"));


    ///
    //Setters and Getters
    //

    public static Set<String> getAttackMotivation() {
        return attackMotivation;
    }

    public static void setAttackMotivation(Set<String> attackMotivation) {
        Vocabularies.attackMotivation = attackMotivation;
    }

    public static Set<String> getAttackResourceLevel() {
        return attackResourceLevel;
    }

    public static void setAttackResourceLevel(Set<String> attackResourceLevel) {
        Vocabularies.attackResourceLevel = attackResourceLevel;
    }

    public static Set<String> getIdentityClass() {
        return IdentityClass;
    }

    public static void setIdentityClass(Set<String> identityClass) {
        IdentityClass = identityClass;
    }

    public static Set<String> getIndicatorLabel() {
        return IndicatorLabel;
    }

    public static void setIndicatorLabel(Set<String> indicatorLabel) {
        IndicatorLabel = indicatorLabel;
    }

    public static Set<String> getMalwareLabel() {
        return malwareLabel;
    }

    public static void setMalwareLabel(Set<String> malwareLabel) {
        Vocabularies.malwareLabel = malwareLabel;
    }

    public static Set<String> getReportLabel() {
        return reportLabel;
    }

    public static void setReportLabel(Set<String> reportLabel) {
        Vocabularies.reportLabel = reportLabel;
    }

    public static Set<String> getIndustrySectors() {
        return industrySectors;
    }

    public static void setIndustrySectors(Set<String> sectors) {
        Vocabularies.industrySectors = sectors;
    }

    public static Set<String> getThreatActorLabel() {
        return threatActorLabel;
    }

    public static void setThreatActorLabel(Set<String> threatActorLabel) {
        Vocabularies.threatActorLabel = threatActorLabel;
    }

    public static Set<String> getThreatActorRole() {
        return threatActorRole;
    }

    public static void setThreatActorRole(Set<String> threatActorRole) {
        Vocabularies.threatActorRole = threatActorRole;
    }

    public static Set<String> getThreatActorSophistication() {
        return threatActorSophistication;
    }

    public static void setThreatActorSophistication(Set<String> threatActorSophistication) {
        Vocabularies.threatActorSophistication = threatActorSophistication;
    }

    public static Set<String> getToolLabel() {
        return toolLabel;
    }

    public static void setToolLabel(Set<String> toolLabel) {
        Vocabularies.toolLabel = toolLabel;
    }


    public static Set<String> getTlpLevels() {
        return tlpLevels;
    }

    public static void setTlpLevels(Set<String> tlpLevels) {
        Vocabularies.tlpLevels = tlpLevels;
    }
}

