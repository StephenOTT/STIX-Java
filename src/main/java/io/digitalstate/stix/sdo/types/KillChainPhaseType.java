package io.digitalstate.stix.sdo.types;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.immutables.serial.Serial;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.digitalstate.stix.validation.GenericValidation;

/**
 * kill-chain-phase
 * <p>
 * The kill-chain-phase represents a phase in a kill chain.
 */
@Value.Immutable @Serial.Version(1L)
@Value.Style(typeAbstract="*Type", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, depluralize = true)
@JsonSerialize(as = KillChainPhase.class) @JsonDeserialize(builder = KillChainPhase.Builder.class)
@JsonPropertyOrder({
    "kill_chain_name",
    "phase_name"
})
public interface KillChainPhaseType extends GenericValidation, Serializable {

    @NotBlank
    @JsonProperty("kill_chain_name")
    @JsonPropertyDescription("The name of the kill chain.")
    String killChainName();

    @NotBlank
    @JsonProperty("phase_name")
    @JsonPropertyDescription("The name of the phase in the kill chain.")
    String phaseName();

    
    /**
     * Create an Enumeration of the most common one:  Lockheed-Martin
     */
    //@TODO Convert to Vocab pattern
    public enum LockheedMartinKillChain {
    	RECONNAISSANCE("reconnaissance"),
    	WEAPONIZATION("weaponization"),
    	DELIVERY("delivery"),
    	EXPLOITATION("exploitation"),
    	INSTALLATION("installation"),
    	COMMAND_AND_CONTROL("command-and-control"),
    	ACTIONS_ON_OBJECTIVE("actions-on-objective");
    	
    	public static final String killChainName = "lockheed-martin-cyber-kill-chain";
    	
    	String phase;

    	LockheedMartinKillChain(String val) {
    		this.phase = val;
    	}
    	
    	@Override
    	public String toString() {return phase;}
    	
    	@JsonValue
    	public String getPhase() { return phase; }
    }
    
}
