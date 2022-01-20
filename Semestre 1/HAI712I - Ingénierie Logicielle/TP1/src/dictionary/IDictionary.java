package dictionary;

public interface IDictionary
{
	public abstract Object get(Object key);
	public abstract IDictionary put(Object key, Object value);
	public abstract boolean isEmpty();
	public abstract boolean containsKey(Object key);
	public abstract int size();
}
