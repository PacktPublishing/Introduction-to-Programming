package com.packt.javapath.ch07demo.pack01;

public class PublicClass01 {

    public static void main(String[] args){

        System.out.println(DefaultAccessInterface01.name);
        DefaultAccessClass01 o = new DefaultAccessClass01();

    }

    class DefaultAccessClass{
    }

    protected class ProtectedClass{
    }

    private class PrivateClass{
    }

    interface DefaultAccessInterface {
    }

    protected class ProtectedInterface{
    }

    private class PrivateInterface{
    }


}

