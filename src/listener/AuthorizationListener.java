/**
 * 
 */
package listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Fernando Costa Migliorini
 * @email fercosmig@gmail.com
 * @since Dec 27, 2019
 * @file listener.Listener.java
 */
public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(AuthorizationListener.class.getName());

	/**
	 * Methods
	 */
	@Override
	public void afterPhase(PhaseEvent event) {
		LOGGER.info("method afterPhase");

		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getViewRoot().getViewId().equals("/index.xhtml")) {
			return;
		}

		boolean isLogged = context.getExternalContext().getSessionMap().get("isLogged") != null;

		if (!isLogged) {
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "index?faces-redirect=true");
			context.renderResponse();
		} else {
			LOGGER.info("method afterPhase, Session isLogged = true");
		}

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		LOGGER.info("method getPhaseId");
		return PhaseId.RESTORE_VIEW;
	}

}
