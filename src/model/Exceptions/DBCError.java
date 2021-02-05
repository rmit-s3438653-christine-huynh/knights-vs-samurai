package model.Exceptions;

public class DBCError {

    private static final String preConditionErrorMessage = "Pre-condition Error: Arguments did not meet pre-conditions in %s method";
    private static final String postConditionErrorMessage = "Post-condition Error: Results did not meet post-conditions in %s method";
    private static final String invariantErrorMessage = "Invariant Error: Class invariant %s violated";

    public static String getPreConditionErrorMessage(String currentMethodName) {
        return String.format(preConditionErrorMessage, currentMethodName);
    }


    public static String getPostConditionErrorMessage(String currentMethodName) {
        return String.format(postConditionErrorMessage, currentMethodName);
    }

    public static String getInvariantErrorMessage(String invariantName) {
        return String.format(invariantErrorMessage, invariantName);
    }
}
