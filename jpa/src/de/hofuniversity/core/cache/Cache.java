package de.hofuniversity.core.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class Cache<E>
{
    private Collection<E> collection;
    
    protected Cache() {}
    
    protected Collection<E> getCollection() {
	if (this.collection == null) {
	    this.collection = new ArrayList<E>();
	}
	return this.collection;
    }
    
    public Collection<E> getUnmodifiableCollection() {
	if (this.getCollection().isEmpty()) {
	    return Collections.emptySet();
	}
	return Collections.unmodifiableCollection(this.getCollection());
    }
    
    public void add(E entity) {
	if (entity == null) {
	    throw new IllegalArgumentException("Can not add NULL to cache " + this.getClass().getSimpleName() + ".");
	}
	this.getCollection().add(entity);
    }
}
