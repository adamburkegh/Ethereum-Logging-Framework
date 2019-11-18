package au.csiro.data61.aap.etl;

/**
 * ValueGetter
 */
@FunctionalInterface
public interface ValueAccessor {
    public Object getValue(EtlState state) throws EtlException;
    
    public static ValueAccessor createLiteralAccessor(Object value) {
        return (state) -> value;
    }

    public static ValueAccessor createVariableAccessor(String name) {
        assert name != null;
        return state -> state.getValueStore().getValue(name);
    }
}