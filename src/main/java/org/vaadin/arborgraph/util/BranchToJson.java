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

package org.vaadin.arborgraph.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.vaadin.arborgraph.Branch;
import org.vaadin.arborgraph.Edge;
import org.vaadin.arborgraph.Node;

public class BranchToJson {

	public String getJson( Branch branch ) {
		String retval = null;

		try {
			ObjectMapper mapper = new ObjectMapper( );

			retval = mapper.writeValueAsString( getData( branch ) );
		} catch ( Exception e ) {
			throw new IllegalArgumentException( e );
		}

		return retval;
	}

	private Object getData( Branch branch ) {
		Map<String, Object> retval = new LinkedHashMap<>( );

		Map<String, Object> nodes = new HashMap<String, Object>( );
		Map<String, Object> edges = new HashMap<String, Object>( );

		retval.put( "nodes", nodes );
		retval.put( "edges", edges );

		for ( Node node : branch.getNodes( ) ) {
			nodes.put( node.getName( ), node.getAttributes( ) );

			if ( branch.hasEdge( node ) ) {
				Map<String, Object> nodeEdges = new HashMap<String, Object>( );

				for ( Edge edge : branch.getEdgesByNode( node ) ) {
					nodeEdges.put( edge.getTail( ).getName( ), edge.getAttributes( ) );
				}

				edges.put( node.getName( ), nodeEdges );
			}
		}

		return retval;
	}

}
