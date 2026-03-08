// Description: Java 25 JavaFX Element TabPane implementation for TopProject.

/*
 *	server.markhome.mcf.CFInt
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFInt - Internet Essentials
 *	
 *	This file is part of Mark's Code Fractal CFInt.
 *	
 *	Mark's Code Fractal CFInt is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU Library General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFInt is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU Library General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFInt is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU Library General Public License
 *	along with Mark's Code Fractal CFInt.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes in order to
 *	tie it to proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfint.cfintjavafx;

import java.math.*;
import java.time.*;
import java.text.*;
import java.util.*;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.inz.Inz;
import server.markhome.mcf.v3_1.cflib.javafx.*;
import org.apache.commons.codec.binary.Base64;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;

/**
 *	CFIntJavaFXTopProjectEltTabPane JavaFX Element TabPane implementation
 *	for TopProject.
 */
public class CFIntJavaFXTopProjectEltTabPane
extends CFTabPane
implements ICFIntJavaFXTopProjectPaneCommon
{
	protected ICFFormManager cfFormManager = null;
	protected ICFIntJavaFXSchema javafxSchema = null;
	protected boolean javafxIsInitializing = true;
	public final String LABEL_TabComponentsSubProjectList = "Optional Components Sub Project";
	protected CFTab tabComponentsSubProject = null;
	protected CFBorderPane tabViewComponentsSubProjectListPane = null;

	public CFIntJavaFXTopProjectEltTabPane( ICFFormManager formManager, ICFIntJavaFXSchema argSchema, ICFIntTopProjectObj argFocus ) {
		super();
		final String S_ProcName = "construct-schema-focus";
		if( formManager == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				"formManager" );
		}
		cfFormManager = formManager;
		if( argSchema == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				2,
				"argSchema" );
		}
		// argFocus is optional; focus may be set later during execution as
		// conditions of the runtime change.
		javafxSchema = argSchema;
		setJavaFXFocusAsTopProject( argFocus );
		// Wire the newly constructed Panes/Tabs to this TabPane
		tabComponentsSubProject = new CFTab();
		tabComponentsSubProject.setText( LABEL_TabComponentsSubProjectList );
		tabComponentsSubProject.setContent( getTabViewComponentsSubProjectListPane() );
		getTabs().add( tabComponentsSubProject );
		javafxIsInitializing = false;
	}

	public ICFFormManager getCFFormManager() {
		return( cfFormManager );
	}

	public void setCFFormManager( ICFFormManager value ) {
		final String S_ProcName = "setCFFormManager";
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				"value" );
		}
		cfFormManager = value;
	}

	public ICFIntJavaFXSchema getJavaFXSchema() {
		return( javafxSchema );
	}

	public void setJavaFXFocus( ICFLibAnyObj value ) {
		final String S_ProcName = "setJavaFXFocus";
		if( ( value == null ) || ( value instanceof ICFIntTopProjectObj ) ) {
			super.setJavaFXFocus( value );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				S_ProcName,
				"value",
				value,
				"ICFIntTopProjectObj" );
		}
	}

	public void setJavaFXFocusAsTopProject( ICFIntTopProjectObj value ) {
		setJavaFXFocus( value );
	}

	public ICFIntTopProjectObj getJavaFXFocusAsTopProject() {
		return( (ICFIntTopProjectObj)getJavaFXFocus() );
	}

	protected class RefreshComponentsSubProjectList
	implements ICFRefreshCallback
	{
		public RefreshComponentsSubProjectList() {
		}

		public void refreshMe() {
			Collection<ICFIntSubProjectObj> dataCollection;
			ICFIntTopProjectObj focus = (ICFIntTopProjectObj)getJavaFXFocusAsTopProject();
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsSubProject( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			CFBorderPane pane = getTabViewComponentsSubProjectListPane();
			ICFIntJavaFXSubProjectPaneList jpList = (ICFIntJavaFXSubProjectPaneList)pane;
			jpList.setJavaFXDataCollection( dataCollection );
		}
	}

	public CFBorderPane getTabViewComponentsSubProjectListPane() {
		if( tabViewComponentsSubProjectListPane == null ) {
			ICFIntTopProjectObj focus = (ICFIntTopProjectObj)getJavaFXFocusAsTopProject();
			Collection<ICFIntSubProjectObj> dataCollection;
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsSubProject( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			ICFIntTopProjectObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFIntTopProjectObj ) ) {
				javafxContainer = (ICFIntTopProjectObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewComponentsSubProjectListPane = javafxSchema.getSubProjectFactory().newListPane( cfFormManager, javafxContainer, null, dataCollection, new RefreshComponentsSubProjectList(), false );
		}
		return( tabViewComponentsSubProjectListPane );
	}

	public void setPaneMode( CFPane.PaneMode value ) {
		CFPane.PaneMode oldMode = getPaneMode();
		super.setPaneMode( value );
		if( tabViewComponentsSubProjectListPane != null ) {
			((ICFIntJavaFXSubProjectPaneCommon)tabViewComponentsSubProjectListPane).setPaneMode( value );
		}
	}
}
