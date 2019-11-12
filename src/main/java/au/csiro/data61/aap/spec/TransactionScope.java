package au.csiro.data61.aap.spec;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import au.csiro.data61.aap.spec.types.SolidityAddress;
import au.csiro.data61.aap.spec.types.SolidityArray;
import au.csiro.data61.aap.spec.types.SolidityBytes;
import au.csiro.data61.aap.spec.types.SolidityInteger;
import au.csiro.data61.aap.spec.types.SolidityString;
import au.csiro.data61.aap.state.ProgramState;

/**
 * TransactionScope
 */
public class TransactionScope extends Scope {
    public static final Variable ANY = new Variable(SolidityString.DEFAULT_INSTANCE, "any",
            VariableCategory.SCOPE_VARIABLE, "any");
    public static final Set<Variable> DEFAULT_VARIABLES;

    private final Variable senders;
    private final Variable recipients;

    public TransactionScope(Variable senders, Variable recipients) {
        assert isValidAddressListVariable(senders);
        assert isValidAddressListVariable(recipients);
        this.recipients = recipients;
        this.senders = senders;
    }

    static {
        DEFAULT_VARIABLES = new HashSet<>();
        addVariable(DEFAULT_VARIABLES, SolidityBytes.DEFAULT_INSTANCE, "tx.blockHash");
        addVariable(DEFAULT_VARIABLES, SolidityInteger.DEFAULT_INSTANCE, "tx.blockNumber");
        addVariable(DEFAULT_VARIABLES, SolidityAddress.DEFAULT_INSTANCE, "tx.from");
        addVariable(DEFAULT_VARIABLES, SolidityInteger.DEFAULT_INSTANCE, "tx.gas");
        addVariable(DEFAULT_VARIABLES, SolidityInteger.DEFAULT_INSTANCE, "tx.gasPrice");
        addVariable(DEFAULT_VARIABLES, SolidityBytes.DEFAULT_INSTANCE, "tx.hash");
        addVariable(DEFAULT_VARIABLES, SolidityString.DEFAULT_INSTANCE, "tx.input");
        addVariable(DEFAULT_VARIABLES, SolidityInteger.DEFAULT_INSTANCE, "tx.nonce");
        addVariable(DEFAULT_VARIABLES, SolidityAddress.DEFAULT_INSTANCE, "tx.to");
        addVariable(DEFAULT_VARIABLES, SolidityInteger.DEFAULT_INSTANCE, "tx.transactionIndex");
        addVariable(DEFAULT_VARIABLES, SolidityInteger.DEFAULT_INSTANCE, "tx.value");
        addVariable(DEFAULT_VARIABLES, SolidityInteger.DEFAULT_INSTANCE, "tx.v");
        addVariable(DEFAULT_VARIABLES, SolidityInteger.DEFAULT_INSTANCE, "tx.r");
        addVariable(DEFAULT_VARIABLES, SolidityInteger.DEFAULT_INSTANCE, "tx.s");
    }

    public void setEnclosingScope(BlockScope enclosingScope) {
        super.setEnclosingScope(enclosingScope);
    }

    public Variable getSenders() {
        return senders;
    }

    public Variable getRecipients() {
        return recipients;
    }

    public static boolean isValidAddressListVariable(Variable variable) {
        return variable != null && (variable == ANY || (variable.getType() instanceof SolidityArray
                && SolidityBytes.DEFAULT_INSTANCE.castableFrom((SolidityArray) variable.getType())));
    }

    @Override
    public void execute(ProgramState state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream<Variable> defaultVariableStream() {
        return DEFAULT_VARIABLES.stream();
    }

    
}