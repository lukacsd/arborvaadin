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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.vaadin.arborgraph.Branch;
import org.vaadin.arborgraph.Edge;
import org.vaadin.arborgraph.Node;

public class DemoBranch implements Branch {
	private Set<Node> nodes = new HashSet<Node>( );
	private Set<Edge> edges = new HashSet<Edge>( );
	private Map<String, List<Edge>> edgesByHead = new HashMap<String, List<Edge>>( );

	public void addNode( Node node ) {
		nodes.add( node );
	}

	@Override
	public Collection<Node> getNodes() {
		return Collections.unmodifiableCollection( nodes );
	}

	public void addEdge( Edge edge ) {
		nodes.add( edge.getHead( ) );
		nodes.add( edge.getTail( ) );

		edges.add( edge );

		List<Edge> edgesToHead = edgesByHead.get( edge.getHead( ).getName( ) );

		if ( edgesToHead == null ) {
			edgesToHead = new LinkedList<Edge>( );
		}

		edgesToHead.add( edge );
		edgesByHead.put( edge.getHead( ).getName( ), edgesToHead );
	}

	@Override
	public boolean hasEdge( Node node ) {
		return edgesByHead.containsKey( node.getName( ) );
	}

	@Override
	public Collection<Edge> getEdgesByNode( Node node ) {
		Collection<Edge> retval = edgesByHead.get( node.getName( ) );

		if ( retval == null ) {
			retval = new LinkedList<Edge>( );
		}

		return Collections.unmodifiableCollection( retval );
	}

}
