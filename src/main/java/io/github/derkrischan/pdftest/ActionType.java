package io.github.derkrischan.pdftest;

import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionEmbeddedGoTo;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionHide;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionImportData;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionLaunch;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionMovie;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionNamed;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionRemoteGoTo;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionResetForm;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionSound;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionSubmitForm;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionThread;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;

/**
 * Supported action types in PDF documents to check for.
 * 
 * @author krischan
 *
 */
public enum ActionType {

	/** an embedded go-to action that can be executed within a PDF document */
	EMBEDDED_GOTO(PDActionEmbeddedGoTo.class),
	/** a go-to action that can be executed in a PDF document */
	GOTO(PDActionGoTo.class),
	/** a hide action */
	HIDE(PDActionHide.class),
	/** an date import action */
	IMPORT_DATA(PDActionImportData.class),
	/** a javascript action */
	JAVASCRIPT(PDActionJavaScript.class),
	/** a launch action */
	LAUNCH(PDActionLaunch.class),
	/** a movie action */
	MOVIE(PDActionMovie.class),
	/** a named action */
	NAMED(PDActionNamed.class),
	/** a remote go-to action */
	REMOTE_GOTO(PDActionRemoteGoTo.class),
	/** a form reset action */
	RESET_FORM(PDActionResetForm.class),
	/** a sound action */
	SOUND(PDActionSound.class),
	/** a form submit action */
	SUBMIT_FORM(PDActionSubmitForm.class),
	/** a thread action */
	THREAD(PDActionThread.class),
	/** a URI action */
	URI(PDActionURI.class);

	private Class<? extends PDAction> pdActionClass;

	private ActionType(Class<? extends PDAction> pdActionClass) {
		this.pdActionClass = pdActionClass;
	}

	/**
	 * Returns the corresponding PDFBox PDAction class.
	 * 
	 * @return corresponding PDAction class
	 */
	public Class<? extends PDAction> getPDActionClass() {
		return pdActionClass;
	}

}
