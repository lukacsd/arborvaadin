/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014, David Lukacs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.vaadin.arborgraph.demo;

import org.vaadin.arborgraph.ArborGraph;
import org.vaadin.arborgraph.Branch;
import org.vaadin.arborgraph.Edge;
import org.vaadin.arborgraph.Node;

import com.vaadin.Application;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class DemoApplication extends Application {
	private static final long serialVersionUID = 5197785337949350577L;

	@SuppressWarnings( "serial" )
	@Override
	public void init() {
		VerticalLayout aboutLayout = new VerticalLayout( );
		aboutLayout.setSizeFull( );
		aboutLayout.addComponent( new Label( getBlah( ), Label.CONTENT_XHTML ) );

		final ArborGraph graph = new ArborGraph( );
		graph.setSizeFull( );

		Button exampleGraphButton = new Button( "Show Example", new ClickListener( ) {

			public void buttonClick( ClickEvent event ) {
				graph.showGraph( getDemoBranch( ) );
			}
		} );

		Label orLabel = new Label( "<i>or</i>", Label.CONTENT_XHTML );
		orLabel.setSizeUndefined( );

		final TextField jsonTextfield = new TextField( );
		jsonTextfield.setWidth( 100, Sizeable.UNITS_PERCENTAGE );
		jsonTextfield.setNullRepresentation( "" );

		Button jsonBranchButton = new Button( "Display Json Branch", new ClickListener( ) {

			public void buttonClick( ClickEvent event ) {
				graph.showGraph( jsonTextfield.getValue( ).toString( ) );
			}
		} );

		Label helpLabel = getHelpLabel( getBranchExplanation( ) );

		HorizontalLayout graphButtonLayout = new HorizontalLayout( );
		graphButtonLayout.setWidth( 100, Sizeable.UNITS_PERCENTAGE );
		graphButtonLayout.setSpacing( true );
		graphButtonLayout.setMargin( false, false, true, false );
		graphButtonLayout.addComponent( exampleGraphButton );
		graphButtonLayout.addComponent( orLabel );
		graphButtonLayout.setComponentAlignment( orLabel, Alignment.MIDDLE_CENTER );
		graphButtonLayout.addComponent( jsonBranchButton );
		graphButtonLayout.addComponent( helpLabel );
		graphButtonLayout.setComponentAlignment( helpLabel, Alignment.MIDDLE_CENTER );
		graphButtonLayout.addComponent( jsonTextfield );
		graphButtonLayout.setExpandRatio( jsonTextfield, .9f );

		VerticalLayout graphLayout = new VerticalLayout( );
		graphLayout.setSizeFull( );
		graphLayout.setMargin( true, false, false, false );
		graphLayout.setSpacing( true );
		graphLayout.addComponent( graphButtonLayout );
		graphLayout.addComponent( graph );
		graphLayout.setExpandRatio( graph, .9f );

		TabSheet tabSheet = new TabSheet( );
		tabSheet.setStyleName( Reindeer.TABSHEET_MINIMAL );
		tabSheet.setSizeFull( );
		tabSheet.addTab( aboutLayout, "About" );
		tabSheet.addTab( graphLayout, "Graph" );

		VerticalLayout mainLayout = new VerticalLayout( );
		mainLayout.setSizeFull( );
		mainLayout.setMargin( true );
		mainLayout.addComponent( tabSheet );
		mainLayout.setExpandRatio( tabSheet, .9f );

		Window mainWindow = new Window( "Arbor-Vaadin" );
		mainWindow.setSizeFull( );
		mainWindow.setContent( mainLayout );

		setTheme( "arbor" );

		setMainWindow( mainWindow );
	}

	private String getBlah() {
		StringBuilder blah = new StringBuilder( );

		blah.append( "<h1>Arbor-Vaadin</h1>" );
		blah.append( "<p>Arbor-Vaadin is an <b>Arbor.js</b> Java binding for the <b>Vaadin <i>6</i></b> framework." );
		blah.append( "It provides the user with functionality to model a graph as POJOs, and also makes " );
		blah.append( "pushing the model from the server component to the client available.</p>" );
		blah.append( "<h2>Arbor.js</h2>" );
		blah.append( "<p>Arbor.js is a very nice, interactive, force-layout graph library by the way. " );
		blah.append( "See <a href=\"http://arborjs.org\" target=\"_blank\">arborjs.org</a></p>" );
		blah.append( "<h2>Source</h2>" );
		blah.append( "<p>Download Arbor-Vaadin from <a href=\"https://github.com/lukacsd/arborvaadin\" target=\"_blank\">Github</a>.</p>" );
		blah.append( "<h2>License</h2>" );
		blah.append( "<p>Arbor-Vaadin is released under the <a href=\"http://en.wikipedia.org/wiki/MIT_License\" target=\"_blank\">MIT license</a>.</p>" );

		return blah.toString( );
	}

	private Label getHelpLabel( String help ) {
		Label retval = new Label( );

		retval.addStyleName( "help" );
		retval.setDescription( help );
		retval.setWidth( 18, Sizeable.UNITS_PIXELS );
		retval.setHeight( 18, Sizeable.UNITS_PIXELS );

		return retval;
	}

	private String getBranchExplanation() {
		StringBuilder retval = new StringBuilder( );

		retval.append( "<p style=\"font-weight: bolder;\">According to the Arbor.js <a href=\"http://arborjs.org/reference\" target=\"_blank\">reference</a>:</p>" );
		retval.append( "<div style=\"font-style: italic; margin: 0 0 0 10px;\">" );
		retval.append( "<p><span style=\"color: #ad621c;\">branch</span> is an object of the form <span style=\"font: 12px Monaco, Courier, monospace;\">{nodes:{}, edges:{}}</span>.</p>" );
		retval.append( "<p>the <span style=\"color: #ad621c;\">nodes</span> attribute contains a mapping of node names to data objects. For example,</p>" );
		retval.append( "<p style=\"font:12px Monaco, Courier, monospace;\">{ nodes:{foo:{color:\"red\", mass:2},<br> " );
		retval.append( "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bar:{color:\"green\"}} }</p>" );
		retval.append( "<p>the <span style=\"color: #ad621c;\">edges</span> attribute contains nested objects to map source identifier to target, then target to edge data object. e.g,</p>" );
		retval.append( "<p style=\"font: 12px Monaco, Courier, monospace;\">{ edges:{bar:{foo:{similarity:0},<br>" );
		retval.append( "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;baz:{similarity:.666}} }</p>" );
		retval.append( "</div>" );

		return retval.toString( );
	}

	private Branch getDemoBranch() {
		DemoBranch retval = new DemoBranch( );

		DemoNode nerd = getNode( "nerd" );
		nerd.addAttribute( "alone", "true" );
		retval.addNode( nerd );

		retval.addEdge( getEdge( getNode( "Center" ), getNode( "South" ) ) );
		retval.addEdge( getEdge( getNode( "South East" ), getNode( "East" ) ) );
		retval.addEdge( getEdge( getNode( "South East" ), getNode( "South" ) ) );
		retval.addEdge( getEdge( getNode( "Center" ), getNode( "East" ) ) );
		retval.addEdge( getEdge( getNode( "North East" ), getNode( "East" ) ) );
		retval.addEdge( getEdge( getNode( "North East" ), getNode( "North" ) ) );
		retval.addEdge( getEdge( getNode( "Center" ), getNode( "North" ) ) );
		retval.addEdge( getEdge( getNode( "North West" ), getNode( "North" ) ) );
		retval.addEdge( getEdge( getNode( "North West" ), getNode( "West" ) ) );
		retval.addEdge( getEdge( getNode( "Center" ), getNode( "West" ) ) );
		retval.addEdge( getEdge( getNode( "South West" ), getNode( "West" ) ) );
		retval.addEdge( getEdge( getNode( "South West" ), getNode( "South" ) ) );

		return retval;
	}

	private DemoNode getNode( String name ) {
		DemoNode retval = new DemoNode( name );

		return retval;
	}

	private Edge getEdge( Node head, Node tail ) {
		Edge retval = new DemoEdge( head, tail );

		return retval;
	}

}
