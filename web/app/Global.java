import play.*;
import play.mvc.*;

import static play.mvc.Results.*;

/**
 * @author Sami Hostikka
 */
public class Global extends GlobalSettings {
	@Override
	public Result onError(play.mvc.Http.RequestHeader arg0, Throwable arg1) {
		return internalServerError(views.html.notFound.render());
	};

	@Override
	public Result onHandlerNotFound(play.mvc.Http.RequestHeader arg0) {
		return notFound(views.html.notFound.render());
	};

	@Override
	public Result onBadRequest(play.mvc.Http.RequestHeader arg0, String arg1) {
		return badRequest(views.html.notFound.render());
	};
}
