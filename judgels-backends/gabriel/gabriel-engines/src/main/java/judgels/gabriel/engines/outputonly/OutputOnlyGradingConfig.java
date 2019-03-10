package judgels.gabriel.engines.outputonly;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import judgels.gabriel.engines.SingleSourceFileWithoutSubtasksGradingConfig;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableOutputOnlyGradingConfig.class)
public interface OutputOnlyGradingConfig extends SingleSourceFileWithoutSubtasksGradingConfig {
    Optional<String> getCustomScorer();

    @Override
    default Map<String, String> getSourceFileFields() {
        return ImmutableMap.of("source", "Output files (.zip)");
    }

    @Override
    default int getTimeLimit() {
        return 0;
    }

    @Override
    default int getMemoryLimit() {
        return 0;
    }

    class Builder extends ImmutableOutputOnlyGradingConfig.Builder {}
}
