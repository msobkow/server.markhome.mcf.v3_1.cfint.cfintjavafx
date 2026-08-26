// Description: Java 25 JavaFX List of Obj Pane implementation for URLProtocol.

/*
 *	server.markhome.mcf.CFInt
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFInt - Internet Essentials
 *	
 *	This file is part of Mark's Code Fractal CFInt.
 *	
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *	
 *	http://www.apache.org/licenses/LICENSE-2.0
 *	
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 *	
 */

package server.markhome.mcf.v3_1.cfint.cfintjavafx;

import java.math.*;
import java.time.*;
import java.util.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.inz.Inz;
import server.markhome.mcf.v3_1.cflib.javafx.*;
import org.apache.commons.codec.binary.Base64;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecjavafx.*;

/**
 *	CFIntJavaFXURLProtocolListPane JavaFX List of Obj Pane implementation
 *	for URLProtocol.
 */
public class CFIntJavaFXURLProtocolListPane
extends CFBorderPane
implements ICFIntJavaFXURLProtocolPaneList
{
	public static String S_FormName = "List URL Protocol";
	protected ICFIntJavaFXSchema javafxSchema = null;
	protected Collection<ICFIntURLProtocolObj> javafxDataCollection = null;
	protected ObservableList<ICFIntURLProtocolObj> observableListOfURLProtocol = null;
	protected ScrollPane scrollMenu = null;
	protected CFHBox hboxMenu = null;
	protected CFButton buttonAddURLProtocol = null;
	protected CFButton buttonViewSelected = null;
	protected CFButton buttonEditSelected = null;
	protected CFButton buttonDeleteSelected = null;
	protected TableView<ICFIntURLProtocolObj> dataTable = null;
	protected TableColumn<ICFIntURLProtocolObj, Integer> tableColumnURLProtocolId = null;
	protected TableColumn<ICFIntURLProtocolObj, String> tableColumnName = null;
	protected TableColumn<ICFIntURLProtocolObj, String> tableColumnDescription = null;
	protected TableColumn<ICFIntURLProtocolObj, Boolean> tableColumnIsSecure = null;

	public final String S_ColumnNames[] = { "Name" };
	protected ICFFormManager cfFormManager = null;
	protected boolean javafxIsInitializing = true;
	protected boolean javafxSortByChain = false;
	protected ICFLibAnyObj javafxContainer = null;
	protected ICFRefreshCallback javafxRefreshCallback = null;
	class ViewEditClosedCallback implements ICFFormClosedCallback {
		public ViewEditClosedCallback() {
		}

		@Override
		public void formClosed( ICFLibAnyObj affectedObject ) {
			if( affectedObject != null ) {
				refreshMe();
			}
		}
	}

	protected ViewEditClosedCallback viewEditClosedCallback = null;

	public ICFFormClosedCallback getViewEditClosedCallback() {
		if( viewEditClosedCallback == null ) {
			viewEditClosedCallback = new ViewEditClosedCallback();
		}
		return( viewEditClosedCallback );
	}

	class DeleteCallback implements ICFDeleteCallback {
		public DeleteCallback() {
		}
		@Override
		public void deleted( ICFLibAnyObj deletedObject ) {
			if( deletedObject != null ) {
				refreshMe();
			}
		}

		@Override
		public void formClosed( ICFLibAnyObj affectedObject ) {
			if( affectedObject != null ) {
				refreshMe();
			}
		}
	}

	protected DeleteCallback deleteCallback = null;

	public ICFDeleteCallback getDeleteCallback() {
		if( deleteCallback == null ) {
			deleteCallback = new DeleteCallback();
		}
		return( deleteCallback );
	}


	public CFIntJavaFXURLProtocolListPane( ICFFormManager formManager,
		ICFIntJavaFXSchema argSchema,
		ICFLibAnyObj argContainer,
		ICFIntURLProtocolObj argFocus,
		Collection<ICFIntURLProtocolObj> argDataCollection,
		ICFRefreshCallback refreshCallback,
		boolean sortByChain )
	{
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
		javaFXFocus = argFocus;
		javafxContainer = argContainer;
		javafxRefreshCallback = refreshCallback;
		javafxSortByChain = sortByChain;
		setJavaFXDataCollection( argDataCollection );
		dataTable = new TableView<ICFIntURLProtocolObj>();
		tableColumnURLProtocolId = new TableColumn<ICFIntURLProtocolObj,Integer>( "URL Protocol Id" );
		tableColumnURLProtocolId.setCellValueFactory( new Callback<CellDataFeatures<ICFIntURLProtocolObj,Integer>,ObservableValue<Integer> >() {
			public ObservableValue<Integer> call( CellDataFeatures<ICFIntURLProtocolObj, Integer> p ) {
				ICFIntURLProtocolObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					int value = obj.getRequiredURLProtocolId();
					Integer wrapped = Integer.valueOf( value );
					ReadOnlyObjectWrapper<Integer> observable = new ReadOnlyObjectWrapper<Integer>();
					observable.setValue( wrapped );
					return( observable );
				}
			}
		});
		tableColumnURLProtocolId.setCellFactory( new Callback<TableColumn<ICFIntURLProtocolObj,Integer>,TableCell<ICFIntURLProtocolObj,Integer>>() {
			@Override public TableCell<ICFIntURLProtocolObj,Integer> call(
				TableColumn<ICFIntURLProtocolObj,Integer> arg)
			{
				return new CFInt32TableCell<ICFIntURLProtocolObj>();
			}
		});
		dataTable.getColumns().add( tableColumnURLProtocolId );
		tableColumnName = new TableColumn<ICFIntURLProtocolObj,String>( "Name" );
		tableColumnName.setCellValueFactory( new Callback<CellDataFeatures<ICFIntURLProtocolObj,String>,ObservableValue<String> >() {
			public ObservableValue<String> call( CellDataFeatures<ICFIntURLProtocolObj, String> p ) {
				ICFIntURLProtocolObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					String value = obj.getRequiredName();
					ReadOnlyObjectWrapper<String> observable = new ReadOnlyObjectWrapper<String>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnName.setCellFactory( new Callback<TableColumn<ICFIntURLProtocolObj,String>,TableCell<ICFIntURLProtocolObj,String>>() {
			@Override public TableCell<ICFIntURLProtocolObj,String> call(
				TableColumn<ICFIntURLProtocolObj,String> arg)
			{
				return new CFStringTableCell<ICFIntURLProtocolObj>();
			}
		});
		dataTable.getColumns().add( tableColumnName );
		tableColumnDescription = new TableColumn<ICFIntURLProtocolObj,String>( "Description" );
		tableColumnDescription.setCellValueFactory( new Callback<CellDataFeatures<ICFIntURLProtocolObj,String>,ObservableValue<String> >() {
			public ObservableValue<String> call( CellDataFeatures<ICFIntURLProtocolObj, String> p ) {
				ICFIntURLProtocolObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					String value = obj.getRequiredDescription();
					ReadOnlyObjectWrapper<String> observable = new ReadOnlyObjectWrapper<String>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnDescription.setCellFactory( new Callback<TableColumn<ICFIntURLProtocolObj,String>,TableCell<ICFIntURLProtocolObj,String>>() {
			@Override public TableCell<ICFIntURLProtocolObj,String> call(
				TableColumn<ICFIntURLProtocolObj,String> arg)
			{
				return new CFStringTableCell<ICFIntURLProtocolObj>();
			}
		});
		dataTable.getColumns().add( tableColumnDescription );
		tableColumnIsSecure = new TableColumn<ICFIntURLProtocolObj,Boolean>( "IsSecure" );
		tableColumnIsSecure.setCellValueFactory( new Callback<CellDataFeatures<ICFIntURLProtocolObj,Boolean>,ObservableValue<Boolean> >() {
			public ObservableValue<Boolean> call( CellDataFeatures<ICFIntURLProtocolObj, Boolean> p ) {
				ICFIntURLProtocolObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					boolean value = obj.getRequiredIsSecure();
					Boolean wrapped = Boolean.valueOf( value );
					ReadOnlyObjectWrapper<Boolean> observable = new ReadOnlyObjectWrapper<Boolean>();
					observable.setValue( wrapped );
					return( observable );
				}
			}
		});
		tableColumnIsSecure.setCellFactory( new Callback<TableColumn<ICFIntURLProtocolObj,Boolean>,TableCell<ICFIntURLProtocolObj,Boolean>>() {
			@Override public TableCell<ICFIntURLProtocolObj,Boolean> call(
				TableColumn<ICFIntURLProtocolObj,Boolean> arg)
			{
				return new CFBoolTableCell<ICFIntURLProtocolObj>();
			}
		});
		dataTable.getColumns().add( tableColumnIsSecure );
		dataTable.getSelectionModel().selectedItemProperty().addListener(
			new ChangeListener<ICFIntURLProtocolObj>() {
				@Override public void changed( ObservableValue<? extends ICFIntURLProtocolObj> observable,
					ICFIntURLProtocolObj oldValue,
					ICFIntURLProtocolObj newValue )
				{
					setJavaFXFocus( newValue );
				}
			});

		scrollMenu = new ScrollPane();
		scrollMenu.setVbarPolicy( ScrollBarPolicy.NEVER );
		scrollMenu.setHbarPolicy( ScrollBarPolicy.AS_NEEDED );
		scrollMenu.setFitToHeight( true );
		scrollMenu.setContent( getPanelHBoxMenu() );

		setTop( scrollMenu );
		setCenter( dataTable );
		javafxIsInitializing = false;
		if( observableListOfURLProtocol != null ) {
			dataTable.setItems( observableListOfURLProtocol );
		}
		adjustListButtons();
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

	public void setPaneMode( CFPane.PaneMode value ) {
		super.setPaneMode( value );
		adjustListButtons();
	}

	public void setJavaFXFocus( ICFLibAnyObj value ) {
		final String S_ProcName = "setJavaFXFocus";
		if( ( value == null ) || ( value instanceof ICFIntURLProtocolObj ) ) {
			super.setJavaFXFocus( value );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				S_ProcName,
				"value",
				value,
				"ICFIntURLProtocolObj" );
		}
		adjustListButtons();
	}

	public ICFIntURLProtocolObj getJavaFXFocusAsURLProtocol() {
		return( (ICFIntURLProtocolObj)getJavaFXFocus() );
	}

	public void setJavaFXFocusAsURLProtocol( ICFIntURLProtocolObj value ) {
		setJavaFXFocus( value );
	}

	public class URLProtocolByQualNameComparator
	implements Comparator<ICFIntURLProtocolObj>
	{
		public URLProtocolByQualNameComparator() {
		}

		public int compare( ICFIntURLProtocolObj lhs, ICFIntURLProtocolObj rhs ) {
			if( lhs == null ) {
				if( rhs == null ) {
					return( 0 );
				}
				else {
					return( -1 );
				}
			}
			else if( rhs == null ) {
				return( 1 );
			}
			else {
				String lhsValue = lhs.getObjQualifiedName();
				String rhsValue = rhs.getObjQualifiedName();
				if( lhsValue == null ) {
					if( rhsValue == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhsValue == null ) {
					return( 1 );
				}
				else {
					return( lhsValue.compareTo( rhsValue ) );
				}
			}
		}
	}

	protected URLProtocolByQualNameComparator compareURLProtocolByQualName = new URLProtocolByQualNameComparator();

	public Collection<ICFIntURLProtocolObj> getJavaFXDataCollection() {
		return( javafxDataCollection );
	}

	public void setJavaFXDataCollection( Collection<ICFIntURLProtocolObj> value ) {
		final String S_ProcName = "setJavaFXDataCollection";
		javafxDataCollection = value;
		observableListOfURLProtocol = FXCollections.observableArrayList();
		if( javafxDataCollection != null ) {
				Iterator<ICFIntURLProtocolObj> iter = javafxDataCollection.iterator();
				while( iter.hasNext() ) {
					observableListOfURLProtocol.add( iter.next() );
				}
				observableListOfURLProtocol.sort( compareURLProtocolByQualName );
		}
		if( dataTable != null ) {
			dataTable.setItems( observableListOfURLProtocol );
			// Hack from stackoverflow to fix JavaFX TableView refresh issue
			((TableColumn)dataTable.getColumns().get(0)).setVisible( false );
			((TableColumn)dataTable.getColumns().get(0)).setVisible( true );
		}
	}

	protected class CompareCFButtonByText
	implements Comparator<CFButton>
	{
		public CompareCFButtonByText() {
		}

		@Override public int compare( CFButton lhs, CFButton rhs ) {
			if( lhs == null ) {
				if( rhs == null ) {
					return( 0 );
				}
				else {
					return( -1 );
				}
			}
			else if( rhs == null ) {
				return( 1 );
			}
			else {
				int retval = lhs.getText().compareTo( rhs.getText() );
				return( retval );
			}
		}
	}

	public CFHBox getPanelHBoxMenu() {
		if( hboxMenu == null ) {
			hboxMenu = new CFHBox( 10 );
			buttonAddURLProtocol = new CFButton();
			buttonAddURLProtocol.setMinWidth( 200 );
			buttonAddURLProtocol.setText( "Add URL Protocol" );
			buttonAddURLProtocol.setOnAction( new EventHandler<ActionEvent>() {
				@Override public void handle( ActionEvent e ) {
					final String S_ProcName = "handle";
					try {
						ICFIntSchemaObj schemaObj = (ICFIntSchemaObj)javafxSchema.getSchema();
						ICFIntURLProtocolObj obj = (ICFIntURLProtocolObj)schemaObj.getURLProtocolTableObj().newInstance();
						ICFIntURLProtocolEditObj edit = (ICFIntURLProtocolEditObj)( obj.beginEdit() );
						if( edit == null ) {
							throw new CFLibNullArgumentException( getClass(),
								S_ProcName,
								0,
								"edit" );
						}
						CFBorderPane frame = javafxSchema.getURLProtocolFactory().newAddForm( cfFormManager, obj, getViewEditClosedCallback(), true );
						ICFIntJavaFXURLProtocolPaneCommon jpanelCommon = (ICFIntJavaFXURLProtocolPaneCommon)frame;
						jpanelCommon.setJavaFXFocus( obj );
						jpanelCommon.setPaneMode( CFPane.PaneMode.Add );
						cfFormManager.pushForm( frame );
					}
					catch( Throwable t ) {
						CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
					}
				}
			});
			hboxMenu.getChildren().add( buttonAddURLProtocol );
			buttonViewSelected = new CFButton();
			buttonViewSelected.setMinWidth( 200 );
			buttonViewSelected.setText( "View Selected" );
			buttonViewSelected.setOnAction( new EventHandler<ActionEvent>() {
				@Override public void handle( ActionEvent e ) {
					final String S_ProcName = "handle";
					try {
						ICFIntSchemaObj schemaObj = (ICFIntSchemaObj)javafxSchema.getSchema();
						if( schemaObj == null ) {
							throw new CFLibNullArgumentException( getClass(),
								S_ProcName,
								0,
								"schemaObj" );
						}
						ICFIntURLProtocolObj selectedInstance = getJavaFXFocusAsURLProtocol();
						if( selectedInstance != null ) {
							int classCode = selectedInstance.getClassCode();
							ICFSecSchema.ClassMapEntry entry = ICFIntSchema.getClassMapByRuntimeClassCode(classCode);
							int backingClassCode = entry.getBackingClassCode();
							if( entry.getSchemaName().equals("CFInt") && backingClassCode == ICFIntURLProtocol.CLASS_CODE ) {
								CFBorderPane frame = javafxSchema.getURLProtocolFactory().newViewEditForm( cfFormManager, selectedInstance, getViewEditClosedCallback(), false );
								((ICFIntJavaFXURLProtocolPaneCommon)frame).setPaneMode( CFPane.PaneMode.View );
								cfFormManager.pushForm( frame );
							}
							else {
								throw new CFLibUnsupportedClassException( getClass(),
									S_ProcName,
									"selectedInstance",
									selectedInstance,
									"ICFIntURLProtocolObj" );
							}
						}
					}
					catch( Throwable t ) {
						CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
					}
				}
			});
			hboxMenu.getChildren().add( buttonViewSelected );

			buttonEditSelected = new CFButton();
			buttonEditSelected.setMinWidth( 200 );
			buttonEditSelected.setText( "Edit Selected" );
			buttonEditSelected.setOnAction( new EventHandler<ActionEvent>() {
				@Override public void handle( ActionEvent e ) {
					final String S_ProcName = "handle";
					try {
						ICFIntSchemaObj schemaObj = (ICFIntSchemaObj)javafxSchema.getSchema();
						if( schemaObj == null ) {
							throw new CFLibNullArgumentException( getClass(),
								S_ProcName,
								0,
								"schemaObj" );
						}
						ICFIntURLProtocolObj selectedInstance = getJavaFXFocusAsURLProtocol();
						if( selectedInstance != null ) {
							int classCode = selectedInstance.getClassCode();
							ICFSecSchema.ClassMapEntry entry = ICFIntSchema.getClassMapByRuntimeClassCode(classCode);
							int backingClassCode = entry.getBackingClassCode();
							if( entry.getSchemaName().equals("CFInt") && backingClassCode == ICFIntURLProtocol.CLASS_CODE ) {
								CFBorderPane frame = javafxSchema.getURLProtocolFactory().newViewEditForm( cfFormManager, selectedInstance, getViewEditClosedCallback(), false );
								((ICFIntJavaFXURLProtocolPaneCommon)frame).setPaneMode( CFPane.PaneMode.Edit );
								cfFormManager.pushForm( frame );
							}
							else {
								throw new CFLibUnsupportedClassException( getClass(),
									S_ProcName,
									"selectedInstance",
									selectedInstance,
									"ICFIntURLProtocolObj" );
							}
						}
					}
					catch( Throwable t ) {
						CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
					}
				}
			});
			hboxMenu.getChildren().add( buttonEditSelected );

			buttonDeleteSelected = new CFButton();
			buttonDeleteSelected.setMinWidth( 200 );
			buttonDeleteSelected.setText( "Delete Selected" );
			buttonDeleteSelected.setOnAction( new EventHandler<ActionEvent>() {
				@Override public void handle( ActionEvent e ) {
					final String S_ProcName = "handle";
					try {
						ICFIntSchemaObj schemaObj = (ICFIntSchemaObj)javafxSchema.getSchema();
						if( schemaObj == null ) {
							throw new CFLibNullArgumentException( getClass(),
								S_ProcName,
								0,
								"schemaObj" );
						}
						ICFIntURLProtocolObj selectedInstance = getJavaFXFocusAsURLProtocol();
						if( selectedInstance != null ) {
							int classCode = selectedInstance.getClassCode();
							ICFSecSchema.ClassMapEntry entry = ICFIntSchema.getClassMapByRuntimeClassCode(classCode);
							int backingClassCode = entry.getBackingClassCode();
							if( entry.getSchemaName().equals("CFInt") && backingClassCode == ICFIntURLProtocol.CLASS_CODE ) {
								CFBorderPane frame = javafxSchema.getURLProtocolFactory().newAskDeleteForm( cfFormManager, selectedInstance, getDeleteCallback() );
								((ICFIntJavaFXURLProtocolPaneCommon)frame).setPaneMode( CFPane.PaneMode.View );
								cfFormManager.pushForm( frame );
							}
							else {
								throw new CFLibUnsupportedClassException( getClass(),
									S_ProcName,
									"selectedInstance",
									selectedInstance,
									"ICFIntURLProtocolObj" );
							}
						}
					}
					catch( Throwable t ) {
						CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
					}
				}
			});
			hboxMenu.getChildren().add( buttonDeleteSelected );

		}
		return( hboxMenu );
	}

	public ICFLibAnyObj getJavaFXContainer() {
		return( javafxContainer );
	}

	public void setJavaFXContainer( ICFLibAnyObj value ) {
		javafxContainer = value;
	}

	public void refreshMe() {
		if( javafxRefreshCallback != null ) {
			javafxRefreshCallback.refreshMe();
		}
	}

	public void adjustListButtons() {
		boolean enableState;
		boolean inEditState;
		boolean allowAdds;
		boolean inAddMode = false;
		ICFIntURLProtocolObj selectedObj = getJavaFXFocusAsURLProtocol();
		CFPane.PaneMode mode = getPaneMode();
		if( mode == CFPane.PaneMode.Edit ) {
			inEditState = true;
			allowAdds = false;
		}
		else {
			inEditState = false;
			if( getJavaFXContainer() != null ) {
				if( getLeft() != null ) {
					allowAdds = false;
					inAddMode = true;
				}
				else {
					allowAdds = true;
				}
			}
			else {
				allowAdds = false;
			}
		}
		if( selectedObj == null ) {
			enableState = false;
		}
		else {
			if( ( ! inAddMode ) && ( ! inEditState ) ) {
				enableState = true;
			}
			else {
				enableState = false;
			}
		}

		if( buttonViewSelected != null ) {
			buttonViewSelected.setDisable( ! enableState );
		}
		if( buttonEditSelected != null ) {
			if( inEditState ) {
				buttonEditSelected.setDisable( true );
			}
			else {
				buttonEditSelected.setDisable( ! enableState );
			}
		}
		if( buttonDeleteSelected != null ) {
			buttonDeleteSelected.setDisable( ! enableState );
		}
		if( buttonAddURLProtocol != null ) {
			buttonAddURLProtocol.setDisable( ! allowAdds );
		}

	}
}
