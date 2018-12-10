package io.digitalstate.stix.validation.sequences;

import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({DefaultValuesProcessor.class, Default.class})
public interface SequenceDefault {
}
