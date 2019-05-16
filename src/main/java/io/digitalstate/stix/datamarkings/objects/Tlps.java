package io.digitalstate.stix.datamarkings.objects;

import io.digitalstate.stix.common.StixInstant;
import io.digitalstate.stix.datamarkings.MarkingDefinition;

public class Tlps {

    public static final MarkingDefinition TLP_WHITE = Tlps.getTlpWhiteMD();
    public static final MarkingDefinition TLP_GREEN = Tlps.getTlpGreenMD();
    public static final MarkingDefinition TLP_AMBER = Tlps.getTlpAmberMD();
    public static final MarkingDefinition TLP_RED = Tlps.getTlpRedMD();

    public static final String TLP_WHITE_VALUE = "white";
    public static final String TLP_GREEN_VALUE = "green";
    public static final String TLP_AMBER_VALUE = "amber";
    public static final String TLP_RED_VALUE = "red";

    public static final String TLP_TYPE_VALUE = "tlp";

    
    /**
     * Factory methods to create the known types
     */
    private static MarkingDefinition getTlpWhiteMD() {
        MarkingDefinition.Builder builder = MarkingDefinition.builder()
                .id("marking-definition--613f2e26-407d-48c7-9eca-b8e91df99dc9")
                .definitionType(TLP_TYPE_VALUE)
                .created(StixInstant.parse("2017-01-20T00:00:00.000Z"))
                .definition(Tlp.builder()
                        .tlp(TLP_WHITE_VALUE)
                        .build());

        return builder.build();
    }

    private static MarkingDefinition getTlpGreenMD() {
        MarkingDefinition.Builder builder = MarkingDefinition.builder()
                .id("marking-definition--34098fce-860f-48ae-8e50-ebd3cc5e41da")
                .definitionType(TLP_TYPE_VALUE)
                .created(StixInstant.parse("2017-01-20T00:00:00.000Z"))
                .definition(Tlp.builder()
                        .tlp(TLP_GREEN_VALUE)
                        .build());

        return builder.build();
    }

    private static MarkingDefinition getTlpAmberMD() {
        MarkingDefinition.Builder builder = MarkingDefinition.builder()
                .id("marking-definition--f88d31f6-486f-44da-b317-01333bde0b82")
                .definitionType(TLP_TYPE_VALUE)
                .created(StixInstant.parse("2017-01-20T00:00:00.000Z"))
                .definition(Tlp.builder()
                        .tlp(TLP_AMBER_VALUE)
                        .build());

        return builder.build();
    }

    private static MarkingDefinition getTlpRedMD() {
        MarkingDefinition.Builder builder = MarkingDefinition.builder()
                .id("marking-definition--5e57c739-391a-4eb3-b6be-7d15ca92d5ed")
                .definitionType(TLP_TYPE_VALUE)
                .created(StixInstant.parse("2017-01-20T00:00:00.000Z"))
                .definition(Tlp.builder()
                        .tlp(TLP_RED_VALUE)
                        .build());

        return builder.build();
    }
}
