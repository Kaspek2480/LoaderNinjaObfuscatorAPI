package com.loader.ninja;

public class CallMethodSpy {

    public static void test() {
        System.out.println("XD");
//        String name = Reflection.getCallerClass(2).getName();
//        System.out.println(name);
    }

    public static void main(String[] args) {
        test();
    }
/*
    private static class SecurityManagerMethod {
        public String getCallerClassName(int callStackDepth) {
            return mySecurityManager.getCallerClassName(callStackDepth);
        }

        public String getMethodName() {
            return "SecurityManager";
        }

        *//**
         * A custom security manager that exposes the getClassContext() information
         *//*
        static class MySecurityManager extends SecurityManager {
            public String getCallerClassName(int callStackDepth) {
                return getClassContext()[callStackDepth].getName();
            }
        }

        private final static MySecurityManager mySecurityManager =
                new MySecurityManager();
    }*/

    static class MySecurityManager extends SecurityManager {
        public String getCallerClassName(int callStackDepth) {
            return getClassContext()[callStackDepth].getName();
        }
    }

    public static String getCallerMethod() {
        MySecurityManager manager = new MySecurityManager();
        String caller;
        int start = 1;
        while (start < 5) {
            String callerClassName = manager.getCallerClassName(start);
            if (!callerClassName.contains("com.loader.ninja.CallMethodSpy")) {
                return callerClassName;
            }
            start++;
        }
        return "Unknown";
    }
}
