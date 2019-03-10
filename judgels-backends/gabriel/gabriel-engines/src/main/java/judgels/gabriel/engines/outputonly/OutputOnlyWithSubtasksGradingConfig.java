package judgels.gabriel.engines.outputonly;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Optional;
import judgels.gabriel.engines.SingleSourceFileWithSubtasksGradingConfig;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableOutputOnlyWithSubtasksGradingConfig.class)
public interface OutputOnlyWithSubtasksGradingConfig extends SingleSourceFileWithSubtasksGradingConfig {
    Optional<String> getCustomScorer();

    @Override
    default int getTimeLimit() {
        return 0;
    }

    @Override
    default int getMemoryLimit() {
        return 0;
    }

    class Builder extends ImmutableOutputOnlyWithSubtasksGradingConfig.Builder {}
}
