package judgels.gabriel.api;

import org.immutables.value.Value;

@Value.Immutable
public interface SubtaskRawResult {
    Verdict getVerdict();
    double getScore();

    class Builder extends ImmutableSubtaskRawResult.Builder {}
}
