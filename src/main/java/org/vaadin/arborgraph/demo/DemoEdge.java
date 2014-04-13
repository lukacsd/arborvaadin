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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.arborgraph.Edge;
import org.vaadin.arborgraph.Node;

public class DemoEdge implements Edge {
	private Node head;
	private Node tail;
	private Map<String, String> attributes = new HashMap<String, String>( );

	public DemoEdge( Node head, Node tail ) {
		this.head = head;
		this.tail = tail;
	}

	@Override
	public Node getHead() {
		return head;
	}

	@Override
	public Node getTail() {
		return tail;
	}

	public void addAttribute( String name, String value ) {
		attributes.put( name, value );
	}

	@Override
	public Map<String, String> getAttributes() {
		return Collections.unmodifiableMap( attributes );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( attributes == null ) ? 0 : attributes.hashCode( ) );
		result = prime * result + ( ( head == null ) ? 0 : head.hashCode( ) );
		result = prime * result + ( ( tail == null ) ? 0 : tail.hashCode( ) );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass( ) != obj.getClass( ) )
			return false;
		DemoEdge other = ( DemoEdge ) obj;
		if ( attributes == null ) {
			if ( other.attributes != null )
				return false;
		} else if ( !attributes.equals( other.attributes ) )
			return false;
		if ( head == null ) {
			if ( other.head != null )
				return false;
		} else if ( !head.equals( other.head ) )
			return false;
		if ( tail == null ) {
			if ( other.tail != null )
				return false;
		} else if ( !tail.equals( other.tail ) )
			return false;
		return true;
	}

}
