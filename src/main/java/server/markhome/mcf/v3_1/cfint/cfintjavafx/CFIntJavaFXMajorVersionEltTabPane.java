// Description: Java 25 JavaFX Element TabPane implementation for MajorVersion.

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
 *	CFIntJavaFXMajorVersionEltTabPane JavaFX Element TabPane implementation
 *	for MajorVersion.
 */
public class CFIntJavaFXMajorVersionEltTabPane
extends CFTabPane
implements ICFIntJavaFXMajorVersionPaneCommon
{
	protected ICFFormManager cfFormManager = null;
	protected ICFIntJavaFXSchema javafxSchema = null;
	protected boolean javafxIsInitializing = true;
	public final String LABEL_TabComponentsMinorVerList = "Optional Components Minor Version";
	protected CFTab tabComponentsMinorVer = null;
	protected CFBorderPane tabViewComponentsMinorVerListPane = null;

	public CFIntJavaFXMajorVersionEltTabPane( ICFFormManager formManager, ICFIntJavaFXSchema argSchema, ICFIntMajorVersionObj argFocus ) {
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
		setJavaFXFocusAsMajorVersion( argFocus );
		// Wire the newly constructed Panes/Tabs to this TabPane
		tabComponentsMinorVer = new CFTab();
		tabComponentsMinorVer.setText( LABEL_TabComponentsMinorVerList );
		tabComponentsMinorVer.setContent( getTabViewComponentsMinorVerListPane() );
		getTabs().add( tabComponentsMinorVer );
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
		if( ( value == null ) || ( value instanceof ICFIntMajorVersionObj ) ) {
			super.setJavaFXFocus( value );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				S_ProcName,
				"value",
				value,
				"ICFIntMajorVersionObj" );
		}
	}

	public void setJavaFXFocusAsMajorVersion( ICFIntMajorVersionObj value ) {
		setJavaFXFocus( value );
	}

	public ICFIntMajorVersionObj getJavaFXFocusAsMajorVersion() {
		return( (ICFIntMajorVersionObj)getJavaFXFocus() );
	}

	protected class RefreshComponentsMinorVerList
	implements ICFRefreshCallback
	{
		public RefreshComponentsMinorVerList() {
		}

		public void refreshMe() {
			Collection<ICFIntMinorVersionObj> dataCollection;
			ICFIntMajorVersionObj focus = (ICFIntMajorVersionObj)getJavaFXFocusAsMajorVersion();
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsMinorVer( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			CFBorderPane pane = getTabViewComponentsMinorVerListPane();
			ICFIntJavaFXMinorVersionPaneList jpList = (ICFIntJavaFXMinorVersionPaneList)pane;
			jpList.setJavaFXDataCollection( dataCollection );
		}
	}

	public CFBorderPane getTabViewComponentsMinorVerListPane() {
		if( tabViewComponentsMinorVerListPane == null ) {
			ICFIntMajorVersionObj focus = (ICFIntMajorVersionObj)getJavaFXFocusAsMajorVersion();
			Collection<ICFIntMinorVersionObj> dataCollection;
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsMinorVer( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			ICFIntMajorVersionObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFIntMajorVersionObj ) ) {
				javafxContainer = (ICFIntMajorVersionObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewComponentsMinorVerListPane = javafxSchema.getMinorVersionFactory().newListPane( cfFormManager, javafxContainer, null, dataCollection, new RefreshComponentsMinorVerList(), false );
		}
		return( tabViewComponentsMinorVerListPane );
	}

	public void setPaneMode( CFPane.PaneMode value ) {
		CFPane.PaneMode oldMode = getPaneMode();
		super.setPaneMode( value );
		if( tabViewComponentsMinorVerListPane != null ) {
			((ICFIntJavaFXMinorVersionPaneCommon)tabViewComponentsMinorVerListPane).setPaneMode( value );
		}
	}
}
