package com.lab1.print.provider;

/**
 * NameProvider is an interface that provides the name of a node in a tree.
 */
public interface NameProvider<T> {
    String getName(T node);
}
