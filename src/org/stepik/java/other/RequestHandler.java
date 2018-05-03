package org.stepik.java.other;

/**
 * It represents a handler and has two methods: one for handling requests and other for combining handlers
 */
@FunctionalInterface
public interface RequestHandler {

    // !!! write a method handle that accept request and returns new request here
    // it allows to use lambda expressions for creating handlers below
    Request handle(Request req);

    // !!! write a default method for combining this and other handler single one
    // the order of execution may be any but you need to consider it when composing handlers
    // the method may has any name
    default RequestHandler accept(RequestHandler handler) {
        return (request) -> handle(handler.handle(request));
    }
}