package fr.umontpellier.bettersncf;

public interface Predicate<T>
{
    public abstract boolean accept(T value);
}
