package it.unibo.oop.lab.nesting2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * OneListAcceptable implementation
 * @author giovanniantonioni
 *
 * @param <T>
 */
public class OneListAcceptable<T> implements Acceptable<T> {

	private final List<T> checkList;

	public OneListAcceptable(List<T> checkList) {
		super();
		this.checkList = new LinkedList<T>(checkList);
	}

	public Acceptor<T> acceptor() {
		return new Acceptor<T>() {

			private final Deque<T> elementCheckerDeque = new LinkedList<T>(OneListAcceptable.this.checkList);
			
			/**
			 * {@inheritDoc}
			 */
			public void accept(T newElement) throws ElementNotAcceptedException {
				try {
					T queueElement = elementCheckerDeque.removeFirst();
					if(!queueElement.equals(newElement)) {
						throw new ElementNotAcceptedException(newElement);
					}
				} catch (NoSuchElementException e) {
					throw new ElementNotAcceptedException(newElement);
				}
			}

			/**
			 * {@inheritDoc}
			 */
			public void end() throws EndNotAcceptedException {
				try {
					if (elementCheckerDeque.isEmpty()) {
						return;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				throw new EndNotAcceptedException();
			}
		};
	}

}
