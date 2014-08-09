package controllers;

import play.mvc.*;
import play.mvc.Http.*;


/**
 * Verifies if user is Logged in by checking session
 * Source from: http://www.playframework.com/documentation/2.2.x/JavaGuide4
 */
public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("user");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.home());
    }
}