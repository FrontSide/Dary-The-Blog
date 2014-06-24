
package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.libs.F.*;

import static play.mvc.Results.*;

import views.html.error.*;

/* Global Controller Extension */
public class Global extends GlobalSettings {

    /* Not Found 404 */
    @Override
    public Promise<Result> onHandlerNotFound(RequestHeader req) {
        return Promise.<Result>pure(notFound(errorpage.render(req)));
    }

}