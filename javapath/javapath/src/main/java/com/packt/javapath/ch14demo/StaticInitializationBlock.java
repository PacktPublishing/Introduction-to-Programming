package com.packt.javapath.ch14demo;

import java.util.HashSet;
import java.util.Set;

public interface StaticInitializationBlock {
    Set<String> set = new HashSet<>()
    { {
        set.add("That is ");
        set.add("another string");
    }};
}
