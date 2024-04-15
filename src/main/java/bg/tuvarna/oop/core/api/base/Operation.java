package bg.tuvarna.oop.core.api.base;

public interface Operation<I extends OperationInput, R extends OperationResult> {

    R process(I getInput);
}